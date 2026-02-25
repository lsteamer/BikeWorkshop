package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MotorcycleAddViewModel(
    private val motorcycleRepository: MotorcycleRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    private val _state = MutableStateFlow(MotorcycleAddState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MotorcycleAddState()
        )

    /* given that this "screen" has a lot of actions. It is here where I think one can finally
     * see the benefits of using an architecture like this.
     *
     * It's still too much for this small project, but as projects grow in size,
     * having EVERY ACTION trackable in a TypeSafe Manner can be very
     * beneficial for readability, refactoring and issue tracking and solving.
     * and having most actions be in this part of the viewModel also helps with readability
     *
     * Right now the actions are just "updating text", but it can be calling complex functions and
     * having "Actions" clearly defined allows us to better understand and modify the code.
     *
     * It will take more than 3 hours tho.
     */
    fun onAction(action: MotorcycleAddAction) {
        when (action) {
            MotorcycleAddAction.OnClickOnSubmit -> {

                val motorcycle = state.value.run {
                    Motorcycle(
                        manufacturer = manufacturerText,
                        model = modelText,
                        powerPS = powerPS.toInt(),
                        type = motorcycleType,
                        yearOfConstruction = yearOfConstructionValue.toInt()
                    )
                }
                viewModelScope.launch {
                    motorcycle.let { motorcycle ->
                        motorcycleRepository.insertMotorcycle(motorcycle)
                        _navigateBack.emit(Unit)
                    }
                }

            }

            is MotorcycleAddAction.OnManufacturerTextFieldChange -> {
                _state.update {
                    it.copy(
                        manufacturerText = action.manufacturerText
                    )
                }

            }

            is MotorcycleAddAction.OnModelTextFieldChange -> {
                _state.update {
                    it.copy(
                        modelText = action.modelText
                    )
                }
            }

            is MotorcycleAddAction.OnPowerPSTextFieldChange -> {
                _state.update {
                    it.copy(
                        powerPS = action.powerPSText
                    )
                }

            }

            is MotorcycleAddAction.OnTypeDropdownMenuChange -> {
                _state.update {
                    it.copy(
                        motorcycleType = action.typeValue
                    )
                }

            }

            is MotorcycleAddAction.OnYearTextFieldChange -> {
                _state.update {
                    it.copy(
                        yearOfConstructionValue = action.yearText
                    )
                }

            }
        }
    }

}
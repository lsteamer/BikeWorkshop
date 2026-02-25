package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import com.anjegonz.bikeworkshop.garage.domain.core.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Year

class MotorcycleAddViewModel(
    private val motorcycleRepository: MotorcycleRepository
) : ViewModel() {

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    private val _state = MutableStateFlow(MotorcycleAddState())
    val state = _state
        .map { state ->
            state.copy(
                areAllFieldsFilled = validateFields(state)
            )

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MotorcycleAddState()
        )

    /**
     * DOCUMENTATION calimoto
     * given that this "screen" has a lot of actions. It is here where I think one can finally
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
                    when(motorcycleRepository.insertMotorcycle(motorcycle)){
                        /**
                         * DOCUMENTATION calimoto
                         * This is another "aspect" that is/was a bit overkill for this small app.
                         * But in a larger, more complex app, this kind of approach would be very
                         * adaptable and will allow you to browse any sort of Failures and then
                         * act upon them ON the UI.
                         */
                        is Result.Success -> _navigateBack.emit(Unit)
                        is Result.Failure -> {
                            println("There was an issue")
                            //Here one would ideally show a notification to the user.
                        }
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

    private fun validateFields(state: MotorcycleAddState): Boolean {

        return state.run {
            val yearInt = yearOfConstructionValue.toIntOrNull()
            val currentYear = Year.now().value
            manufacturerText.isNotBlank() &&
                    modelText.isNotBlank() &&
                    powerPS.isNotBlank() &&
                    yearInt != null &&
                    yearInt in 1900..currentYear
        }


    }

}
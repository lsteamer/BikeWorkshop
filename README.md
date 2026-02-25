  
\# BikeWorkshop

  

An Android app to showcase my skills in a project. You see a list of Motorcycles, click to see their details and add new Motorcycles.

  

Built with Kotlin, Jetpack Compose, Room, and Koin.

  

The “documentation” in the app that I feel works for you is labeled as:    
/\*\*  
'\* DOCUMENTATION calimoto    
…    
…

  

\---

  

\#\# What is it?

  

\- See all your motorcycles in a list

\- Add a new one with manufacturer, model, power (PS), type, and year

\- Tap any bike to see its full details and calculated age

\- See other bikes from the same manufacturer in a carousel on the detail screen

\- Delete a motorcycle from the Workshop.

  

\---

  

\#\# Architecture

  

This follows the same blueprint I use in most of my recent projects (iNeedAnotherVolunteer, Bookpedia, and to a degree Chirp) have the same feel to them

  

Is what I’d consider a MVI with three layers    
  

\- \*\*Domain\*\* — the innermost layer, only business logic. \`Motorcycle\`, \`MotorcycleType\`, the \`MotorcycleRepository\` contract and the Result handler.

\- \*\*Data\*\* — Room database, DAO, the repository implementation, Converters and the mappers.

\- \*\*Presentation\*\* — Jetpack Compose screens and ViewModels.

  

The navigation uses type-safe routes via \`@Serializable\` data classes/objects.

  

\#\#\# The 4-file pattern

  

Each feature (screen) is made up of four files:

  

1\. \*\*Action\*\* — a sealed interface that registers everything the user can possibly do on that screen.

2\. \*\*State\*\* — a data class that holds everything that can change on screen. This is what the UI observes and the single source of truth

3\. \*\*Screen\*\* — the Compose UI. The “root” function takes the viewModel and starts to observe the state. Then we give a \`State\` and an \`onAction\` callback to the screen. The screen itself doesn’t know about ViewModels, it doesn't talk to the database. Separation of concerns.

4\. \*\*ViewModel\*\* — receives actions via \`onAction\`, does what viewModel do, does calls to the Database with the repository, updates the state (and through it the UI).

  

There are 3 features/screens in the app.  

\- \*\*MotorcycleList\*\*  
\- \*\*MotorcycleAdd\*\*  
\- \*\*MotorcycleDisplay\*\*  
  

\#\#\# Why this structure

  

I’ve grown comfortable with this structure. It can be overkill for a small app like this one, but when the \*\*State\*\* is the single source of truth, it means any issue with the UI will always be examined by the state, no need to look for scattered stuff. If it’s in the UI, it went through the state.

  

The \*\*Action\*\* file is a good way to have a “contract” regarding what the feature should do. On small apps like this one it can feel a lot for something small (There was no action taken from the viewModel in MotorcycleList). But on when apps grow and features have a lot of actions, (MotorcycleAdd has several actions) having every interaction typed and named makes the ViewModel's \`onAction\` block readable, what the viewmodel does is laid out clearly and makes it easy to understand new code. It also makes refactoring a lot safer, when you need to change something, you know where to look.

  

It's somewhat of a boilerplate for this app, but it showcases my skillset.

  

\---

  

\#\# What’s missing?

  

I would have done a nice “year picker” when adding the Motorcycle, done the MotorcycleAdd and the MotorcycleDisplay screens a bit nicer, better validation on the MotorcycleAdd, maybe Toasts, Would have modified the carousel, both how it looks and what it does (and maybe a headline) I would have made it so the Motorcycle being displayed was absent from the carousel (if you only have 1 motorcycle, there’s no carousel.

  

The really big one would have been the Unit Tests. There’s a lot of low hanging fruit there and under different circumstances I would have gone in and worked on it. But I did take more than the 3 hours originally required. I didn’t hard time myself, but I must have made over 4 hours. It’s difficult to know what “counts” as coding.  

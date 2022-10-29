# Android Basic Project
Android Basic Project (case: Movie App) built with simple Clean Architecture that connects to TMDB API (themoviedb.org)

## Libraries
- [Jetpack Navigation](https://developer.android.com/guide/navigation) with single Activity
- [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines) and [Flow](https://developer.android.com/kotlin/flow)
- [Koin](https://insert-koin.io/) as Dependency Injection
- [Room](https://developer.android.com/training/data-storage/room) as Persistence storage
- [Paging v3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for pagination list
- [Retrofit](https://square.github.io/retrofit/) as remote api service
- Mockito (testing)
- Espresso (ui testing)

## Package Structure
Globally the project has the following top level packages:
- `core` : Contains models, repositories and all the related data accessing and manipulating objects.
- `di` : Dependency module (App Level) provided by Koin
- `presentation` : Contains Views (Activity, Fragment), Adapter, ViewModel.
- `utils` : Contains helper extension.

## Tech debt
- Modularization
- Implement UseCase when the project is more complex
- Better UI TestCase, especially for testing navigation

## References
- https://medium.com/@BerkOzyurt/android-clean-architecture-mvvm-usecase-ae1647f0aea3
- https://developer.android.com/codelabs/android-paging
- https://developer.android.com/training/testing/ui-testing/espresso-testing


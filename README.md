
# PeopleCert task (Camera Features)

**This task helps to use most of live camera feeds features:**

### Architecture
* [Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164)
* [MVVM](https://www.raywenderlich.com/8984-mvvm-on-android)

### Patterns
* [Repository](https://developer.android.com/jetpack/docs/guide)

### Approaches
* [SOLID Principle](https://itnext.io/solid-principles-explanation-and-examples-715b975dcad4?gi=79443348411d)


### Technology Stack
* [Kotlin](https://kotlinlang.org/)
* [Java](https://docs.oracle.com/)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Dagger 2](https://github.com/google/dagger)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Android Jetpack](https://developer.android.com/jetpack)
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * [Room DB](https://developer.android.com/training/data-storage/room)
* [Agora IO](https://docs.agora.io/en)
* [Camera2 API](https://developer.android.com/training/camera2)


### Layers
* core(This layer is responsible to hold interactors (usecases), domain and data ).
* dagger(layer holds everything about dependency injection)
* presentation (Responsible for UI stuff such as displaying data)


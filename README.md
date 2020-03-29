# facts-android
==================

**Installation**

- The application should be ready to be built and then to install after cloning from the repository

- A working internet connection would be required for the first time, to download all the dependencies - gradle, third party libs etc.


**Usage and UI Features**

- The app currently shows a very minimal and basic UI.

- Supports orientation changes.

- All errors are communicated to the user via simple toast messages.


**Internal Workings**

- When the app is launched, the data is fetched initially.

- A title text of "Loading..." is shown when the data is being fetched but the UI is not blocked.

- Data is then displayed on the screen.

- Orientation change does not refresh/clear the data. Thus, state is maintained.

- Debug and release build variants

- Even though the UI is very basic the app has been architected in a way that any new functionality, can be added with ease.

- Pull to refresh is available without blocking the UI.


**Architecture**

- Clean Architecture and SOLID principles have been used with MVVM, Android Architecture Components and use-cases

- DI is provided through Dagger 2

- Data Binding Library has been used.

- There are three main modules - DOMAIN, APP and DATA

- Threading is done via RxJava/RxAndroid.

- Picasso has been used for image downloading/handling

- Each layer has its own defined models. They might look similar across layers but the aim was to independently define each layer and to maintain decoupling.

- Information moves between each layer after conversion through Mappers (defined in DATA and APP layers)

- A few unit tests have been written in each layer and most classes are documented.

- At the time of writing this README there are 16 test-cases in total.

- A few TODOs have been added in code for improvements in case time permits.


**DOMAIN**

- A pure Kotlin layer which has no understanding of Android

- This layer handles the business logic of the application.

- Each of the smallest logical piece of business logic is handled by a use-case.

- The threading is also handled within the use-cases.

- All remote interactions are handled via RemoteRepository interface.

- Observer threads is also defined via an interface.

- None of the other modules are visible to this layer.


**DATA**

- A pure Kotlin layer whose only responsibility is to implement RemoteRepository contract.

- Data is fetched from the remote API via Retrofit API.

- It is then mapped into models as understood by RemoteRepository and returned via Observable streams.

- Only DOMAIN module is visible to this layer.


**APP**

- A Android specific layer whose major responsibility is to show the data which is requested from DOMAIN.

- This layer only uses DOMAIN for its workings.

- DATA is also included as a project but only to satisfy the DI within DATA layer.

- The data interaction from view model to view happens via LiveData objects.

- There is NO reference to the view in the view model.

- Data is requested from DOMAIN Layer. Once it is received, the data is mapped UI models, which are then fed into the views.


**Notes**

- The application code is future ready. Additional features (UI and non-UI) can be added with ease.


**Branching**

- master





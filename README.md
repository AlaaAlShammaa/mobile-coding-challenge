# Matic Mobile Coding Challenge

#### App Arch
Based on mvvm architecture and repository pattern.

#### App Packages
* <b>data</b> - contains
    * <b>api</b> - contains the api classes to make api calls to github api server, using Retrofit.
    * <b>repository</b> - contains the repository classes, responsible for triggering api requests.
* <b>di</b> - contains dependency injection classes, using Dagger2.
* <b>ui</b> - contains classes needed to display Activity.
* <b>util</b> - contains helper classes.


#### App Specs
* Minimum SDK 16
* MVVM Architecture
* Android Architecture Components (LiveData, Lifecycle, ViewModel, ConstraintLayout)
* [RxJava2](https://github.com/ReactiveX/RxJava) for implementing Observable pattern.
* [Dagger 2](https://google.github.io/dagger/) for dependency injection.
* [Retrofit 2](https://square.github.io/retrofit/) for API integration.
* [Gson](https://github.com/google/gson) for serialisation.
* [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
* [Mockito](https://site.mockito.org/) for implementing unit test cases
* [Picasso](http://square.github.io/picasso/) for image loading.
# SuperHeroApp

[![CircleCI](https://circleci.com/gh/rwnhrmwn23/SuperHeroApp.svg?style=svg)](https://app.circleci.com/pipelines/github/rwnhrmwn23/SuperHeroApp)

Welcome to my capstone project for the Dicoding class ["Menjadi Android Developer Expert"](https://www.dicoding.com/academies/165)! This app showcases a comprehensive implementation of modern Android development techniques, ensuring high-quality, maintainable, and secure code. Dive into the features and technologies that power this app below.

## About 

SuperHeroApp is an Android application that allows users to search for superheroes, view detailed information, and save their favorite heroes. The data is sourced from the [SuperHero API](https://www.superheroapi.com/), providing extensive details on each superhero.

## Screenshots

![SuperHeroApp](https://github.com/rwnhrmwn23/SuperHeroApp/assets/25237512/30de34cb-ca06-4583-8891-06c38026cf7a)

### Features
- **Search Superheroes**: Easily search for your favorite superheroes by name.
- **View Details**: Access detailed information about each superhero, including:
  - Biography
  - Appearance
  - Powerstats
  - Additional Info
- **Favorite List**: Save your favorite superheroes to a list for quick access.

## Highlights

| Feature                         | Description                                                                                         |
|---------------------------------|-----------------------------------------------------------------------------------------------------|
| **Kotlin**                       | Leverages Kotlin's modern language features for concise and expressive code.|
| **Clean Architecture**          | Organized into multiple layers (data, domain, presentation) for enhanced maintainability and testability. |
| **MVVM Pattern**                | Utilizes the Model-View-ViewModel architecture to manage UI-related data efficiently and reactively. |
| **Continuous Integration**      | Streamlined development and deployment process using [CircleCI](https://circleci.com/) for automated testing and integration. |
| **Dependency Injection with Koin** | Simplifies dependency management, making the code modular and easily testable ([Koin](https://insert-koin.io/)). |
| **Single Activity Architecture** | Manages navigation through fragments using [Navigation Component](https://developer.android.com/guide/navigation) for a seamless user experience. |
| **Room Database**               | Leverages [Room](https://developer.android.com/training/data-storage/room) for robust and efficient local data storage. |
| **ViewBinding**                 | Facilitates easy view binding with generated binding classes for each XML layout ([ViewBinding](https://developer.android.com/topic/libraries/view-binding)). |
| **Kotlin Coroutines**           | Simplifies asynchronous programming with concise, sequential code ([Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)). |
| **Retrofit for Networking**     | Performs reliable API network requests using [Retrofit](https://square.github.io/retrofit/). |
| **Memory Leak Prevention**      | Implements best practices and tools like [LeakCanary](https://square.github.io/leakcanary/) to detect and fix memory leaks. |
| **Code Obfuscation**            | Protects the app from reverse engineering with [ProGuard](https://www.guardsquare.com/en/products/proguard) and [R8](https://developer.android.com/studio/build/shrink-code). |
| **SSL Pinning**                 | Ensures secure network communications by enforcing SSL certificates ([SSL Pinning](https://square.github.io/okhttp/features/security/)). |
| **Database Encryption**         | Secures sensitive data with [SQLCipher for Android](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/), encrypting the local database. |

## Thanks to

This project represents my dedication to mastering advanced Android development techniques and delivering high-quality applications. Explore the code, experiment with the features, and enjoy the journey through modern Android development!

I want to extend my gratitude to [Dicoding](https://www.dicoding.com) for the class that made this project possible. You can view my certification of completion [here](https://www.dicoding.com/certificates/98XWK5E5LXM3).

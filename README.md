# Recipe Book [Under construction]

This is a "simple" personal project that provides a small collection of recipes and their details. 

The whole project is a case that prioritize a scalable architecture, following good design principles, intented to be a  codebase that could be maintained by a large number of collaborators/teams. Also, some new approachs may be used as study purpose.


## Architecture

Some buzzwords that summarizes the architecture pillars that the app follows:

* MVVM
* Clean architecture
* Dependency inversion
* Dependency injection

### Application layers

1. App
2. Feature
3. Core
4. Utility

### Feature layers

For features, I'm following clean architecture with the corresponding layers:
1. View
2. Presentation
3. Domain
4. Data

[clean_feature.png]

## Modularization
//TODO
[modules_structure.png]

## Code

### Code style
//TODO

### Resources naming convention
//TODO

### Classes naming convention
//TODO

## Tooling

### Koin (DI)
//TODO

### Crash report
//TODO

### Event report
//TODO

## Build Config

### Gradle files

#### Kotlin DSL
For affinity, I'm using kotlin DSL on all gradle files [ref:link]

#### Sync dependency versions
To synchronize dependency versions and avoid string duplications, I'm using a common file that declares dependency versions  as constant referencing them inside build.gradle files.  [ref:link]

#### Reuse build.gradle
In general, features modules have a lot of common dependency. Said that, I'm reusing a base build.gradle files whenever is possible.

#### Proguard
Applying obfuscation and shirink code is a must to build the release version.

## CI/CD

### Framework
I'm using Github actions for CI. Currently, it's still pretty basic...

### Secrets
//TODO

## Authors

* **Cesar Morigaki**

## License

//TODO

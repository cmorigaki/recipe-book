# Recipe Book - My pet project 👷‍

This app provides a small collection of recipes and their details. Also there is with a few possible settings configuration.

This project is intended to be a case that prioritize a scalable architecture, following good design principles, a codebase that could be maintained by a large number of collaborators/teams. Also, some new approachs may be used as study purpose.

## Table of contents
-   [Features](https://github.com/cmorigaki/recipe-book/#Features)
-   [Architecture](https://github.com/cmorigaki/recipe-book/#Architecture)
-   [Application layers](https://github.com/cmorigaki/recipe-book/#Application layers)
-   [Feature layers](https://github.com/cmorigaki/recipe-book/#Feature layers)

## Tech stack
- // TODO not sure if it's necessary

## Architecture
As we're using 100% kotlin, the project rely all the data flow using kotlin coroutines and flow for reactive.

### Modularization

### Feature layers
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
```
The MIT License (MIT)

Copyright (c) 2020 Cesar Augusto Morigaki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

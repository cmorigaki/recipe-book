# Recipe Book - Forever Work In Progress 👷‍
<a href="https://play.google.com/store/apps/details?id=br.com.recipebook"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height=60px /></a>
<img src="misc/screenshots/list_dark.png" width="336" align="right" hspace="20">
My app is a cookbook containing recipes that we frequently make in my house.

This project is developed in order to create a scalable architecture, with good design principles that could be maintained by a large number of collaborators/teams. Additionally, newer libraries or approaches can be used for experimentation.

## Table of contents
-   [Tech stack](https://github.com/cmorigaki/recipe-book/#tech-stack)
-   [Architecture](https://github.com/cmorigaki/recipe-book/#architecture)
-   [Modularization](https://github.com/cmorigaki/recipe-book/#modularization)
-   [Code](https://github.com/cmorigaki/recipe-book/#code)
-   [Build Config](https://github.com/cmorigaki/recipe-book/#build-config)
-   [CI/CD](https://github.com/cmorigaki/recipe-book/#cicd)

## Tech stack
- 100% kotlin
- coroutines
- StateFlow (instead of LiveData)
- Jetpack ViewModel
- Jetpack Compose
- Koin
- Retrofit
- Moshi
- Timber
- Gradle Groovy
- Github actions

## Architecture
SOLID and clean architecture are the pillars.

### Features
In a feature scope (a screen for this project), the architecture relies on 5 distinct layers: View, Presentation, Domain, Data, Data source. These layers follows a **Clean architecture** dependency that can be represented by the below picture:
<p align="center">
    <img src="misc/modeling/feature_clean_arch.png" hspace="20">
</p>

For "view architecture" I'm using MVVM (Jetpack ViewModel), kotlin StateFlow to provide state, and Compose.

### Data flow
Since I'm using 100% kotlin, any asynchronous operation or background work uses kotlin coroutines and Flow when a reactive approach is necessary.
For any SDK integration, I would wrap them using suspendCoroutine/channelFlow to give them a suspend function abstraction over the API calls.

### Navigation
There are some solutions for navigation that have huge impact by modularization.
Today, I have a MainNavigator interface that receives an object that relates to a given screen. The injected list of Navigation resolvers are provided using Koin "multibinding".
<p align="center">
    <img src="misc/modeling/navigation.png" hspace="20">
</p>

### Theme
The application has dark and light mode that can be changed at runtime. All definitions/styles are inside design-system module.
| Light | Dark |
|---|---|
| <img src="misc/screenshots/list_light.png"> | <img src="misc/screenshots/list_dark.png"> |

### DI Framework
Koin

## Modularization
The whole application is composed of several modules that are ruled by a hierarchy dependency structure. All modules are classified into a specific module layer and this layer must respect the dependency direction, this is, a given module can only depend on modules of the same layer or below.

### Modules layers
The picture shows the current modules and how they are structured.
<p align="center">
    <img src="misc/modeling/modularization.png" hspace="20">
</p>

1. **App** - Glue all modules and it has project configurations like build variants, API keys.
2. **Feature** - Product features are developed at this level of modules. For this project I have modules:screen 1:1 but it may vary a lot.
3. **Core** - Here I have modules that are not tied to a specific feature scope but the entire app like Base Classes when needed (I am pretty confident that I keep this layer really small).
4. **Infrastructure** - Modules that compose the foundation of the project. Configuration, navigation, analytics, design-system...
4. **Utility** - Helpers and extensions classes goes here. But only the ones that are not related to business of the project and it can be reused by other projects.

## Quality

### Code style and code smell
[Detekt](https://detekt.github.io/detekt/) + [ktlint formatting](https://ktlint.github.io/)

### Strict Mode
[Strict Mode](https://developer.android.com/reference/android/os/StrictMode) is a developer tool which detects things you might be doing by accident and brings them to your attention so you can fix them.

### Resources naming convention
//TODO

### Classes naming convention
//TODO

## Analytics + Monitoring

### Crash report and logs
Currently I'm using [Sentry](https://sentry.io) for crash report and [Timber](https://github.com/JakeWharton/timber) as log abstraction layer.
Monitoring must be the first thing to initialize!

### Event analytics
The module `analytics` is an abstraction for event report. We currently rely on Amplitude platform to send and analyse user/application behaviour.

## Build Config

### Gradle files

#### ~~Kotlin DSL~~
I tried to use gradle kotlin DSL but I've encountered errors when splitting build files for modules reuse.

#### Sync dependency versions
To synchronize dependency versions and avoid string duplications, I'm using a common file that declares dependency versions as constants referencing them inside build.gradle files. [ref:link]

#### Reuse build.gradle
In general, features modules have a lot of common dependency. Said that, I'm reusing a base build.gradle files whenever is possible.

#### Proguard
Applying obfuscation and shrink code is a must for the release build.

## CI/CD

### Framework
I'm using Github actions for CI. Currently, it's still pretty basic...

### Secrets
//TODO

## Others
[Variant ribbon](https://github.com/usefulness/easylauncher-gradle-plugin)
<p>
    <img src="misc/variant_ribbon.png" hspace="20"/>
</p>

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

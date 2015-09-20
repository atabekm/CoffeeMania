# CoffeeMania

CoffeeMania is a sample Android app to find coffeeshops near your location. It will show the list
of coffeeshops, and each list item will show how far the venue is located from you, venue name
and its address. Upon clicking any list item, details of the venue will be displayed, such as
address, url, phone number, and its location on Google Maps widget.

## Development approach

The app was developed using two-pane layout, so that it will be optimised for using on both
phones and tablets. The `MainFragment` will show the `ListView` with venues, and upon clicking any of
the items `DetailFragment` will be opened with details.

Venue details are retrieved from Foursquare API, to be specific Venues Search API was utilized. From
JSON response, following data are taken to be used in the app:

1. Venue name
2. Venue location latitude;
3. Venue location longitude;
4. Venue's distance to current location
5. Venue address
6. Venue category
7. Venue total checkins count
8. Venue user checkins count
9. Venue total tips count
10. Venue phone number, if exists
11. Venue URL, if exists

`Play Services Location API (FusedLocationApi)` was used to get and update the location. If the app
is open, the location will be updated every 5 minutes, and accordingly, the venues list will be
updated as well.

In `DetailFragment`, if a venue has a phone number or URL, it will be 'clickable', i.e. clicking
phone number will open Dialer, and clicking URL will open default browser.

Also, `MapView` from `Play Services Map API` was utilized to show the location of the venue in
`DetailFragment`.

### Development environment

On following environment the app was developed:

* OS: OS X Yosemite 10.10.5
* IDE: Android Studio 1.3.2
* Java: 1.6.0_65
* Dependency management: Gradle 1.3.0

### Used external libraries
* `Google Volley` to communicate with APIs
* `Robotium` to implement UI testing

## Assumptions made

The following assumptions were made to develop the app:

* Target devices have Location, Wifi, and/or network enabled to get the location
* No constraints on battery consumption (the app uses High accuracy priority, which could drain the
battery faster than usual)

## Building/running the app
### With Gradle/adb

If you want to build the app from source code, first set `ANDROID_HOME` environment variable to your
current Android SDK location, for example:

    export ANDROID_HOME=/Users/qwerty/Library/Android/sdk

Next, execute the followings:

    git clone git@github.com:atabekm/CoffeeMania.git
    cd CoffeeMania/
    ./gradlew build

If your build is successful, you can install newly generated apk file to your device by running
the followings (your `adb` file should be located inside platform-tools folder of `ANDROID_HOME`):

    adb install -r app/build/outputs/apk/app-debug.apk

### With Android Studio
1. Check out project from Version Control -> GitHub -> Login with your GitHub account
2. Paste URI from clipboard to `Git Repository URL`, check `Parent Directory` and `Directory Name`,
click **Clone**
3. Just to build, select `Build -> Make Project` from menu
4. Or to run, select `Run -> Run 'app'` from menu

## Testing the app

Basic set of Unit and Instrumentation test have been implemented. In ideal case, both types of the
test should be located in separate directories/files, however, due to the time limitation, Unit
tests have been added to Instrumentation test suite.

### With Gradle

If you want to run the tests of the app from source code, first set `ANDROID_HOME` environment
variable to your current Android SDK location, for example (you can skip all the commands till the
last one, if you have already the source code):

    export ANDROID_HOME=/Users/qwerty/Library/Android/sdk

Next, execute the followings:

    git clone git@github.com:atabekm/CoffeeMania.git
    cd CoffeeMania/
    ./gradlew connectedAndroidTest --info

### With Android Studio

To run the test from Android Studio, execute following steps (skip first two steps, if your project
has been already opened in Android Studio):

1. Check out project from Version Control -> GitHub -> Login with your GitHub account
2. Paste URI from clipboard to `Git Repository URL`, check `Parent Directory` and `Directory Name`,
click **Clone**
3. On Project window on the top left, expand the directories, and find `UITest` class
4. Tap with two fingers on `UITest` (right click on PC)
5. From opened context menu, select `Run 'UITest'`

## Screenshots
### Phone

![device-2015-09-20-161025](https://cloud.githubusercontent.com/assets/8268042/9980049/6745beb2-5fcd-11e5-9dd9-a9197de023be.jpg)
![phone2](https://cloud.githubusercontent.com/assets/8268042/9980050/70a6c7c6-5fcd-11e5-979b-f5217f8e1baf.jpg)

### Tablet

![device-2015-09-20-160832](https://cloud.githubusercontent.com/assets/8268042/9980048/674419fe-5fcd-11e5-9d1c-1af2cba6f514.jpg)

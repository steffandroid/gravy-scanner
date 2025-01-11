# Gravy Scanner
Android app that reveals installed apps which may be leaking your location data.

## Rationale
Wired published a [story](https://www.wired.com/story/gravy-location-data-app-leak-rtb/) which revealed that thousands of apps are potentially leaking location data to a data broker named Gravy Analytics. A [full list of apps](https://docs.google.com/spreadsheets/d/1Ukgd0gIWd9gpV6bOx2pcSHsVO6yIUqbjnlM4ewjO6Cs/edit?usp=sharing) is provided in the story, but is in spreadsheet form and contains over 12,000 entries.

Gravy Scanner takes a copy of this spreadsheet and compares it against all the applications installed on your Android device. Any matches are displayed in a list, and can be clicked to go through to the app info. From here you can easily review location permission settings, or uninstall.

## Install
Install the most recent APK [here](https://github.com/steffandroid/gravy-scanner/releases/latest/download/app-release.apk).

# HugeCurrencyTest

### Currency Calculator usigin Dagger 2 + MVP + Retrofit 2 + Rx + Realm Example

This app is made as an example of how to use Dagger 2 together with MVP. The app also uses Realm and Retrofit 2 in combination with RxJava. You are welcome to check it out and use the code. The code contains some comments of some encountered problems during the development.

The application consists of three screens:

 - Currency calculator : This screen allows you to calculate convertion to another currency and vice versa.
 - Exchange rates : Displays the current exchange rates from the HNB API. For every currency buying rate, median rate and selling rate are shown. (Developing the start of the project i start using fixer.io but i encounter some problems: using a API KEY, got registered, etc etc and the fetch dont know why, but was wrong, so i move to another api HBN http://hnbex.eu/api/v1/ which is free and dont need registration.)
 - Currency stats : Statistical preview for last 7 days of a currency exchange rate.


## The package strucutre explained:

 - adapters : adapter classes
 - internals : dagger and mvp stuff
 - networking : retrofit apis
 - realm : all the realm object classes
 - support : all the utils, helpers, managers and everything else that I think is a support class. You can also divide it into several package names.
 - ui : I normally use the ui package for dialogs, activities, fragmetns. Inside the package I usually create an additional package name for every major feature of the app.


### TODO

Write tests
Write documentation
Fix slow initial start time of the app
Fix a bug with the convertion. Still on that dont have more time but i will check it then. :(

### Additional libs used

Butterknife
MPAndroidChart
JodaTimeAndroid
RxJava
AndroidX: Android Jetpack UI migration.


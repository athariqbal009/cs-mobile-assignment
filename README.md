# Mobile Assignment CS - MovieBox
MovieBox listed latested and polular movies. Consuming the `API` from `TMDB`. It includes the description about movies i.e Movie poster, release date, rating, overview etc.

_**Try latest apk 👇**_

[![MovieBox](https://img.shields.io/badge/MovieBox-APK-black.svg?style=for-the-badge&logo=android)](https://gitlab.com/athariqbal009/cs-mobile-assignment/uploads/d5a91ab552b089721a6ef50cf8ebd930/app-release.apk)

SplashScreen | HomeScreen | DetailScreen
--- | --- | --- |
![](https://gitlab.com/athariqbal009/cs-mobile-assignment/uploads/ab51ee44e082359e0753f93e92ec6624/Screenshot_2021-06-22-11-52-01-83_a35e1787587fb8fa24c2e255ba40229b_1_.jpg) | ![](https://gitlab.com/athariqbal009/cs-mobile-assignment/uploads/ca36c4c6b2e23f8b329ed906d461bd3e/Screenshot_2021-06-22-11-52-09-18_a35e1787587fb8fa24c2e255ba40229b_1_.jpg) | ![](https://gitlab.com/athariqbal009/cs-mobile-assignment/uploads/d92b1f321e4fd488503d5fab9cc8ba4b/Screenshot_2021-06-22-11-52-14-40_a35e1787587fb8fa24c2e255ba40229b_1_.jpg)

## App Overview & Functionalities
MovieBox consist of three activities.
1. SplashScreen: This is the launcher activity. When the app lauch it will show the app name. This screen only show the app name. holding the activity for ~3ms move to the HomeScreen activity. for this delay here using the Handler class.
2. HomeScreen: This screen listed movie in two categories.
   * Playing Now: In this we can only display the movie poster scrollable horizontally. In this screen for Horizontally displaying data `now_playing` API will be consume.
   * Most Popular: In this we display the movies vertically Scrollable by using `popular` API and displaying the following details:
     * Movie Poster
     * Title
     * Release Date
     * Time ~ this data is not available in the `API` response
     * Rating
3. DetailScreen: In this we can show the detail of the movie by passing the movie id and consuming the `API` to get the response. This screen showing the following details:
   * Movie Poster
   * Title
   * Release Date
   * Time ~ this data is not available in the `API` response
   * Overview
   * Genres

## Architecture & Dependencies
This app made up of using the `MVVM` architecture with following dependencies:
1. Android lifecycle: ViewModel, LiveData 
2. Network Library: For consuming API and received data from server `Retrofit` will be used. It is ease of use, fast, internally response parsing using `GSON`.
3. Image Library: For movie poster loading am using `Coil` kotlin first image library. It will automatically handle image caching and memory.
4. Rating View: For this view am using `ProgressBar` by applying the drawable xmls to display the rating.
5. Themes: Are used to maintain the consistency through out the app. 

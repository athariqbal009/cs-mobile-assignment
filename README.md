# Mobile Assignment CS - MovieBox
MovieBox listed latested and polular movies. Consuming the `API` from `TMDB`. It includes the description about movies i.e Movie poster, release date, rating, overview etc.

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

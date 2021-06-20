# Mobile Assignment CS - MovieBox
MovieBox listed latested and polular movies. Consuming the `API` from `TMDB`. It includes the description about movies i.e Movie poster, release date, rating, overview etc.

## App Overview
MovieBox consist of three activities.
1. SplashScreen: This is the launcher activity. When the app lauch it will show the app name.
2. HomeScreen: This screen listed movie in two categories.
 * Playing Now: In this we can only display the movie poster horizontally. it is scrollable.
 * Most Popular: In this we display the movies vertically with the following details:
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

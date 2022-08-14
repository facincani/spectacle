package ada.spectacle.service

import ada.spectacle.domain.Movie

interface MovieService {

    fun getAll(): List<Movie>

    fun getMovies(userId: Int): List<Movie>
    fun addToMoviesList(userId: Int, movie: Movie)

}
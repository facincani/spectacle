package ada.spectacle.service.impl

import ada.spectacle.domain.Movie
import ada.spectacle.domain.toMovieEntity
import ada.spectacle.entity.toMovie
import ada.spectacle.repository.MovieRepository
import ada.spectacle.repository.UserRepository
import ada.spectacle.service.MovieService
import org.springframework.stereotype.Component

@Component
class MovieServiceImpl(val userRepository: UserRepository, val movieRepository: MovieRepository): MovieService {
    override fun getAll(): List<Movie> {
        return movieRepository.findAll().map { it.toMovie() }
    }

    override fun getMovies(userId: Int): List<Movie> {
        return userRepository.findById(userId).get().movies!!.map { it.toMovie() }
    }

    override fun addToMoviesList(userId: Int, movie: Movie) {
        movieRepository.findByName(movie.name).ifPresent {
            movie.id = it.id
        }
        userRepository.findById(userId).ifPresent {
            it.movies?.add(movie.toMovieEntity())
            userRepository.save(it)
        }
    }
}
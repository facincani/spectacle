package ada.spectacle.domain

import ada.spectacle.entity.MovieEntity

data class Movie(
        var id: Int,
        val name: String,
        val category: String,
        val posterUri: String
)

fun Movie.toMovieEntity() = MovieEntity(id, name, category, posterUri)
package ada.spectacle.entity

import ada.spectacle.domain.Movie
import javax.persistence.*

@Entity(name = "Movie")
data class MovieEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Column(length=250,unique = true)
        val name: String,
        val category: String,
        val posterUri: String
)

fun MovieEntity.toMovie() = Movie(id, name, category, posterUri)
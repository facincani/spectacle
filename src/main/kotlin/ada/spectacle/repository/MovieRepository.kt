package ada.spectacle.repository

import ada.spectacle.entity.MovieEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MovieRepository: CrudRepository<MovieEntity, Int> {
    fun findByName(name: Any): Optional<MovieEntity>
}
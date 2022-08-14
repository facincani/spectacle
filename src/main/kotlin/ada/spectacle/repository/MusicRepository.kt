package ada.spectacle.repository

import ada.spectacle.entity.MusicEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MusicRepository: CrudRepository<MusicEntity, Int>{

    fun findByName(name: String): Optional<MusicEntity>

}

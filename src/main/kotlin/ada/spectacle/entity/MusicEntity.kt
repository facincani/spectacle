package ada.spectacle.entity

import ada.spectacle.domain.Music
import javax.persistence.*

@Entity(name = "Music")
data class MusicEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val artist: String,
        @Column(length=250, unique = true)
        val name: String,
        val booklet: String,

        )

fun MusicEntity.toMusic() = Music(id, artist, name, booklet)
package ada.spectacle.domain

import ada.spectacle.entity.MusicEntity


data class Music(
        var id: Int,
        val artist: String,
        val name: String,
        val booklet: String
)

fun Music.toMusicEntity() = MusicEntity(id, artist, name, booklet)
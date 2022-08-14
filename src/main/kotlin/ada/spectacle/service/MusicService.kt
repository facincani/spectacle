package ada.spectacle.service

import ada.spectacle.domain.Music

interface MusicService{

    fun getAll(): List<Music>

    fun getMusics(userId: Int): List<Music>

    fun addToPlaylist(userId: Int, music: Music)

}
package ada.spectacle.service.impl

import ada.spectacle.domain.Music
import ada.spectacle.domain.toMusicEntity
import ada.spectacle.entity.toMusic
import ada.spectacle.repository.MusicRepository
import ada.spectacle.repository.UserRepository
import ada.spectacle.service.MusicService
import org.springframework.stereotype.Component

@Component
class MusicServiceImpl(val userRepository: UserRepository, val musicRepository: MusicRepository): MusicService {
    override fun getAll(): List<Music> {
        return musicRepository.findAll().map { it.toMusic() }
    }

    override fun getMusics(userId: Int): List<Music> {
        return userRepository.findById(userId).get().musics!!.map { it.toMusic() }
    }

    override fun addToPlaylist(userId: Int, music: Music) {
        musicRepository.findByName(music.name).ifPresent {
            music.id = it.id
        }
        userRepository.findById(userId).ifPresent {
            it.musics?.add(music.toMusicEntity())
            userRepository.save(it)
        }
    }

}
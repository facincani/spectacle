package ada.spectacle.controller

import ada.spectacle.domain.Music
import ada.spectacle.domain.User
import ada.spectacle.entity.MovieEntity
import ada.spectacle.entity.MusicEntity
import ada.spectacle.repository.MusicRepository
import ada.spectacle.service.MusicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.net.URI
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/music")
class MusicController(val service: MusicService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Music>>{
        return ResponseEntity.ok(service.getAll())
    }


    @GetMapping("/{userId}")
    fun getMusics(@PathVariable("userId") userId: Int): ResponseEntity<List<Music>> {
        return ResponseEntity.ok(service.getMusics(userId))
    }

    @PostMapping("/{userId}")
    fun addToPlaylist(@PathVariable("userId") userId: Int, @RequestBody music: Music):ResponseEntity<String>{
        kotlin.runCatching {
            service.addToPlaylist(userId, music)
        }.onSuccess {
            return ResponseEntity.created(URI("")).build()
        }.onFailure {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }.also {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred")
        }
    }

}
package ada.spectacle.controller

import ada.spectacle.domain.Movie
import ada.spectacle.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/movie")
class MovieController(val service: MovieService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Movie>>{
        service.getAll()
        return ResponseEntity.ok(service.getAll())
    }

    @GetMapping("/{userId}")
    fun getMovies(@PathVariable("userId")  userId: Int): ResponseEntity<List<Movie>> {
        return ResponseEntity.ok(service.getMovies(userId))
    }

    @PostMapping("/{userId}")
    fun addToMoviesList(@PathVariable("userId") userId: Int, @RequestBody movie: Movie):ResponseEntity<String>{

        kotlin.runCatching {
            service.addToMoviesList(userId, movie)
        }.onSuccess {
            return ResponseEntity.created(URI("")).build()
        }.onFailure {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }.also {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred")
        }
    }


}
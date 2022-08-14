package ada.spectacle.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.JoinColumn

import javax.persistence.JoinTable

import javax.persistence.FetchType

import javax.persistence.ManyToMany




@Entity(name = "user")
data class UserEntity (
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
        @Column(length=250,unique = true)
    var login: String,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
        @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(name = "user_music", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "music_id", referencedColumnName = "id")])
    var musics: MutableList<MusicEntity>?,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(name = "user_movie", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "id")])
    val movies: MutableList<MovieEntity>?

)
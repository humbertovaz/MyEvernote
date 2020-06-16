package com.project.evernote.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long? = null,
        val username : String? = null,
        val email: String? = null,
        val state: String? = null,
        var password : String? = null,
        val mobile: String? = null,
        var status: String? = null,
        @ManyToMany(mappedBy = "users")
        val notes: Set<Note>? = null

) : Serializable
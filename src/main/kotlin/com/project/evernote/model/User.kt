package com.project.evernote.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long? = null,
        @Column(nullable = false,length = 10, unique = true)
        val username : String? = null,
        @Column(nullable = false,length = 10, unique = true)
        val email: String? = null,
        val state: String? = null,
        @Column(nullable = false)
        var password : String? = null,
        val mobile: String? = null,
        var status: String? = null,
        @ManyToMany(mappedBy = "users")
        val notes: Set<Note>? = null

) : Serializable
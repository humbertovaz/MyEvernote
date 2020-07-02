package com.project.evernote.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long? = null,
        @Column(nullable = false,length = 255, unique = true)
        var username : String? = null,
        @Column(nullable = false,length = 255, unique = true)
        var email: String? = null,
        val state: String? = null,
        @Column(nullable = false)
        var password : String? = null,
        val mobile: String? = null,
        var status: String? = null,
        @ManyToMany(mappedBy = "users")
        val notes: Set<Note>? = null

) : Serializable
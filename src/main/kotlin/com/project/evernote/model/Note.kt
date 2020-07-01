package com.project.evernote.model

import javax.persistence.*
import java.io.Serializable
import java.util.*


@Entity
@Table (name = "note")
class Note(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        val date: Date? = null,
        @Column(nullable = false, unique = true)
        val name : String? = null,
        var description: String? = null,
        @ManyToMany
        @JoinTable(name="note_user",
                joinColumns = [JoinColumn(name="note_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name="user_id", referencedColumnName = "id")]
        )
        val users: Set<User?>?

) : Serializable
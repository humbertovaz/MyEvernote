package com.project.evernote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EvernoteApplication

fun main(args: Array<String>) {
	runApplication<EvernoteApplication>(*args)
}

package com.example.demo.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import javax.annotation.PostConstruct


@Component
class FileSaver(private val uploadTracer: UploadTracer) {

    @Value("\${files.path}")
    lateinit var filesBasePath: String

    private val logger = LoggerFactory.getLogger(FileSaver::class.java)

    @PostConstruct
    fun init() = logger.info("Files base path is set to: $filesBasePath")

    fun save(file: MultipartFile) {

        logger.debug("Saving file: NAME=${file.originalFilename}, CONTENT_TYPE=${file.contentType}, SIZE=${file.size}")

        // create new file if it does not exist
        val convertedFile = File("$filesBasePath/${file.originalFilename}")
        convertedFile.createNewFile()

        // save file
        logger.debug("Saving file to ${convertedFile.absolutePath}")
        FileOutputStream(convertedFile).apply { write(file.bytes) ; close() }

        // trace uploads
        uploadTracer.trace(file.originalFilename!!, filesBasePath)
    }
}
package com.example.demo.controller

import com.example.demo.service.FileSaver
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping()
class UploadController(private val fileSaver: FileSaver) {

    @PostMapping("FileUpload", consumes = [(MediaType.MULTIPART_FORM_DATA_VALUE)])
    fun save(@RequestParam("file") file: MultipartFile):  ResponseEntity<String> {
        try {
            fileSaver.save(file)
            return ResponseEntity.ok("Arquivo armazenado com sucesso!! ")
            // some code
        } catch (e : Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar armazenar arquivo :(")
        }
    }
}
package com.kitaplik.libraryservice.dto

data class AddBookRequest @JvmOverloads constructor(
        val id: String,
        val isbn: String
){
}
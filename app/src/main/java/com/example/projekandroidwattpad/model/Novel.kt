package com.example.projekandroidwattpad.model

import java.io.Serializable

data class Novel(
    var judulNovel: String? = "",
    var genreNovel: String? = "",
    var coverNovel: String? = "",
    var penulisNovel: String? = "",
    var isiCerita: String? = ""
) : Serializable

package com.alxd.petslist.data

import java.io.Serializable

data class Puppy (
    val id : Int,
    val title : String,
    val description : String,
    val puppyImageId : Int = 0,
    val age : Int = 0
        ) : Serializable
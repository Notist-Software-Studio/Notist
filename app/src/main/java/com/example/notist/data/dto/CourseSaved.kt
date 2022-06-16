package com.example.notist.data.dto

import androidx.room.PrimaryKey

//data type of fetched data
data class CourseSaved(@PrimaryKey var courseId : String="", var class_name: String ="", var major: String="",var remoteUri:String=""){

}

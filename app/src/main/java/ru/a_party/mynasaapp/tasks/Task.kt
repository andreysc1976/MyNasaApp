package ru.a_party.mynasaapp.tasks

import java.util.*

data class Task
    (
    var complete:Boolean,
    val name:String,
    val description:String,
    val date:Date
)
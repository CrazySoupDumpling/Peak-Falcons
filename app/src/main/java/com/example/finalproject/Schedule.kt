package com.example.finalproject

class Schedule constructor(name: String, items: String){
    val name = name
    val items = "get Dressed, leave"

    @Override
    public final override fun toString(): String{

        return name + "," + items
    }
}
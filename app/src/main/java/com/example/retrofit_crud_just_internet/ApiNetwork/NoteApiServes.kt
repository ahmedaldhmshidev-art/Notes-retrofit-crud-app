package com.example.retrofit_crud_just_internet.ApiNetwork

import com.example.retrofit_crud_just_internet.Model.Note
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NoteApiServes {

    @GET ("notes")
    suspend fun getNotes():retrofit2.Response<List<Note>>
    @POST("notes")
    suspend fun addNote(@Body note :Note): retrofit2.Response<Note>
    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") id :String,@Body note :Note): retrofit2.Response<Note>
    @DELETE("notes/{id}")
    suspend fun removeNote(@Path("id") id :String):retrofit2.Response<Unit>

}
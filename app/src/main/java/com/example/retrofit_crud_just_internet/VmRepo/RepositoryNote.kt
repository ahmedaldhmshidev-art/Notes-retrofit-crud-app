package com.example.retrofit_crud_just_internet.VmRepo

import com.example.retrofit_crud_just_internet.ApiNetwork.RetrofitNote
import com.example.retrofit_crud_just_internet.Model.Note

class RepositoryNote
{
    val api = RetrofitNote.api
    suspend fun fetchAllNote():retrofit2.Response<List<Note>>
    {
        return RetrofitNote.api.getNotes()
    }
    suspend fun addNewNote(note : Note):retrofit2.Response<Note>
    {
        return api.addNote(note)
    }
    suspend fun updateNote(id:String , note: Note):retrofit2.Response<Note>
    {
        return api.updateNote(id, note)
    }
    suspend fun removedNote(id : String):retrofit2.Response<Unit>
    {
        return api.removeNote(id)
    }

}
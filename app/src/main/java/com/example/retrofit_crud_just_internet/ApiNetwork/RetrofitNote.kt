package com.example.retrofit_crud_just_internet.ApiNetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitNote {
    val uRl = "http://68e96992f1eeb3f856e3f81c.mockapi.io/"

//     lazy تنشي الكائن من اجل جلب البيانات مع lazy تنشي الكائن مره واحدة من بعدها تجلب بيانات
    val api: NoteApiServes by lazy {
        Retrofit.Builder()
            .baseUrl(uRl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApiServes::class.java)
    }
}

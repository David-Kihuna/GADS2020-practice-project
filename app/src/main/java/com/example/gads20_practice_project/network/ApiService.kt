package com.example.gads20_practice_project.network

import com.example.gads20_practice_project.network.models.LearningResponse
import com.example.gads20_practice_project.network.models.SkillResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://gadsapi.herokuapp.com"
private const val BASE_URL_SUBMIT = "https://docs.google.com/forms/d/e/"

interface ApiService {
    @GET("/api/hours")
    suspend fun getLearningLeaders() : List<LearningResponse>

    @GET("/api/skilliq")
    suspend fun getSkillIQLeaders() : List<SkillResponse>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submit(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email:String,
        @Field("entry.284483984") linkToProject: String
        ): Call<Void>

}

object Api {
    val retrofitService : ApiService by lazy {
        createNetworkClient(
            BASE_URL
        )
            .create(ApiService::class.java) }
}

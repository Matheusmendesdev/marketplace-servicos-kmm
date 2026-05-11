package com.conectaservicos.api

import com.conectaservicos.model.Login
import com.conectaservicos.model.LoginResponse
import com.conectaservicos.model.Service
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ConectaApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    private val BASE_URL = "http://10.0.2.2:8080/v1";

    suspend fun fetchServices(): List<Service> {
        return httpClient.get("$BASE_URL/services").body()
    }

    suspend fun fetchLogin(login: Login): LoginResponse {
        return httpClient.post("$BASE_URL/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(login)
        }.body()
    }

}
package uz.isheraliyev.core.data.di

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import sp.bvantur.inspektify.ktor.InspektifyKtor
import uz.isheraliyev.core.data.local.Constants.BASE_URL
import uz.isheraliyev.core.data.manager.TokenManager
import uz.isheraliyev.core.data.service.RefreshApiService


object Network {
    val module = module {
        singleOf(::createHttpClient)
        singleOf(::TokenManager)
        singleOf(::RefreshApiService)
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(tokenManager: TokenManager): HttpClient {
    return HttpClient(Android) {
        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
        }

        install(InspektifyKtor)

        install(Auth) {
            bearer {
                loadTokens { tokenManager.loadTokens() }
                refreshTokens { tokenManager.refreshTokens() }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }
}
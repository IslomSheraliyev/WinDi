package uz.isheraliyev.core.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import sp.bvantur.inspektify.ktor.InspektifyKtor
import uz.isheraliyev.core.data.exception.safeApiCall
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.data.local.Constants.CHECK_JWT
import uz.isheraliyev.core.data.local.Constants.REFRESH_TOKEN
import uz.isheraliyev.core.data.request.RefreshTokenRequest
import uz.isheraliyev.core.data.response.RefreshTokenResponse
import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result

class RefreshApiService {
    @OptIn(ExperimentalSerializationApi::class)
    private val ktor = HttpClient(Android) {
        install(InspektifyKtor)

        defaultRequest {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Accept, ContentType.Application.Json)
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

    suspend fun validateAndLoadTokens(): Result<BearerTokens, DataError.Network> = safeApiCall {
        val accessToken = AppPreferences.accessToken
        val refreshToken = AppPreferences.refreshToken

        if (accessToken.isEmpty() || refreshToken.isEmpty()) return Result.Error(DataError.Network.UNAUTHORIZED_ERROR)

        return try {
            val response = ktor.get(CHECK_JWT) { header(HttpHeaders.Authorization, "Bearer $accessToken") }
            if (response.status == HttpStatusCode.OK) Result.Success(BearerTokens(accessToken, refreshToken))
            else Result.Error(DataError.Network.UNAUTHORIZED_ERROR)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNAUTHORIZED_ERROR)
        }
    }

    suspend fun refreshAccessToken(): Result<BearerTokens, DataError.Network> = safeApiCall {
        val refreshToken = AppPreferences.refreshToken

        if (refreshToken.isEmpty()) return Result.Error(DataError.Network.UNAUTHORIZED_ERROR)

        return try {
            val response: HttpResponse = ktor.post(REFRESH_TOKEN) {
                setBody(RefreshTokenRequest(refreshToken))
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                val newTokens: RefreshTokenResponse = response.body()

                AppPreferences.accessToken = newTokens.access_token
                AppPreferences.refreshToken = newTokens.refresh_token

                Result.Success(
                    BearerTokens(
                        accessToken = newTokens.access_token,
                        refreshToken = newTokens.refresh_token
                    )
                )
            } else Result.Error(DataError.Network.UNAUTHORIZED_ERROR)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNAUTHORIZED_ERROR)
        }
    }
}
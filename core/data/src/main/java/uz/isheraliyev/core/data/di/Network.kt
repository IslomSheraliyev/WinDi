package uz.isheraliyev.core.data.di

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import sp.bvantur.inspektify.ktor.InspektifyKtor
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.data.local.Constants.BASE_URL
import uz.isheraliyev.core.data.local.Constants.REFRESH_TOKEN
import uz.isheraliyev.core.data.request.RefreshTokenRequest
import uz.isheraliyev.core.data.response.RefreshTokenResponse

object Network {
    @OptIn(ExperimentalSerializationApi::class)
    val module = module {
        single {
            HttpClient(Android) {
                defaultRequest {
                    url(BASE_URL)
                    contentType(ContentType.Application.Json)
                }

                install(InspektifyKtor)

                install(Auth) {
                    bearer {
                        loadTokens {
                            if (
                                AppPreferences.accessToken.isNotEmpty() &&
                                AppPreferences.accessToken.isNotEmpty()
                            ) BearerTokens(
                                accessToken = AppPreferences.accessToken,
                                refreshToken = AppPreferences.accessToken,
                            ) else null
                        }
                        refreshTokens {
                            val refreshToken = AppPreferences.refreshToken

                            if (refreshToken.isEmpty()) return@refreshTokens null

                            try {
                                val response: HttpResponse = client.post(REFRESH_TOKEN) {
                                    setBody(RefreshTokenRequest(refreshToken))
                                    contentType(ContentType.Application.Json)
                                }

                                if (response.status == HttpStatusCode.OK) {
                                    val newTokens: RefreshTokenResponse = response.body()

                                    AppPreferences.accessToken = newTokens.access_token
                                    AppPreferences.refreshToken = newTokens.refresh_token
                                    AppPreferences.isDeviceRegistered = true

                                    BearerTokens(
                                        accessToken = newTokens.access_token,
                                        refreshToken = newTokens.refresh_token
                                    )
                                } else null
                            } catch (e: Exception) {
                                null
                            }
                        }
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
    }
}
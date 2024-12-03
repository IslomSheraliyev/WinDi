package uz.isheraliyev.feature.auth.data.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import uz.isheraliyev.core.data.exception.safeApiCall
import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.auth.data.request.CheckAuthCodeRequest
import uz.isheraliyev.feature.auth.data.request.SendAuthCodeRequest
import uz.isheraliyev.feature.auth.data.response.CheckAuthCodeResponse
import uz.isheraliyev.feature.auth.data.response.SendAuthCodeResponse
import uz.isheraliyev.feature.auth.data.url.AuthUrl

class AuthApiService(
    private val ktor: HttpClient
) {
    suspend fun sendAuthCode(body: SendAuthCodeRequest): Result<SendAuthCodeResponse, DataError.Network> =
        safeApiCall {
            ktor.post(AuthUrl.SEND_AUTH_CODE) {
                setBody(body)
            }.body()
        }

    suspend fun checkAuthCode(body: CheckAuthCodeRequest): Result<CheckAuthCodeResponse, DataError.Network> =
        safeApiCall {
            ktor.post(AuthUrl.CHECK_AUTH_CODE) {
                setBody(body)
                header(HttpHeaders.Authorization, null)
            }.body()
        }
}
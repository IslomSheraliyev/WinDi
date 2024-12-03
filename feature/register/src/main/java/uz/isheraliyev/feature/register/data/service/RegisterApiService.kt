package uz.isheraliyev.feature.register.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import uz.isheraliyev.core.data.exception.safeApiCall
import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.register.data.request.RegisterRequest
import uz.isheraliyev.feature.register.data.response.RegisterResponse
import uz.isheraliyev.feature.register.data.url.RegisterUrl

class RegisterApiService(
    private val ktor: HttpClient
) {
    suspend fun register(body: RegisterRequest): Result<RegisterResponse, DataError.Network> =
        safeApiCall {
            ktor.post(RegisterUrl.REGISTER) { setBody(body) }.body()
        }
}
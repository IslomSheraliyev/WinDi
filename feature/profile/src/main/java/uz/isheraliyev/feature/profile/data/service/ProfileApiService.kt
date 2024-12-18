package uz.isheraliyev.feature.profile.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import uz.isheraliyev.core.data.exception.safeApiCall
import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.data.request.PostMeRequest
import uz.isheraliyev.feature.profile.data.response.GetMeResponse
import uz.isheraliyev.feature.profile.data.url.ProfileUrl

class ProfileApiService(
    private val ktor: HttpClient
) {
    suspend fun getMe(): Result<GetMeResponse, DataError.Network> = safeApiCall {
        ktor.get(ProfileUrl.ME).body()
    }

    suspend fun postMe(body: PostMeRequest): Result<Unit, DataError.Network> = safeApiCall {
        ktor.post(ProfileUrl.ME) { setBody(body) }.body()
    }
}
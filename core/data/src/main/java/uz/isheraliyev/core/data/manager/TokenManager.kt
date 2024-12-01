package uz.isheraliyev.core.data.manager

import io.ktor.client.plugins.auth.providers.BearerTokens
import uz.isheraliyev.core.data.service.RefreshApiService
import uz.isheraliyev.core.domain.error.Result

class TokenManager(private val refreshApiService: RefreshApiService) {
    suspend fun loadTokens(): BearerTokens? {
        return when (val result = refreshApiService.validateAndLoadTokens()) {
            is Result.Error -> null
            is Result.Success -> result.data
        }
    }

    suspend fun refreshTokens(): BearerTokens? {
        return when (val result = refreshApiService.refreshAccessToken()) {
            is Result.Error -> null
            is Result.Success -> result.data
        }
    }
}
package uz.isheraliyev.core.domain.error

sealed interface DataError : Error {
    enum class Network : DataError {
        UNAUTHORIZED_ERROR,
        REDIRECT_RESPONSE_ERROR,
        CLIENT_REQUEST_ERROR,
        SERVER_RESPONSE_ERROR,
        NO_INTERNET_ERROR,
        SERIALIZATION_ERROR,
        SOCKET_TIME_OUT_ERROR,
        UNKNOWN_ERROR
    }
}
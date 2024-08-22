package domain.utils

enum class LocalError : Error {
    USER_NOT_FOUND,
    USER_NOT_SAVED
}

enum class WalletError : Error {
    NO_WALLET_FOUND
}
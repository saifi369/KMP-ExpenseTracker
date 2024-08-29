package presentation.screen.home

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import domain.model.Wallet
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object WalletNavType {
    val walletType = object : NavType<Wallet>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Wallet? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Wallet {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: Wallet): String {
            return Json.encodeToString(value)
        }

        override fun put(bundle: Bundle, key: String, value: Wallet) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}
package eu.jobernas.rfidcomm

import android.os.Build
import android.os.Bundle

/**
 * Get parcelable
 *
 * @param T
 * @param key
 * @return
 */
@SuppressWarnings("deprecation")
inline fun <reified T>Bundle.getParcelableObject(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }


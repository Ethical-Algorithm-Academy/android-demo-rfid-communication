package eu.jobernas.rfidcomm

import android.content.Intent
import android.os.Build

/**
 * Get parcelable
 *
 * @param T
 * @param key
 * @return
 */
@SuppressWarnings("deprecation")
inline fun <reified T> Intent.getParcelableObject(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key)
    }

/**
 * Get parcelable array objects
 *
 * @param T
 * @param key
 * @return
 */
@SuppressWarnings("deprecation")
inline fun <reified T> Intent.getParcelableArrayObjects(key: String): Array<T?>? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayExtra(key, T::class.java)
    } else {
        getParcelableArrayExtra(key) as? Array<T?>
    }


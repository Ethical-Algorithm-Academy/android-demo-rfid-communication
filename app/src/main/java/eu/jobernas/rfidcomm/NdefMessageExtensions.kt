package eu.jobernas.rfidcomm

import android.nfc.NdefMessage
import kotlin.experimental.and

/**
 * Get text
 *
 * @return
 */
fun NdefMessage.getText(): String {
    val payload = records.firstOrNull()?.payload ?:  return ""
//    val firstByte = payload?.get(0) ?: return ""
//    val textEncoding = if((firstByte and 128.toByte()) == 0.toByte()) "UTF-8" else "UTF-16"
//    val languageCodeLength = (firstByte and 63.toByte())
    return String(payload)//String(payload, languageCodeLength + 1, payload?.length - languageCodeLength - 1, textEncoding)
}
package eu.jobernas.rfidcomm

import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NFCReaderViewModel:
    ViewModel() {

    companion object {
        private val TAG = NFCReaderViewModel::class.java.simpleName
    }

    private var nfcAdapter: NfcAdapter? = null

    val onInformationReceived: MutableLiveData<List<String>> = MutableLiveData(emptyList())

    fun load(context: Context) {
        Log.d(TAG, "load::executed")
        nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    }

    fun readNewTag(intent: Intent?) {
        when (intent?.action) {
            NfcAdapter.ACTION_TAG_DISCOVERED -> Log.w(TAG, "ACTION_TAG_DISCOVERED")
            NfcAdapter.ACTION_TECH_DISCOVERED -> Log.w(TAG, "ACTION_TECH_DISCOVERED")
            NfcAdapter.ACTION_NDEF_DISCOVERED -> Log.w(TAG, "ACTION_NDEF_DISCOVERED")
            else -> return
        }

//        val tagFromIntent: Tag? = intent.getParcelableObject(NfcAdapter.EXTRA_TAG)
//        if (tagFromIntent != null) {
//            val nfcTag = NfcA.get(tagFromIntent)
//            val atqa: ByteArray = nfcTag.atqa
//            val sak: Short = nfcTag.sak
//            Log.d(TAG, "atqa: $atqa, sak: $sak")
//        } else {
//            Log.w(TAG, "Tag is Null")
//        }

        val rawMessages = intent.getParcelableArrayObjects<NdefMessage>(NfcAdapter.EXTRA_NDEF_MESSAGES) ?: emptyArray()
        onInformationReceived.postValue(rawMessages.mapNotNull { it?.getText() })

//        onInformationReceived.postValue(nfcTag.tag.toString())
//        nfcTag.connect()
//
//        val isConnected= nfcTag.isConnected
//        Log.d(TAG, "isConnected: $isConnected")
//        if(isConnected) {
//            val receivedData:ByteArray= nfcTag.transceive(NFC_READ_COMMAND)
////            ..
////            //code to handle the received data
////            // Received data would be in the form of a byte array that can be converted to string
////            //NFC_READ_COMMAND would be the custom command you would have to send to your NFC Tag in order to read it
////            ..
//        } else{
//            Log.e("ans", "Not connected")
//        }
    }

//    fun enableForegroundDispatch(activity: Activity) {
//        val intent = Intent(activity.applicationContext, activity.javaClass).apply {
//            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//        }
//
//        val pendingIntent = PendingIntent.getActivity(activity.applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//        val filters = arrayOfNulls<IntentFilter>(1)
//        val techList = arrayOf<Array<String>>()
//
//        filters[0] = IntentFilter()
//        filters[0]?.apply {
//            addAction(NfcAdapter.ACTION_NDEF_DISCOVERED)
//            addCategory(Intent.CATEGORY_DEFAULT)
//            try {
//                addDataType("text/plain")
//            } catch (ex: IntentFilter.MalformedMimeTypeException) {
//                throw RuntimeException(ex)
//            }
//        }
//        nfcAdapter?.enableForegroundDispatch(activity, pendingIntent, filters, techList)
//    }

//    fun enableReader(activity: Activity) {
//        nfcAdapter?.enableReaderMode(activity, {
//
//        })
//    }

}
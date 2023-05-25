package eu.jobernas.rfidcomm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import eu.jobernas.rfidcomm.databinding.ActivityNfcReaderBinding

class NFCReaderActivity: AppCompatActivity() {

    private lateinit var nfcReaderViewModel: NFCReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNfcReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nfcReaderViewModel = ViewModelProvider(this)[NFCReaderViewModel::class.java].apply {
            load(this@NFCReaderActivity)
            binding.apply {
                onInformationReceived
                    .observe(this@NFCReaderActivity) {
                    nfcReaderValueTextView.text = it.joinToString(separator = ", \n")
                }
            }
        }
        nfcReaderViewModel.readNewTag(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        nfcReaderViewModel.readNewTag(intent)
    }

    override fun onResume() {
        super.onResume()
//        nfcReaderViewModel.enableForegroundDispatch(this)
    }

}
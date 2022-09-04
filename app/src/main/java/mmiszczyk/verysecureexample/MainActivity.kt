package mmiszczyk.verysecureexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            Thread {
                val host = "example.org"
                val pin = CertificatePinner
                    .Builder()
                    .add(host, "sha256/S4kZuhQQ1DPcMOCYFQXD0gG+UW0zmyVx6roNWpRl65I=")
                    .add(host, "sha256/RQeZkB42znUfsDIIFWIRiYEcKl7nHwNFwWCrnMMJbVc=")
                    .add(host, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
                    .build()
                val c = OkHttpClient.Builder().certificatePinner(pin).build()
                val r = Request.Builder().url("https://$host").build()
                c.newCall(r).execute()
            }.start()
        }
    }
}
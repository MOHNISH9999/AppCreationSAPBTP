package com.company.appcreationsapbtp.app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.appcreationsapbtp.R
import com.google.mlkit.vision.barcode.common.Barcode
import com.sap.cloud.mobile.fiori.qrcode.QRCodeConfig
import com.sap.cloud.mobile.fiori.qrcode.QRCodeReaderScreen

class SimpleActivity: AppCompatActivity() {


    companion object {
        const val QR_CODE_REQUEST_CODE = 123 // You can choose any unique integer value
    }

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.simple)


        var bt1:Button = findViewById(R.id.buttonnxt)
         resultTextView=findViewById(R.id.sample_text)




        bt1.setOnClickListener {
            // Launch QRCodeScanActivity
            val qrCodeIntent = Intent(this, QRPage::class.java)
            startActivityForResult(qrCodeIntent, QR_CODE_REQUEST_CODE)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == QR_CODE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val qrResult = data?.getStringExtra("qrResult")
                if (!qrResult.isNullOrEmpty()) {
                    // Set the result in your TextView
                    resultTextView.text = "Scanned Result: $qrResult"
                }
            } else {
                // Handle cancellation or other results
            }
        }
    }
}
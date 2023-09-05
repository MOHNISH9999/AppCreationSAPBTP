package com.company.appcreationsapbtp.app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.company.appcreationsapbtp.R
import com.google.mlkit.vision.barcode.common.Barcode
import com.sap.cloud.mobile.fiori.qrcode.QRCodeConfig
import com.sap.cloud.mobile.fiori.qrcode.QRCodeReaderScreen

class QRPage : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrpage)
        val qrCodeScreen = findViewById<QRCodeReaderScreen>(R.id.qr_reader)
        resultTextView = findViewById(R.id.resultTextView)

        val qrCodeConfig = QRCodeConfig.Builder()
            .setBarCodeFormat(Barcode.FORMAT_ALL_FORMATS)
            .setScanInterval(500)
            .setAutoFocus(true)
            .build()

        qrCodeScreen.setQRConfig(qrCodeConfig)

        qrCodeScreen.setBarCodeListener(this, object : QRCodeReaderScreen.OnMultiBarCodeDetectorListener {
            override fun onBarcodeDetected(barcode: String?) {
                if (!barcode.isNullOrEmpty()) {
                    val resultIntent = Intent()
                    resultIntent.putExtra("qrResult", barcode)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }

            }

            override fun onMultiBarcodeDetected(barcodes: MutableList<String>?) {
                // Handle multi barcodes scan result
            }

            override fun onMultiBarcodeCaptured(barcodesBitMap: Bitmap?, barcodes: MutableList<Barcode>?) {
                // Handle the captured image and multi barcodes scan result
            }
        })



    }

}
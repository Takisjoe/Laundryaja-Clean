package com.takisjoeapp.laundryaja.util.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.takisjoeapp.laundryaja.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qractivity);

        mScannerView = findViewById(R.id.scanner_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        // Handle result here, e.g. by passing the barcode value to another activity.
        String barcodeValue = result.getText();
        // Do something with the barcode value...

        // Do something with the scanned barcode value, for example:
        // Display the barcode value on the screen
//        TextView barcodeResultView = findViewById(R.id.barcode_result);
//        barcodeResultView.setText(barcodeValue);

        // Send the barcode value to another activity
//        Intent intent = new Intent(this, AnotherActivity.class);
//        intent.putExtra("barcodeValue", barcodeValue);
//        startActivity(intent);

        // Resume the camera preview
        mScannerView.resumeCameraPreview(this);
        Toast.makeText(this, "Code " + barcodeValue, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
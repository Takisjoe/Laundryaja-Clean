package com.takisjoeapp.laundryaja.util.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;


public class QRUtil {
    public static Bitmap generateQRCode(String data) {
        Bitmap bitmap = null;
        BitMatrix result;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            int width = result.getWidth();
            int height = result.getHeight();
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, result.get(i, j) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void scanBarcode(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    public static void scanQR(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.initiateScan();
    }

//    public static void scanBarcode(Fragment fragment) {
//        IntentIntegratorSupportV4 integrator = IntentIntegratorSupportV4.forSupportFragment(fragment);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Scan a barcode");
//        integrator.setCameraId(0);
//        integrator.setBeepEnabled(false);
//        integrator.setBarcodeImageEnabled(false);
//        integrator.initiateScan();
//    }


    public static String handleScanResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            if (result.getContents() == null) {
                Log.d("QRUtil", "Cancelled scan");
            } else {
                Log.d("QRUtil", "Scanned");
                return result.getContents();
            }
        } else {

            Log.d("QRUtil", "Scan failed");
        }
        return null;
    }
}


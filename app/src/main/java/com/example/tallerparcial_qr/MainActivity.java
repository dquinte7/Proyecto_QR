package com.example.tallerparcial_qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonScan;
    TextView textSMS1, textSMS2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = findViewById(R.id.buttonScan);
        textSMS1 = findViewById(R.id.textSMS1);
        textSMS2 = findViewById(R.id.textSMS2);

        buttonScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult !=null){
            if(intentResult.getContents() == null){
                Toast.makeText(getBaseContext(), "cancelled", Toast.LENGTH_SHORT).show();
            }else{
                textSMS1.setText(intentResult.getContents());
                textSMS2.setText(intentResult.getFormatName());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    }
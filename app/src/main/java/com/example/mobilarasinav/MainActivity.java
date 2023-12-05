package com.example.mobilarasinav;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_converter = findViewById(R.id.btnConverter);
        Button btn_random = findViewById(R.id.btnRandom);
        Button btn_sms = findViewById(R.id.btnSms);

        TextView txt_numara = findViewById(R.id.ogrNo);
        TextView txt_isim = findViewById(R.id.adSoyad);

        btn_converter.setAlpha(0f);
        btn_random.setAlpha(0f);
        btn_sms.setAlpha(0f);
        txt_numara.setAlpha(0f);
        txt_isim.setAlpha(0f);

        ObjectAnimator txt_numara_girisAnimasyonu = ObjectAnimator.ofFloat(txt_numara, TextView.ALPHA, 0f, 1f);
        txt_numara_girisAnimasyonu.setDuration(2500);
        txt_numara_girisAnimasyonu.setInterpolator(new AccelerateDecelerateInterpolator());
        txt_numara_girisAnimasyonu.start();

        ObjectAnimator txt_isim_girisAnimasyonu = ObjectAnimator.ofFloat(txt_isim, TextView.ALPHA, 0f, 1f);
        txt_isim_girisAnimasyonu.setDuration(2500);
        txt_isim_girisAnimasyonu.setStartDelay(500);
        txt_isim_girisAnimasyonu.setInterpolator(new AccelerateDecelerateInterpolator());
        txt_isim_girisAnimasyonu.start();

        ObjectAnimator btn_converter_girisAnimasyonu = ObjectAnimator.ofFloat(btn_converter, TextView.ALPHA, 0f, 1f);
        btn_converter_girisAnimasyonu.setDuration(2500);
        btn_converter_girisAnimasyonu.setStartDelay(1000);
        btn_converter_girisAnimasyonu.setInterpolator(new AccelerateDecelerateInterpolator());
        btn_converter_girisAnimasyonu.start();

        ObjectAnimator btn_random_girisAnimasyonu = ObjectAnimator.ofFloat(btn_random, TextView.ALPHA, 0f, 1f);
        btn_random_girisAnimasyonu.setDuration(2500);
        btn_random_girisAnimasyonu.setStartDelay(1500);
        btn_random_girisAnimasyonu.setInterpolator(new AccelerateDecelerateInterpolator());
        btn_random_girisAnimasyonu.start();

        ObjectAnimator btn_sms_girisAnimasyonu = ObjectAnimator.ofFloat(btn_sms, TextView.ALPHA, 0f, 1f);
        btn_sms_girisAnimasyonu.setDuration(2500);
        btn_sms_girisAnimasyonu.setStartDelay(2000);
        btn_sms_girisAnimasyonu.setInterpolator(new AccelerateDecelerateInterpolator());
        btn_sms_girisAnimasyonu.start();


        try{
            btn_converter.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Converter.class);
                startActivity(intent);
            });
            btn_random.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Random.class);
                startActivity(intent);
            });
            btn_sms.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Sms.class);
                startActivity(intent);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
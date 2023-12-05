package com.example.mobilarasinav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Converter extends AppCompatActivity {

//
// 3 farklı birimi diğer birimlere çeviren programı yazınız.
// Dönüştürme işlemi buton ekleyebileceğiniz gibi değişiklik olayını da kontrol edebilirisiniz.

// a) Onluk tabanda girilen sayıyı; ikilik, sekizlik ve on altılık sayı tabanına dönüştürünüz.
// b) Mega Byte olarak girilen değeri; kilo byte, byte, kibi byte ve bit olarak dönüştürünüz.
// c) Celsius olarak girilen sıcaklığı; Fahrenheit ve Kelvin’e dönüştürünüz.
//
// Dönüşümlerde ortaya çıkabilecek hataları denetim altına almak +5 puan
//

    private final String[] sayiTabanlari = {"2 (Binary)","8 (Octal)","16 (Hexadecimal)"};
    private final String[] kapasiteListesi = {"KiloByte","Byte","KibiByte","Bit"};

    String secimDecimal, secimMegaByte;
    EditText inputDecimal, inputMegaByte, inputCelcius;
    TextView sonucDecimal, sonucMegaByte, sonucCelsius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        inputDecimal = findViewById(R.id.inputTextDecimal);
        sonucDecimal = findViewById(R.id.sonucDecimal);
        inputMegaByte = findViewById(R.id.inputTextMegaByte);
        sonucMegaByte = findViewById(R.id.sonucMegaByte);

        inputCelcius = findViewById(R.id.inputTextCelcius);
        sonucCelsius = findViewById(R.id.sonucCelcius);

        Spinner spinnerDecimal, spinnerMegaByte;
        ArrayAdapter<String> decimalAdapter, megaByteAdepter;

        spinnerDecimal = findViewById(R.id.spinnerDecimal);
        decimalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sayiTabanlari);
        spinnerDecimal.setAdapter(decimalAdapter);

        spinnerMegaByte = findViewById(R.id.spinnerMegaByte);
        megaByteAdepter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kapasiteListesi);
        spinnerMegaByte.setAdapter(megaByteAdepter);

        RadioButton radioFahrenheit = findViewById(R.id.radioButtonF);
        RadioButton radioKelvin = findViewById(R.id.radioButtonK);


        inputDecimal.addTextChangedListener(new android.text.TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sonucDecimal.setText("SONUC : ");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if(inputDecimal.getText().toString().equals("")){
                        sonucDecimal.setText("SONUC : ");
                    }
                    else {
                        long decimal = Long.parseLong(inputDecimal.getText().toString());
                        decimalSonucuGoster(decimal);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {
                // DO NOTHING
            }
        });

        spinnerDecimal.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {

                try {
                    secimDecimal = sayiTabanlari[position];
                    if(inputDecimal.getText().toString().equals("")){
                        sonucDecimal.setText("SONUC : ");
                    }
                    else {
                        long decimal = Long.parseLong(inputDecimal.getText().toString());
                        decimalSonucuGoster(decimal);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // DO NOTHING
            }
        });


        inputMegaByte.addTextChangedListener(new android.text.TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sonucMegaByte.setText("SONUC : ");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inputMegaByte.getText().toString().equals("")){
                    sonucMegaByte.setText("SONUC : ");
                }
                else {
                    long megabayt = Long.parseLong(inputMegaByte.getText().toString());
                    megaByteSonucuGoster(megabayt);
                }
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {
                // DO NOTHING
            }
        });

        spinnerMegaByte.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

                try {
                    secimMegaByte = kapasiteListesi[position];
                    if(inputMegaByte.getText().toString().equals("")){
                        sonucMegaByte.setText("SONUC : ");
                    }
                    else {
                        long megabayt = Long.parseLong(inputMegaByte.getText().toString());
                        megaByteSonucuGoster(megabayt);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // DO NOTHING
            }
        });


        inputCelcius.addTextChangedListener(new android.text.TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sonucCelsius.setText("SONUC : ");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if(inputCelcius.getText().toString().equals("")){
                        sonucCelsius.setText("SONUC : ");
                    }
                    else {
                        double celcius = Double.parseDouble(inputCelcius.getText().toString());
                        if(radioFahrenheit.isChecked()){
                            double fahrenheit = (celcius * 9 / 5) + 32;
                            sonucCelsius.setText("SONUC : " + fahrenheit + " °F");
                        }
                        else if(radioKelvin.isChecked()){
                            double kelvin = (celcius + 273.15);
                            sonucCelsius.setText("SONUC : " + kelvin + " K");
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {
                // DO NOTHING
            }
        });

        radioFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        double fahrenheit = (Double.parseDouble(inputCelcius.getText().toString()) * 9 / 5) + 32;
                        sonucCelsius.setText("SONUC : " + fahrenheit + " °F");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        radioKelvin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        double kelvin = (Double.parseDouble(inputCelcius.getText().toString()) + 273.15);
                        sonucCelsius.setText("SONUC : " + kelvin + " K");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void decimalSonucuGoster(long decimal){
        String sonuc = "";
        switch (secimDecimal) {
            case "2 (Binary)":
                sonuc = convertDecimalToOther(decimal, 2);
                break;
            case "8 (Octal)":
                sonuc = convertDecimalToOther(decimal, 8);
                break;
            case "16 (Hexadecimal)":
                sonuc = convertDecimalToOther(decimal, 16);
                break;
            default:
                sonuc = "HATA";
                break;
        }
        sonucDecimal.setText("SONUC : " + sonuc);
    }

    private String convertDecimalToOther(long decimal, int base){
        String sonuc = "";
        long kalan;
        while(decimal != 0){
            kalan = decimal % base;
            if(kalan<10)
                sonuc = kalan + sonuc;
            else
                sonuc = (char)(kalan + 55) + sonuc; // 55 = 'A' - 10
            decimal /= base;
        }
        return sonuc;
    }

    private void megaByteSonucuGoster(long megabayt){
        String sonuc = "";
        switch (secimMegaByte) {
            case "KiloByte":
                sonuc = Long.toString(megabayt*1024);
                break;
            case "Byte":
                sonuc = Long.toString(megabayt*1024*1024);
                break;
            case "KibiByte":
                sonuc = Double.toString(megabayt * 976.5625);
                break;
            case "Bit":
                sonuc = Long.toString(megabayt*1024*1024*8);
                break;
            default:
                sonuc = "HATA";
                break;
        }
        sonucMegaByte.setText("SONUC : " + sonuc);
    }
}



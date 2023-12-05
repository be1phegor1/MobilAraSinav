package com.example.mobilarasinav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sms extends AppCompatActivity {

    String tel, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        Button btnGonder = findViewById(R.id.btnGonder);
        Button btnRandomSoz = findViewById(R.id.btnRandomSoz);
        EditText textTel = findViewById(R.id.inputPhone);
        EditText textMessage = findViewById(R.id.inputMessage);

        try{
            btnGonder.setOnClickListener(v->{
                tel="";
                message="";

                if(ContextCompat.checkSelfPermission(Sms.this, android.Manifest.permission.SEND_SMS) != getPackageManager().PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Sms.this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
                }
                else {
                    if (textTel.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Lütfen telefon numarası giriniz.", Toast.LENGTH_SHORT).show();
                    }
                    else if(textMessage.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Lütfen mesaj giriniz.", Toast.LENGTH_SHORT).show();
                    }
                    else if(textTel.getText().toString().length() != 11){
                        Toast.makeText(getApplicationContext(), "Lütfen 11 haneli geçerli bir telefon numarası giriniz.", Toast.LENGTH_SHORT).show();
                    }
                    else if(textMessage.getText().toString().length() > 160){
                        Toast.makeText(getApplicationContext(), "Lütfen 160 karakterden fazla mesaj girmeyiniz.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        tel = textTel.getText().toString();
                        message = textMessage.getText().toString();
                        sendSMS();
                    }
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
            btnRandomSoz.setOnClickListener(v -> new getQuotes().execute());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendSMS() {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(tel, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Mesaj gönderildi.", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class getQuotes extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result;
            try {
                URL url = new URL("https://api.quotable.io/random");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                while ((result = br.readLine()) != null) {
                    sb.append(result).append("\n");
                }
                br.close();
                is.close();
                conn.disconnect();
                return sb.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                String quote = jsonObject.getString("content");

                EditText textMessage = findViewById(R.id.inputMessage);
                textMessage.setText(quote);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
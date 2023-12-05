package com.example.mobilarasinav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Random extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        LinearLayout scrollPanel = findViewById(R.id.scrollPanel);
        EditText textAdet = findViewById(R.id.inputAdet);
        EditText textMin = findViewById(R.id.inputRandMin);
        EditText textMax = findViewById(R.id.inputRandMax);

        try {
            textAdet.addTextChangedListener(new android.text.TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    scrollPanel.removeAllViews();
                    if (textAdet.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Lütfen adet giriniz.", Toast.LENGTH_SHORT).show();
                    } else if (textAdet.getText().toString().length() == 3) {
                        Toast.makeText(getApplicationContext(), "Programin cokmemesi icin en fazla 99 adet progress bar olusturabilirsin.", Toast.LENGTH_SHORT).show();
                    } else if (textAdet.getText().toString().length() > 3) {
                        // DO NOTHING.. cok fazla bildirim cikmamasi icin burayi ayirdim.
                    } else {
                        int adet = Integer.parseInt(textAdet.getText().toString());
                        int randomBoundaries[] = generateRandomBoundaries(0, 100);
                        int randMin = randomBoundaries[0];
                        int randMax = randomBoundaries[1];

                        textMin.setText(String.valueOf(randMin));
                        textMax.setText(String.valueOf(randMax));

                        for (int i = 0; i < adet; i++) {
                            int progressBarBoundaries[] = generateRandomBoundaries(randMin,randMax);
                            addProgressBar(Random.this, scrollPanel, progressBarBoundaries[0], progressBarBoundaries[1]);
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    scrollPanel.removeAllViews();
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    scrollPanel.removeAllViews();
                }

            });
        } catch (Exception e) { }
    }

    private int[] generateRandomBoundaries(int min, int max){
        int randMin = (int) (Math.random() * (max - min + 1) + min);
        int randMax;
        do {
            randMax = (int) (Math.random() * (max - min + 1) + min);
        }
        while (randMax == randMin);
        if (randMin > randMax) {
            int temp = randMin;
            randMin = randMax;
            randMax = temp;
        }
        return new int[]{randMin, randMax};
    }

    private void addProgressBar(Context context, LinearLayout scrollPanel, int min, int max) {

        int randPosition = (int) (Math.random() * (max - min - 1) + min + 1);

        String percentage = Double.toString((double) (randPosition - min) / (max - min) * 100);
        percentage = percentage.contains(".") ? percentage.substring(0, percentage.indexOf(".")) : percentage;

        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));



        TextView percentageText = new TextView(context);
        percentageText.setId(View.generateViewId());
        percentageText.setText(randPosition +" = %" + percentage);

        ConstraintLayout.LayoutParams percentageTextParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        percentageTextParams.topToTop = ConstraintSet.PARENT_ID;
        percentageTextParams.startToStart = ConstraintSet.PARENT_ID;
        percentageTextParams.setMargins(dpToPx(180), dpToPx(10), 0, 0);

        percentageText.setLayoutParams(percentageTextParams);
        constraintLayout.addView(percentageText);




        TextView minText = new TextView(context);
        minText.setId(View.generateViewId());
        minText.setText(String.valueOf(min));

        ConstraintLayout.LayoutParams minTextParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        minTextParams.topToTop = ConstraintSet.PARENT_ID;
        minTextParams.startToStart = ConstraintSet.PARENT_ID;
        minTextParams.setMargins(dpToPx(80), dpToPx(40), 0, 0);

        minText.setLayoutParams(minTextParams);
        constraintLayout.addView(minText);




        TextView maxText = new TextView(context);
        maxText.setId(View.generateViewId());
        maxText.setText(String.valueOf(max));

        ConstraintLayout.LayoutParams maxTextParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        maxTextParams.topToTop = ConstraintSet.PARENT_ID;
        maxTextParams.endToEnd = ConstraintSet.PARENT_ID;
        maxTextParams.setMargins(0, dpToPx(40), dpToPx(80), 0);

        maxText.setLayoutParams(maxTextParams);
        constraintLayout.addView(maxText);


        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setId(View.generateViewId());
        ConstraintLayout.LayoutParams progressBarParams = new ConstraintLayout.LayoutParams(
                dpToPx(150),
                dpToPx(20)
        );
        progressBarParams.topToTop = ConstraintSet.PARENT_ID;
        progressBarParams.startToStart = minText.getId();
        progressBarParams.endToEnd = maxText.getId();
        progressBarParams.setMargins(dpToPx(30), dpToPx(40), dpToPx(30), dpToPx(90));

        progressBar.setLayoutParams(progressBarParams);
        progressBar.setMin(min);
        progressBar.setProgress(Integer.parseInt(String.valueOf(randPosition)));
        progressBar.setMax(max);
        constraintLayout.addView(progressBar);


        scrollPanel.addView(constraintLayout);
    }

    private int dpToPx(int dp){
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
}
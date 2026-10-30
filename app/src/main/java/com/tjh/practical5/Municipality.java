package com.tjh.practical5;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private String currentMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String passedData = getIntent().getStringExtra("municipality");
        currentMunicipality = TextProcessor.processMunicipality(passedData);

        if (currentMunicipality == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView tvName = findViewById(R.id.tvMunicipalityName);
        TextView tvInfo = findViewById(R.id.tvMunicipalityInfo);
        ImageView ivImage = findViewById(R.id.ivMunicipalityImage);
        Button btnVisit = findViewById(R.id.btnVisitSite);

        String[] summaries = getResources().getStringArray(R.array.municipalities_array);

        int colorResId;
        int imageResId;
        int arrayIndex;

        switch (currentMunicipality) {
            case "Beijing":
                colorResId = R.color.municipality_Beijing;
                imageResId = R.drawable.beijing;
                arrayIndex = 0;
                break;
            case "Shanghai":
                colorResId = R.color.municipality_shanghai;
                imageResId = R.drawable.shanghai;
                arrayIndex = 1;
                break;
            case "Chongqing":
                colorResId = R.color.municipality_chongqing;
                imageResId = R.drawable.chongqing;
                arrayIndex = 2;
                break;
            case "Tianjin":
            default:
                colorResId = R.color.municipality_tianjin;
                imageResId = R.drawable.tianjin;
                arrayIndex = 3;
                break;
        }

        int themeColor = ContextCompat.getColor(this, colorResId);
        int lightTextColor = ContextCompat.getColor(this, R.color.color_textlight);

        tvName.setText(currentMunicipality);
        tvName.setBackgroundColor(themeColor);
        tvName.setTextColor(lightTextColor);

        ivImage.setImageResource(imageResId);

        tvInfo.setText(summaries[arrayIndex]);
        tvInfo.setTextColor(themeColor);

        btnVisit.setBackgroundColor(themeColor);
        btnVisit.setTextColor(lightTextColor);

        btnVisit.setOnClickListener(v -> {
            String url;
            switch (currentMunicipality) {
                case "Beijing":
                    url = "https://english.beijing.gov.cn/";
                    break;
                case "Shanghai":
                    url = "https://english.shanghai.gov.cn/";
                    break;
                case "Chongqing":
                    url = "https://english.cq.gov.cn/";
                    break;
                case "Tianjin":
                default:
                    url = "https://en.tj.gov.cn/";
                    break;
            }

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            try {
                startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No app found to open this website.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
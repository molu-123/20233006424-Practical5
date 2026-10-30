package com.tjh.practical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editMunicipality = findViewById(R.id.editMunicipality);

        editMunicipality.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                String rawInput = editMunicipality.getText().toString();

                String validMunicipality = TextProcessor.processMunicipality(rawInput);

                if (validMunicipality != null) {
                    Intent intent = new Intent(MainActivity.this, Municipality.class);
                    intent.putExtra("municipality", validMunicipality);
                    startActivity(intent);

                    editMunicipality.setText("");
                } else {
                    editMunicipality.setError(getString(R.string.error_invalid));
                }
                return true;
            }
            return false;
        });
    }
}
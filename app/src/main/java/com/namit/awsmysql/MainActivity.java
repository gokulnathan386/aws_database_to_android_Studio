package com.namit.awsmysql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button addButton, showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonPlace);
        addButton = findViewById(R.id.buttonAdd);
        showButton = findViewById(R.id.buttonShow);

    }

    public void addFunc(View view) {
        String name_str = e1.getText().toString();
        String place_str = e2.getText().toString();
        if (name_str.isEmpty()) {
            e1.setError("Field cannot be empty");
        } else if (place_str.isEmpty()) {
            e2.setError("Field cannot be empty");
        } else {
            // add to AWS RDS DB:

            ShowActivity.addTemp(name_str, place_str);
        }


    }

    public void showFunc(View view) {
        startActivity(new Intent(MainActivity.this, ShowActivity.class));
    }


}
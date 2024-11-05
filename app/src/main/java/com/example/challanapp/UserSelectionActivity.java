package com.example.challanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        Button officerButton = findViewById(R.id.officerButton);
        Button citizenButton = findViewById(R.id.citizenButton);

        officerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity("officer");
            }
        });

        citizenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity("citizen");
            }
        });
    }

    private void openLoginActivity(String userType) {
        Intent intent = new Intent(UserSelectionActivity.this, LoginActivity.class);
        intent.putExtra("USER_TYPE", userType);
        startActivity(intent);
    }
}

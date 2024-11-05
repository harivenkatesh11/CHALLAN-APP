package com.example.challanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (validateCredentials(username, password)) {
                    if (isOfficer(username)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else if (isCitizen(username)) {
                        Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString(username, null);
        return storedPassword != null && storedPassword.equals(password);
    }

    private boolean isOfficer(String username) {
        // Example check; you may need to use different criteria for officers
        SharedPreferences sharedPreferences = getSharedPreferences("UserRoles", MODE_PRIVATE);
        return "officer".equals(sharedPreferences.getString(username, null));
    }

    private boolean isCitizen(String username) {
        // Example check; you may need to use different criteria for citizens
        SharedPreferences sharedPreferences = getSharedPreferences("UserRoles", MODE_PRIVATE);
        return "citizen".equals(sharedPreferences.getString(username, null));
    }
}

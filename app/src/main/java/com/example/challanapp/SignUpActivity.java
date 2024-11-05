package com.example.challanapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText roleEditText; // Ensure this matches the XML ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        roleEditText = findViewById(R.id.roleEditText); // Ensure this matches the XML ID
        Button signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String role = roleEditText.getText().toString(); // Ensure this matches the XML ID

                if (validateSignUp(username, password, confirmPassword, role)) {
                    saveUserCredentials(username, password);
                    saveUserRole(username, role); // Save the user's role
                    Toast.makeText(SignUpActivity.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean validateSignUp(String username, String password, String confirmPassword, String role) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!role.equals("officer") && !role.equals("citizen")) {
            Toast.makeText(this, "Invalid role", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveUserCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, password);
        editor.apply();
    }

    private void saveUserRole(String username, String role) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserRoles", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, role);
        editor.apply();
    }
}

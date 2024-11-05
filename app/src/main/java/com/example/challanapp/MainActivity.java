package com.example.challanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText vehicleNumberEditText;
    private EditText ownerNameEditText;
    private EditText fineAmountEditText;
    private ListView challanListView;
    private ArrayAdapter<String> adapter;
    private List<String> challanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleNumberEditText = findViewById(R.id.vehicleNumberEditText);
        ownerNameEditText = findViewById(R.id.ownerNameEditText);
        fineAmountEditText = findViewById(R.id.fineAmountEditText);
        Button saveChallanButton = findViewById(R.id.saveChallanButton);
        Button aboutUsButton = findViewById(R.id.aboutUsButton);
        challanListView = findViewById(R.id.challanListView);

        challanList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challanList);
        challanListView.setAdapter(adapter);

        saveChallanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChallan();
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        // Load and display saved challans
        loadChallans();
    }

    private void saveChallan() {
        String vehicleNumber = vehicleNumberEditText.getText().toString();
        String ownerName = ownerNameEditText.getText().toString();
        String fineAmount = fineAmountEditText.getText().toString();

        if (vehicleNumber.isEmpty() || ownerName.isEmpty() || fineAmount.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        String challan = "Vehicle: " + vehicleNumber + ", Owner: " + ownerName + ", Fine: " + fineAmount;
        challanList.add(challan);

        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the updated list of challans
        Set<String> challanSet = new HashSet<>(challanList);
        editor.putStringSet("challans", challanSet);
        editor.apply();

        // Clear fields after saving
        vehicleNumberEditText.setText("");
        ownerNameEditText.setText("");
        fineAmountEditText.setText("");

        // Notify user
        Toast.makeText(this, "Challan saved", Toast.LENGTH_SHORT).show();

        // Refresh ListView
        adapter.notifyDataSetChanged();
    }

    private void loadChallans() {
        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", Context.MODE_PRIVATE);

        // Retrieve saved challans
        Set<String> challanSet = sharedPreferences.getStringSet("challans", new HashSet<String>());
        challanList.clear();
        challanList.addAll(challanSet);

        // Refresh ListView
        adapter.notifyDataSetChanged();
    }
}

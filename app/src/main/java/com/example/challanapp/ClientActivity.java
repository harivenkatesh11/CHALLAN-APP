package com.example.challanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class ClientActivity extends AppCompatActivity {

    private ListView challanListView;
    private SearchView searchView;
    private Button backButton;
    private List<String> challanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        challanListView = findViewById(R.id.challanListView);
        searchView = findViewById(R.id.searchView);
        backButton = findViewById(R.id.backButton);

        // Initialize challanList as an ArrayList
        challanList = new ArrayList<>();

        // Load challan data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", MODE_PRIVATE);
        Set<String> challanSet = sharedPreferences.getStringSet("challans", new HashSet<>());
        if (challanSet != null) {
            challanList.addAll(challanSet);
        }

        // Set up the adapter and ListView
        ChallanAdapter adapter = new ChallanAdapter(this, challanList);
        challanListView.setAdapter(adapter);

        // Filter the list based on search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Set up the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the LoginActivity
                Intent intent = new Intent(ClientActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finish the current activity to remove it from the back stack
            }
        });
    }
}

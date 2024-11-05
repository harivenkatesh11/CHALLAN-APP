package com.example.challanapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChallanDetailsActivity extends AppCompatActivity {

    private ListView challanListView;
    private ArrayAdapter<String> adapter;
    private List<String> challanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challan_details);

        challanListView = findViewById(R.id.challanListView);

        challanList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challanList);
        challanListView.setAdapter(adapter);

        loadChallans();
    }

    private void loadChallans() {
        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", MODE_PRIVATE);

        Set<String> challanSet = sharedPreferences.getStringSet("challans", new HashSet<String>());
        challanList.clear();
        challanList.addAll(challanSet);

        adapter.notifyDataSetChanged();
    }
}

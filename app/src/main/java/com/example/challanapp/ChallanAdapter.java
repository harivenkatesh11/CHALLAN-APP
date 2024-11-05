package com.example.challanapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChallanAdapter extends ArrayAdapter<String> {

    public ChallanAdapter(Context context, List<String> challans) {
        super(context, 0, challans);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String challan = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.challan_item, parent, false);
        }

        TextView challanTextView = convertView.findViewById(R.id.challanTextView);
        Button paymentButton = convertView.findViewById(R.id.payButton);

        challanTextView.setText(challan);

        // Initially set payment button visibility to GONE
        paymentButton.setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle payment button visibility
                if (paymentButton.getVisibility() == View.GONE) {
                    paymentButton.setVisibility(View.VISIBLE);
                } else {
                    paymentButton.setVisibility(View.GONE);
                }
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle payment button click
                String fineAmount = extractFineAmount(challan);
                openPaymentApp(fineAmount);
            }
        });

        return convertView;
    }

    // Method to extract fine amount (this is an example, adjust based on your data format)
    private String extractFineAmount(String challan) {
        // Assuming the challan format is "Vehicle: <vehicle>, Owner: <owner>, Fine: <amount>"
        int index = challan.lastIndexOf(":");
        if (index != -1) {
            return challan.substring(index + 1).trim();
        }
        return "";
    }

    // Method to open the payment app
    private void openPaymentApp(String amount) {
        // Construct the URI to open a UPI payment app
        Uri uri = Uri.parse("upi://pay?pa=<your_upi_id>@upi&pn=PoliceDepartment&mc=0000&tid=0000&tr=0000&tn=TrafficFine&am=" + amount + "&cu=INR");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Verify there is an app to handle the intent
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            getContext().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "No UPI app found on your device", Toast.LENGTH_SHORT).show();
        }
    }
}
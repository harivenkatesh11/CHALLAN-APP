package com.example.challanapp;
public class Challan {
    private String vehicleNumber;
    private String ownerName;
    private String fineAmount;
    public Challan(String vehicleNumber, String ownerName, String fineAmount) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.fineAmount = fineAmount;
    }  public String getVehicleNumber() {
        return vehicleNumber;
    }public String getOwnerName() {
        return ownerName;
    }public String getFineAmount() {
        return fineAmount;
    }
}

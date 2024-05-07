package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvCarType = findViewById(R.id.tvCarType);
        TextView tvOutsideCity = findViewById(R.id.tvOutsideCity);
        TextView tvDay = findViewById(R.id.tvDay);
        TextView tvDiscount = findViewById(R.id.tvDisscount); // Perbaiki ID dari tvDiscount
        TextView tvTotal = findViewById(R.id.tvTotal);

        // Retrieve data from Intent
        String selectedCar = getIntent().getStringExtra("selectedCar");
        String selectedCity = getIntent().getStringExtra("selectedCity");
        int rentDays = getIntent().getIntExtra("rentDays", 0);
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0);

        // Calculate base price per day based on selected car
        double basePricePerDay;
        switch (selectedCar) {
            case "Pajero":
                basePricePerDay = 2400000;
                break;
            case "Alphard": // Perbaiki penulisan Alpard menjadi Alphard
                basePricePerDay = 1550000;
                break;
            case "Inova":
                basePricePerDay = 850000;
                break;
            case "Brio":
                basePricePerDay = 550000;
                break;
            default:
                basePricePerDay = 0;
        }

        // Additional charges for inside or outside city
        double additionalCharge = 0;
        if (selectedCity.equals("Inside City")) {
            additionalCharge = 135000;
        } else if (selectedCity.equals("Outside City")) {
            additionalCharge = 250000;
        }

        // Calculate total price
        double totalBeforeDiscount = basePricePerDay * rentDays + additionalCharge;
        double discount = 0;
        if (totalBeforeDiscount > 10000000) {
            discount = totalBeforeDiscount * 0.07; // 7% discount
        } else if (totalBeforeDiscount > 5000000) {
            discount = totalBeforeDiscount * 0.05; // 5% discount
        }
        double totalPriceAfterDiscount = totalBeforeDiscount - discount;

        // Format total price into currency format
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedTotalPrice = formatRupiah.format(totalPriceAfterDiscount);
        String formattedDiscount = formatRupiah.format(discount);

        // Set the text for each TextView
        tvCarType.setText("Car Type: " + selectedCar);
        tvOutsideCity.setText("Outside City: " + selectedCity);
        tvDay.setText("Day Of Rent: " + rentDays);
        tvDiscount.setText("Discount: " + formattedDiscount);
        tvTotal.setText("Total: " + formattedTotalPrice);
    }
}



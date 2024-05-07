package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg1;
    private RadioGroup rg2;
    private TextView tvDay;
    private Button btnRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        tvDay = findViewById(R.id.etDay); // Perbaiki ID dari tvDay
        btnRent = findViewById(R.id.btnRent);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarId = rg1.getCheckedRadioButtonId();
                RadioButton selectedCarRadioButton = findViewById(selectedCarId);
                String selectedCar = selectedCarRadioButton.getText().toString();

                int selectedCityId = rg2.getCheckedRadioButtonId();
                RadioButton selectedCityRadioButton = findViewById(selectedCityId);
                String selectedCity = selectedCityRadioButton.getText().toString();

                String rentDaysStr = tvDay.getText().toString();
                int rentDays = Integer.parseInt(rentDaysStr);

                double basePrice;
                switch (selectedCar) {
                    case "Pajero":
                        basePrice = 2400000;
                        break;
                    case "Alphard": // Perbaiki penulisan Alpard menjadi Alphard
                        basePrice = 1550000;
                        break;
                    case "Innova":
                        basePrice = 850000;
                        break;
                    case "Brio":
                        basePrice = 550000;
                        break;
                    default:
                        basePrice = 0;
                }

                double totalPrice = basePrice * rentDays;

                // Add additional charges for inside or outside city
                if (selectedCity.equals("Inside City")) {
                    totalPrice += 135000;
                } else if (selectedCity.equals("Outside City")) {
                    totalPrice += 250000;
                }

                // Apply discount based on total price
                if (totalPrice > 10000000) { // If total price is more than 10 million
                    totalPrice *= 0.93; // 7% discount
                } else if (totalPrice > 5000000) { // If total price is more than 5 million
                    totalPrice *= 0.95; // 5% discount
                }

                // Example: Intent to DetailActivity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("selectedCar", selectedCar);
                intent.putExtra("selectedCity", selectedCity);
                intent.putExtra("rentDays", rentDays);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }
}


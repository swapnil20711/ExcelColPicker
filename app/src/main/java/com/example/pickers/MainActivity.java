package com.example.pickers;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pickers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(binding.startNumber.getText().toString().isEmpty()) && !(binding.endNumber.getText().toString().isEmpty())) {
                    intializePicker();
                } else {
                    Toast.makeText(MainActivity.this, "Enter both values to see picker", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkBox.isChecked()) {
                    binding.numberPicker.setVisibility(View.VISIBLE);
                } else {
                    binding.numberPicker.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.radioNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.radioReverse.setChecked(false);
                binding.numberPicker.setVisibility(View.GONE);
            }
        });

        binding.radioReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.radioNormal.setChecked(false);
                binding.numberPicker.setVisibility(View.GONE);
            }
        });

    }

    //To intialize the picker
    private void intializePicker() {
        binding.numberPicker.setMinValue(Integer.valueOf(binding.startNumber.getText().toString()));
        binding.numberPicker.setMaxValue((Integer.valueOf(binding.endNumber.getText().toString())));


        binding.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String ans;
                if (binding.radioReverse.isChecked()) {
                    binding.radioNormal.setChecked(false);
                    ans = getReverseColName(value);
                    return value + " " + ans;
                } else {
                    binding.radioReverse.setChecked(false);
                    ans = getColName(value);
                    return value + " " + ans;
                }
            }
        });
    }
    //To get coloum name in order
    private String getColName(int col) {
        StringBuilder CV = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                CV.append('Z');
                col = (col / 26) - 1;
            } else {
                CV.append((char) (64 + rem));
                col /= 26;
            }
        }
        return CV.reverse().toString();
    }
    //To get coloum name in reverse order
    private String getReverseColName(int col) {
        StringBuilder CV = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                CV.append('A');
                col = (col / 26) - 1;
            } else {
                CV.append((char) (65 + 26 - rem));
                col /= 26;
            }
        }
        return CV.reverse().toString();
    }
}
package com.example.aaronsandroidlab1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.aaronsandroidlab1.data.MainViewModel;
import com.example.aaronsandroidlab1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.myButton.setOnClickListener(click ->{
            model.editString.postValue(variableBinding.myEditText.getText().toString());

        });
        model.editString.observe(this, s -> {
           variableBinding.myText.setText("Your edit text says: '" + s + "'");
        });

    }

}
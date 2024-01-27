package com.example.aaronsandroidlab1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.widget.Toast;

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


/*      Why does model line not work, specifically postValue parameter
        I assume the isSelected() method is evaluating the state of the switch before the change
        in state is made. so it reads false, the state is then changed to true from the selection
        and then is placed back to false by isSelected because thats what it viewed it as in
        its initial state, is this correct?
        variableBinding.myCheckBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(variableBinding.myCheckBox.isSelected());
        });
*/
        variableBinding.myCheckBox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.myRadioButton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );
        variableBinding.mySwitch.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );



        model.isSelected.observe(this, selected ->{
            Toast.makeText(this, "The value is now: " + selected, Toast.LENGTH_SHORT).show();
            variableBinding.myCheckBox.setChecked(selected);
            variableBinding.myRadioButton.setChecked(selected);
            variableBinding.mySwitch.setChecked(selected);
        });

        variableBinding.myButton.setOnClickListener(click ->{
            model.editString.postValue(variableBinding.myEditText.getText().toString());

        });
        model.editString.observe(this, s -> {
           variableBinding.myText.setText("Your edit text says: '" + s + "'");
        });

        variableBinding.myImageButton.setOnClickListener(v -> {
            Toast.makeText(this,"the width= " + variableBinding.myImageButton.getWidth() + " and the height= " + variableBinding.myImageButton.getHeight(), Toast.LENGTH_SHORT).show();
        });

    }

}
package com.example.codingchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    public static final String ITEM_NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * onClick method attached to each button which will get the text of the button clicked and
     * pass it as an intent extra
     * @param view Button
     */
    public void addItem(View view) {
        Button button = (Button)view;
        String itemName = button.getText().toString();

        Intent addItemIntent = new Intent();
        addItemIntent.putExtra(ITEM_NAME, itemName);
        setResult(RESULT_OK, addItemIntent);
        finish();
    }


}
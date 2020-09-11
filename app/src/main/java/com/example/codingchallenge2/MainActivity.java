package com.example.codingchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * App which will allow the user to construct a shopping list of size 10 or fewer items.
 * This activity displays the current list of items added to the "cart", and contains a button which
 * will launch a second activity displaying items that are available.
 * @author Cory Bradford
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static final int ITEM_REQUEST = 1;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private List<TextView> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference of the 10 TextView objects that have been added to the activity
        TextView item_1 = findViewById(R.id.item_1);
        TextView item_2 = findViewById(R.id.item_2);
        TextView item_3 = findViewById(R.id.item_3);
        TextView item_4 = findViewById(R.id.item_4);
        TextView item_5 = findViewById(R.id.item_5);
        TextView item_6 = findViewById(R.id.item_6);
        TextView item_7 = findViewById(R.id.item_7);
        TextView item_8 = findViewById(R.id.item_8);
        TextView item_9 = findViewById(R.id.item_9);
        TextView item_10 = findViewById(R.id.item_10);

        //adding the TextView object to a List in order to be accessed easily
        itemList.add(item_1);
        itemList.add(item_2);
        itemList.add(item_3);
        itemList.add(item_4);
        itemList.add(item_5);
        itemList.add(item_6);
        itemList.add(item_7);
        itemList.add(item_8);
        itemList.add(item_9);
        itemList.add(item_10);

        //if check used to ensure that items already added to the cart will not be lost whenever the
        //user swaps orientation of the device
        if(savedInstanceState != null) {
            boolean itemsInList = savedInstanceState.getBoolean("itemsInList");
            if(itemsInList) {
                for(int i = 0; i < itemList.size(); i++) {
                    TextView currentTextView = itemList.get(i);
                    currentTextView.setText(savedInstanceState.getString("item_" + i));
                }
            }
        }
    }

    /**
     * function which contains a for loop which will loop through each TextView object in the
     * List and check if it contains text.
     * If so, the text will be stored using outState.putString and a unique identifier to later be
     * pulled if the device swaps orientation.
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for(int i = 0; i < itemList.size(); i++) {
            if(!itemList.get(i).getText().equals("")) {
                outState.putBoolean("itemsInList", true);
                outState.putString("item_" + i, itemList.get(i).getText().toString());
            }
        }
    }

    /**
     * onClick method for the add item button of Main Activity which launches second activity for
     * result.
     * @param view
     */
    public void addItem(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    /**
     * If the user performed an action during the second activity screen, then we will store the
     * name of the button selected and launch the function addToTextView passing the string.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ITEM_REQUEST) {
            if(resultCode == RESULT_OK) {
                String itemName = data.getStringExtra(SecondActivity.ITEM_NAME);
                addToTextView(itemName);
            }
        }
    }

    /**
     * function used to iterate through available TextView objects in itemList until an object with
     * no text is found. At that point the string passed to the function will be set to that object.
     * @param itemName
     */
    public void addToTextView(String itemName) {
        int currentIndex = 0;
        TextView currentTextView = itemList.get(currentIndex);
        String currentTextViewValue = currentTextView.getText().toString();

        while(!currentTextViewValue.equals("") && currentIndex < 10) {
            currentIndex++;
            currentTextView = itemList.get(currentIndex);
            currentTextViewValue = currentTextView.getText().toString();
        }

        currentTextView.setText(itemName);
    }
}
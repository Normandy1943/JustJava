package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.justjava.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hsaChocolate = chocolateBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_edittext);
        String name = nameEditText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hsaChocolate);
        Log.v("MainActivity", "hasWhippedCream " + hasWhippedCream);
        displayMessage(createOrderSummary(price, hasWhippedCream, hsaChocolate, name));
    }

    /**
     * Create summary of the order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hsaChocolate    is whether or not the user wants whipped cream topping
     * @param price           of the order
     * @param name            of the customer
     * @return text summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hsaChocolate, String name) {
        String priceMessage = "Name:" + name + "\n Add: Whippeed Cream?" + hasWhippedCream + "\nAdd:Chocolate?" + hsaChocolate + "\nQuantity:" + quantity +
                "\nTotal:$" + price + "\n Thank you!";
        return priceMessage;
    }


    /**
     * Calculates the price of the order.
     *
     * @param mHasWhippedCream is whether or not the user wants whipped cream topping
     * @param mHsaChocolate    is whether or not the user wants whipped cream topping
     * @return total prices
     */

    private int calculatePrice(boolean mHasWhippedCream, boolean mHsaChocolate) {
      /*  if (mHasWhippedCream == false && mHsaChocolate == false) {
            return quantity * 5;
        } else if (mHasWhippedCream = true && mHsaChocolate == true) {
            return quantity * 8;
        } else if (mHasWhippedCream == true && mHsaChocolate == false) {
            return quantity * 6;
        } else if (mHasWhippedCream == false && mHsaChocolate == true) {
            return quantity * 7;
        } else {
            return 0;
        }*/
        int basePrice = 5;
        if (mHasWhippedCream) {
            basePrice += 1;
        }
        if (mHsaChocolate) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity==100){
            Toast.makeText(this, "最多订100杯", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this, "最少订一杯", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}

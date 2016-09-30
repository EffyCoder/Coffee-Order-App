package com.example.shantanoo_pc.coffee;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

import static java.lang.String.valueOf;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int noOfCoffees=1;
    int pricePerUnit=5;

    /**
     * This method is called when '+' button is clicked.
     * @param view
     */

    public void increaseQuantity(View view)
    {
        if(noOfCoffees == 100)
        {
            Toast toast = Toast.makeText(this, "You cant have more than 100 coffees.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        noOfCoffees++;
        display(noOfCoffees);

    }
    /**
     * This method is called when '-' button is clicked.
     * @param view
     */
    public void decreaseQuantity(View view)
    {
        if(noOfCoffees == 1)
        {
            Toast toast = Toast.makeText(this, "You cant have less than 1 coffee.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        noOfCoffees--;
        display(noOfCoffees);
    }

    /**
     * This method is called when the order button is clicked.
     * @param view
     */
    public void submitOrder(View view) {

        CheckBox WhippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_status);
        CheckBox ChocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        final boolean hasChocolate = ChocolateCheckBox.isChecked();
        final boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        EditText nameOfCustomer = (EditText)findViewById(R.id.nameId);
        final String customersName = valueOf((nameOfCustomer.getText()));
        displayMessage(createOrderSummary(customersName,calculatePrice(hasWhippedCream,hasChocolate),hasWhippedCream,hasChocolate));

    }

    /**
     * calculates the price.
     * @return the price.
     */
    private int calculatePrice(boolean whippedCream,boolean chocolate) {
         int basePrice=pricePerUnit;
        //Add $1 if customer wants whipped cream.
        if (whippedCream) {
            basePrice = basePrice + 1;

        }
        //Add $2 if customer wants chocolate.

        if (chocolate) {
            basePrice = basePrice + 2;
        }

        return basePrice * noOfCoffees;

    }

    /**
     * creates an order summary.
     * @param price
     * @param addWhippedCream is whether user wants or not the whipped cream with coffee as toppings.
     * @param addChocolate is whether user wants or not the chocolate with coffee as toppings.
     * @param customersName is the name of the Customer.
     * @return the order summary.
     */

    private String createOrderSummary(String customersName,int price,boolean addWhippedCream,boolean addChocolate)
    {

        String orderMessage="Name: "+customersName;
        orderMessage+="\nQuantity: "+noOfCoffees;
        orderMessage+="\nAdd whipped cream? "+addWhippedCream;
        orderMessage+="\nAdd Chocolate? "+addChocolate;
        orderMessage+="\nTotal price: $"+price;
        orderMessage+="\nThank you.";
        return orderMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}


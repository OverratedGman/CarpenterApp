package com.gautham.carpenterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

public class QuoteDisplay extends AppCompatActivity {
    public DBHelper db;
    String rawWidth,rawLength, textSpinner, finalFullName, finalAddress, finalPhoneNumber;
    int finalWidth,finalLength,finalArea,finalGripper,finalHours;
    double finalCost;

    public void ONfinalise(View view){

        db.insertNewCustomer(finalFullName,finalAddress,finalPhoneNumber,Integer.toString(finalLength), Integer.toString(finalWidth),Integer.toString(finalArea),textSpinner,Integer.toString(finalGripper),Integer.toString(finalHours),Double.toString(finalCost));
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void shareIntent(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + finalFullName+"\n" + "Address: "+finalAddress+"\n"+"PhoneNumber: "+finalPhoneNumber+"\n"+"Width: "+finalWidth+"\n"+"Length: "+finalLength+"\n"+"Area: "+finalArea+"\n"+"Underlay Type: "+textSpinner+"\n"+"Hours: "+finalHours+"\n"+"Gripper length: "+finalGripper+"\n"+"Cost: "+finalCost);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelper(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             finalFullName = extras.getString("finalFullName");
           finalAddress = extras.getString("finalAddress");
            finalPhoneNumber = extras.getString("finalPhoneNumber");
           finalCost = extras.getDouble("finalCost");
           rawLength = extras.getString("finalrawLength");
            rawWidth = extras.getString("finalrawWidth");
           textSpinner = extras.getString("finalUnderlayType");
            finalArea = extras.getInt("finalArea");
            finalGripper = extras.getInt("finalGripper");
            finalHours = extras.getInt("finalHours");
            finalLength = extras.getInt("finalLength");
            finalWidth = extras.getInt("finalWidth");

        }

        EditText ffn = (EditText) findViewById(R.id.finalFullName);
        EditText fa = (EditText) findViewById(R.id.finalAddress);
        EditText fpn = (EditText) findViewById(R.id.finalPhoneNumber);
        EditText fd = (EditText) findViewById(R.id.finalDimensions);
        EditText fut = (EditText) findViewById(R.id.finalUnderlayType);
        EditText fqc = (EditText) findViewById(R.id.finalQuoteCost);

        ffn.setText(finalFullName);
        fa.setText(finalAddress);
        fpn.setText(finalPhoneNumber);

        fd.setText("Dimensions: "+rawWidth+ "m by "+rawLength+"m");
        fut.setText("Underlay Type: "+textSpinner);
        DecimalFormat df = new DecimalFormat("#.##");
        finalCost= Double.valueOf(df.format(finalCost));
        fqc.setText("Overall Cost: £"+finalCost);


    }


}

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
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
   public String fn,ad,pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

    public void clearOnClickFullName(View view){
        EditText tem = (EditText)findViewById(R.id.nameTF);
        tem.setText("");
    }
    public void clearOnClickAddress(View view){
        EditText tem = (EditText)findViewById(R.id.addressTF);
        tem.setText("");
    }
    public void clearOnClickPhoneNumber(View view){
        EditText tem = (EditText)findViewById(R.id.phoneNumberTF);
        tem.setText("");
    }

    public void next1ButtonClicked(View view){


        EditText fullName   = (EditText)findViewById(R.id.nameTF);
        EditText address   = (EditText)findViewById(R.id.addressTF);
        EditText phoneNumber   = (EditText)findViewById(R.id.phoneNumberTF);
        fn = fullName.getText().toString() ;
        ad =address.getText().toString();
        pn = phoneNumber.getText().toString();
        if(!fn.equals("Full Name - Double tap to clear")&&!ad.equals("Address - Double tap to clear")&&!pn.equals("Phone number - Double tap to clear")&&!fn.equals("")&&!ad.equals("")&&!pn.equals("")) {

            Intent intent = new Intent(this, CarpetInput.class);
            intent.putExtra("finalFullName", fn);
            intent.putExtra("finalAddress",ad);
            intent.putExtra("finalPhoneNumber",pn);

            startActivity(intent);
        }
        else{Toast.makeText(getApplicationContext(), "Please enter your details correctly", Toast.LENGTH_SHORT).show();}
        }

    }



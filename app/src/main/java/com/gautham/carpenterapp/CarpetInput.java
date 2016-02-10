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
import android.widget.Spinner;
import android.widget.Toast;

public class CarpetInput extends AppCompatActivity {
    int Rwidth,Rlength,Rarea;
    int finalGripper,finalHours;
    String rawWidth,rawLength, textSpinner, finalFullName, finalAddress, finalPhoneNumber;
    double finalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpet_input);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            finalFullName = extras.getString("finalFullName");
            finalAddress = extras.getString("finalAddress");
            finalPhoneNumber = extras.getString("finalPhoneNumber");
        }

    }

    public void clearOnClickWidth(View view){
        EditText tem = (EditText)findViewById(R.id.Rwidth);
        tem.setText("");
    }
    public void clearOnClickLength(View view){
        EditText tem = (EditText)findViewById(R.id.Rlength);
        tem.setText("");
    }

    public void next2ButtonClicked(View view) {
        EditText width = (EditText) findViewById(R.id.Rwidth);
        EditText length = (EditText) findViewById(R.id.Rlength);
        rawWidth= width.getText().toString();
        rawLength = length.getText().toString();
        Spinner mySpinner=(Spinner) findViewById(R.id.UnderlayChoser);
        textSpinner = mySpinner.getSelectedItem().toString();
        boolean validInputs1 = false;
        boolean validInputs2 = false;
        boolean validInputs3 = false;
        if (rawWidth.equals("Room width (in m) Double tap to clear")) {validInputs1=false;}
        else if(rawWidth.equals("")){validInputs1=false;}
        else{validInputs1=true;}
        if (rawLength.equals("Room Length (in m) Double tap to clear")) {validInputs2=false;}
        else if(rawLength.equals("")){validInputs2=false;}
        else{validInputs2=true;}
        if(!textSpinner.equals("Please choose your underlay type")){validInputs3=true;}


        if(validInputs1==true && validInputs2 ==true&& validInputs3==true){
            Rwidth = (int) Math.ceil(Double.parseDouble(rawWidth));
            Rlength = (int) Math.ceil(Double.parseDouble(rawLength));
            Rarea = (Rwidth * Rlength);
            int underlayType = 0;
            switch(textSpinner){
                case "Type 1 - £5.99 per sq meter": underlayType =1;
                    break;
                case "Type 2 - £7.99 per sq meter": underlayType =2;
                    break;
                case "Type 3 - £60 per sq meter": underlayType =3;
                    break;
            }

            finalCost =calculateCost(Rarea, underlayType);
            Intent intent = new Intent(this, QuoteDisplay.class);
            intent.putExtra("finalrawWidth",rawWidth);
            intent.putExtra("finalrawLength",rawLength);
            intent.putExtra("finalWidth",Rwidth);
            intent.putExtra("finalLength",Rlength);
            intent.putExtra("finalUnderlayType",textSpinner);
            intent.putExtra("finalCost",finalCost);
            intent.putExtra("finalFullName",finalFullName);
            intent.putExtra("finalAddress",finalAddress);
            intent.putExtra("finalPhoneNumber",finalPhoneNumber);
            intent.putExtra("finalGripper",finalGripper);
            intent.putExtra("finalHours",finalHours);
            intent.putExtra("finalArea",Rarea);
            startActivity(intent);
        }else if(validInputs1==true && validInputs2 ==true&&validInputs3==false)
        {Toast.makeText(getApplicationContext(),"Please choose an underlay from the dropdown list", Toast.LENGTH_SHORT).show();}
        else if (validInputs1==false||validInputs2==false)
        {Toast.makeText(getApplicationContext(),"Please check your room measurements again, they seem to be incorrect", Toast.LENGTH_SHORT).show();}
        else{Toast.makeText(getApplicationContext(),"Please enter room measurements and choose an underlay before continuing", Toast.LENGTH_SHORT).show();}
    }
    public double calculateCost(int area, int underlayType){
      double cost =0;
      double labourCost =65;
      if(area>16){
          double temp = (double)area/16;
          finalHours=(int)Math.ceil(temp);
          labourCost = finalHours*65;
      }
      double carpetCost = area*22.5;
      double underlayCost =0;

            switch(underlayType) {
            case 1:underlayCost = (area*5.99);
                break;
            case 2: underlayCost = (area*7.99);
                break;
            case 3: underlayCost = (area*60);
                break;
        }
        finalGripper =(int)Math.ceil(2*(Double.parseDouble(rawLength)+Double.parseDouble(rawWidth)));
        double gripperCost = finalGripper*1.1;
        cost = labourCost+carpetCost+underlayCost+gripperCost;
        return cost;
    }

}

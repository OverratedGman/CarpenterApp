package com.gautham.carpenterapp;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.lang.*;

public class ViewAll extends AppCompatActivity {
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelper(this);
        int size =db.numberOfRows();
        TextView tf = (TextView) findViewById(R.id.clientTF);
        if(size>0) {
           for (int i = 0; i <= size ; i++) {
                Cursor temp = db.getData(i);
                 String newt = DatabaseUtils.dumpCursorToString(temp);
                tf.setText(tf.getText()+newt);



            }
        }
    }


}

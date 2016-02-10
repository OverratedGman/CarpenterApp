package com.gautham.carpenterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gautham on 09/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CustomerDetails.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    /*public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "fullname";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_RLENGTH = "room length";
    public static final String CONTACTS_COLUMN_RWIDTH = "room width";
    public static final String CONTACTS_COLUMN_RAREA = "room area";
    public static final String CONTACTS_COLUMN_UNDERLAYTYPE = "underlayType";
    public static final String CONTACTS_COLUMN_GRIPPER = "gripper";
    public static final String CONTACTS_COLUMN_HOURS = "hours";
    public static final String CONTACTS_COLUMN_COST = "cost";*/// for reference only

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table contacts " +
                        "(id integer primary key,NameAddressPhoneNumber TEXT,WidthLengthAreaUnderlayHours TEXT ,cost TEXT)"
        );
        //length VARCHAR,width VARCHAR,area VARCHAR,underlayType VARCHAR,gripper VARCHAR,hours VARCHAR,
        //length,width,area, underlayType,gripper,hours,
        //+Rlength+"','"+Rwidth+"','"+Rarea+"','"+UnderlayType+"','"+Gripper+"','"+Hours+"','"
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public boolean insertNewCustomer  (String fullname, String address, String phone, String Rlength, String Rwidth, String Rarea, String UnderlayType,String Gripper,String Hours, String Cost)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String part1 = "Name: " + fullname+"\n" + "Address: "+address+"\n"+"PhoneNumber: "+phone;
        String part2 = "Width: "+Rwidth+"Length: "+Rlength+"Area: "+Rarea+"UnderLay: "+UnderlayType+"Hours: "+Hours;
        String part3 = Cost;
        sqLiteDatabase.execSQL("INSERT INTO contacts(NameAddressPhoneNumber,WidthLengthAreaUnderlayHours,cost) VALUES ('"+part1+"','"+part2+"','"+part3+"')");
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public void deleteContact (Integer id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


}

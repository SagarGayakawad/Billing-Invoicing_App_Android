package com.example.mahe.lab5;

/**
 * Created by student on 24-Feb-17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahe on 2/22/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME="SELLBUY.db";
    //Items Table
    public static final String TABLE_NAME = "iteminfomation";
    public static final String COL1 = "ITEMNO";
    public static final String COL2 = "ITEMNAME";
    public static final String COL3 = "PRICE";
    public static final String COL4 = "STOCKS";

    //Transaction Table
    public static final String TABLE_NAMEO = "tran";
    public static final String COL5 = "ORDERID";
    public  static  final String COL6="ORDERBY";
    public static final String COL7 = "ITEMNO";
    public static final String COL8 = "ITEMNAME";
    public static final String COL9 = "NOOFITEMS";
    public static final String COL10 = "TOTAMT";

    //Table create strings
    public  static  final String trantable="create table iteminfomation(ITEMNO integer primary key autoincrement,ITEMNAME text,PRICE integer,STOCKS integer);";
    public  static  final String itemtable="create table tran(ORDERID integer primary key autoincrement,ORDERBY text," +
            "ITEMNO integer,ITEMNAME text,NOOFITEMS integer,TOTAMT integer,foreign key(ITEMNO) references iteminfomation(ITEMNO));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(trantable);
        db.execSQL(itemtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS iteminfo");
        db.execSQL("DROP TABLE IF EXISTS transaction");
        onCreate(db);
    }

    public boolean insertData(String itemName,Integer price,Integer stocks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL2,itemName);
        values.put(COL3,price);
        values.put(COL4,stocks);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean updateItem(Integer itemNo,String itemName,Integer price,Integer stocks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values1=new ContentValues();
        values1.put(COL1,itemNo);
        values1.put(COL2,itemName);
        values1.put(COL3,price);
        values1.put(COL4,stocks);
        long res=db.update(TABLE_NAME,values1,"ITEMNO=?",new String[]{itemNo.toString()});
        if(res==-1)
            return false;
        else
            return true;
    }

    public Integer delItem(Integer itemNo)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ITEMNO=?",new String[]{itemNo.toString()});
    }

    public Cursor dispItems()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from "+TABLE_NAME,null);
        return  cur;
    }

    public Cursor searchItem(Integer itemNo)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from "+ TABLE_NAME +" where ITEMNO=?",new String[]{itemNo.toString()});
        return  cur;
    }

    public Cursor searchByname(String itemName)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from "+ TABLE_NAME +" where ITEMNAME=?",new String[]{itemName});
        return  cur;
    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public boolean insertData1(String custName,Integer itemNo,String itemName,Integer quantity,Integer Totprice)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL6,custName);
        values.put(COL7,itemNo);
        values.put(COL8,itemName);
        values.put(COL9,quantity);
        values.put(COL10,Totprice);
        long result=db.insert(TABLE_NAMEO,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }

}

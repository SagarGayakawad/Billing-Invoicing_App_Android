package com.example.mahe.lab5;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends Activity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText);
        e3=(EditText)findViewById(R.id.editText2);
        e4=(EditText)findViewById(R.id.editText3);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.delete);
        b3=(Button)findViewById(R.id.display);
        b4=(Button)findViewById(R.id.search);
        updateData();
        delData();
        dispData();
        searData();
    }
    public void updateData()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res=db.updateItem(Integer.parseInt(e1.getText().toString()),e2.getText().toString(),Integer.parseInt(e3.getText().toString()),Integer.parseInt(e4.getText().toString()));
                if(res==true)
                    Toast.makeText(getApplicationContext(),"Data is Updated.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Data is not Updated.",Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void delData()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long res1=db.delItem(Integer.parseInt(e1.getText().toString()));
                if(res1==0)
                    messageShow("ERROR","NO SUCH ITEM EXIST");
                else
                    Toast.makeText(getApplicationContext(),"Data is Deleted.",Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void dispData()
    {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur=db.dispItems();
                if(cur.getCount()==0)
                {
                    messageShow("ERROR","NO DATA ITEMS ARE PRESENT.");
                    return;
                }
                StringBuffer data=new StringBuffer();
                while(cur.moveToNext())
                {
                    data.append("ITEM ID: "+cur.getString(0)+"\n");
                    data.append("ITEM NAME: "+cur.getString(1)+"\n");
                    data.append("ITEM PRICE: "+cur.getString(2)+"\n");
                    data.append("TOTAL STOCKS: "+cur.getString(3)+"\n\n");
                }
                messageShow("ITEMS LIST",data.toString());
            }
        });
    }

    public  void searData()
    {
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur=db.searchItem(Integer.parseInt(e1.getText().toString()));
                if(cur.getCount()==0)
                {
                    messageShow("ALERT","SUCH ITEM NOT PRESENT.");
                    return;
                }
                StringBuffer data1=new StringBuffer();
                while(cur.moveToNext()) {
                    data1.append("ITEM ID: " + cur.getString(0) + "\n");
                    data1.append("ITEM NAME: " + cur.getString(1) + "\n");
                    data1.append("ITEM PRICE: " + cur.getString(2) + "\n");
                    data1.append("TOTAL STOCKS: " + cur.getString(3) + "\n\n");
                }
                messageShow("RESULT",data1.toString());
            }
        });
    }
    public void messageShow(String title,String message)
    {
        AlertDialog.Builder alertbuild=new AlertDialog.Builder(this);
        alertbuild.setTitle(title);
        alertbuild.setMessage(message);
        alertbuild.show();
    }
}

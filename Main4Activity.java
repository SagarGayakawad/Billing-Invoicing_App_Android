package com.example.mahe.lab5;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Main4Activity extends AppCompatActivity {
    Spinner spinner;
    Button b1, b2, b3;
    EditText e1, e2;
    DatabaseHelper db;
    StringBuffer str1 = new StringBuffer();
    Integer grandTot = 0,i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        db = new DatabaseHelper(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        b1 = (Button) findViewById(R.id.puritem);
        b2 = (Button) findViewById(R.id.billbut);
        b3 = (Button) findViewById(R.id.refresh);
        e1 = (EditText) findViewById(R.id.editText5);
        e2 = (EditText) findViewById(R.id.editText6);
        str1.append("ITEM DETAILS:\n\n");
        loadSpinnerData();
        instran();
        cleerAll();
        genbill();
    }

    public void instran() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().toString().trim().length() > 0) {
                    Integer itemno = 0, price = 0, actquant = 0, ordqaunt = 0, nowqunat = 0, totamount = 0;
                    Cursor cur = db.searchByname(spinner.getSelectedItem().toString());
                    while (cur.moveToNext()) {
                        itemno = Integer.parseInt(cur.getString(0));
                        price = Integer.parseInt(cur.getString(2));
                        actquant = Integer.parseInt(cur.getString(3));
                    }
                    ordqaunt = Integer.parseInt(e2.getText().toString());
                    if (actquant > ordqaunt) {  
                        nowqunat = actquant - ordqaunt;
                        totamount = ordqaunt * price;
                        i=i+1;
                        str1.append("\n"+i+ "\t" + spinner.getSelectedItem().toString() + "\t" + ordqaunt.toString() + "\t" + totamount.toString() + "\n");
                        grandTot = grandTot + totamount;
                        boolean res1 = db.updateItem(itemno, spinner.getSelectedItem().toString(), price, nowqunat);
                        if (res1 == true) {
                            boolean res = db.insertData1(e1.getText().toString(), itemno, spinner.getSelectedItem().toString(), ordqaunt, totamount);
                            if (res = true) {
                                Toast.makeText(getApplicationContext(), "Item is Added to List.", Toast.LENGTH_SHORT).show();
                                e2.setText("");
                            }
                            else {
                                messageShow("MESSAGE", "ITEMS IS NOT ADDED.");
                            }
                        }
                        else {
                            messageShow("ERROR","UPDATION ERROR.");
                        }
                    } else {
                        messageShow("ERROR","QUANTITY OF ITEM OREDED IS GREATER THAN ACTUAL QUNATITY.");
                    }
                } else {
                    messageShow("ERROR","ITEM NOT SELECTED.");
                }
            }
        });
    }

    public void cleerAll() {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
                str1 = null;
                grandTot = 0;
            }
        });
    }

    public void genbill() {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str1.append("\nGRAND TOTAL:\t\t\t" + grandTot);
                messageShow("BILL", str1.toString());
                e1.setText("");
                e2.setText("");
                str1 = null;
                grandTot = 0;
            }
        });
    }

    private void loadSpinnerData() {
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    public void messageShow(String title, String message) {
        AlertDialog.Builder alertbuild = new AlertDialog.Builder(this);
        alertbuild.setTitle(title);
        alertbuild.setMessage(message);
        alertbuild.show();
    }
}
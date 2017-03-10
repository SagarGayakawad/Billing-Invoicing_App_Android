package com.example.mahe.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    DatabaseHelper db;
    Button b1;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new DatabaseHelper(this);
        b1=(Button)findViewById(R.id.update);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        AddData();

    }
    public void AddData()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String name=e1.getText().toString();
                Integer price=Integer.parseInt(e2.getText().toString());
                Integer stock=Integer.parseInt(e3.getText().toString());
                boolean result=db.insertData(name,price,stock);
                if(result==true)
                    Toast.makeText(getApplicationContext(),"Data Inserted Succesfully.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Data Not Inserted.",Toast.LENGTH_LONG).show();
            }
        });
    }
}

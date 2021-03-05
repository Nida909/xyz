package com.example.chatting;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class    SignIn extends AppCompatActivity {
   DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String val1;
    int val2,val3,val4,val5;
    Button b1,b2;

    TextView tv,tt,txt1;
    EditText t1,t2;
    String str1,str2,str3;
    boolean bo=false;
    Context context;
    Resources resources;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        dbHelper = new DatabaseHelper(this);
        tv=(TextView)findViewById(R.id.txt1);
        tt=(TextView)findViewById(R.id.txt2);
        t1=(EditText) findViewById(R.id.edt1);
        t2=(EditText) findViewById(R.id.edt2);
        txt1=findViewById(R.id.tt);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

        Intent intent = getIntent();
        String languages = intent.getExtras().getString("language");
        Toast.makeText(this, languages, Toast.LENGTH_SHORT).show();
        if(languages.equals("ENGLISH"))
        {

            context = LocalHelper.setLocale( SignIn.this, "en");
            resources = context.getResources();
            txt1.setText(resources.getString(R.string.signinhere));
            t1.setText(resources.getString(R.string.email));
            t2.setText(resources.getString(R.string.enteryourpassword));
            b1.setText(resources.getString(R.string.signin));
            tv.setText(resources.getString(R.string.question1));
            b2.setText(resources.getString(R.string.account));
            str="ENGLISH";

        }

        if(languages.equals("اردو"))
        {
            context = LocalHelper.setLocale(SignIn.this, "an");
            resources = context.getResources();
            txt1.setText(resources.getString(R.string.signinhere));
            t1.setText(resources.getString(R.string.email));
            t2.setText(resources.getString(R.string.enteryourpassword));
            b1.setText(resources.getString(R.string.signin));
            tv.setText(resources.getString(R.string.question1));
            b2.setText(resources.getString(R.string.account));
            str="اردو";


        }


    }
    public void onbtn1(View v)
    {
        db=dbHelper.getReadableDatabase();
        str1=t1.getText().toString();
        str2=t2.getText().toString();
        if((str1.isEmpty())||(str2.isEmpty()))
        {
            tt.setText("Field must be filled");
            tt.setVisibility(View.VISIBLE);
        }
        else {
            String[] columns= {DatabaseContract.Customers._ID,"Email","Password"};
            String[] values={str1,str2};
            Cursor cr=db.query(DatabaseContract.Customers.TABLE_NAME,columns,"Email=? AND Password=?",values,null,null,null);
                if (cr.getCount() > 0) {
                    cr.moveToFirst();
                    bo = true;
                    str3 = String.valueOf(cr.getLong(0));
                    tt.setText(str3);
                    tt.setVisibility(View.VISIBLE);
                    db.close();
                    Intent intent = new Intent(SignIn.this,MilkManList.class);
                    intent.putExtra("val", str3);
                    startActivity(intent);
                }
                else {
                    tt.setText("Email Address or Password is incorrect ");
                    tt.setVisibility(View.VISIBLE);
                }




        }

    }
    public void onbtn2(View v)
    {

        Intent intent=new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
}
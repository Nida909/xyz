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

public class LogInPage extends AppCompatActivity {
    TextView txt1,txt2;
    Button createaccount;
    EditText name,password;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String val;
    Context context;
    Resources resources;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        dbHelper = new DatabaseHelper(this);
        createaccount=findViewById(R.id.createaccount);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);


        Intent intent = getIntent();
        String languages = intent.getExtras().getString("language");
        Toast.makeText(this, languages, Toast.LENGTH_SHORT).show();
        if(languages.equals("ENGLISH"))
        {

            context = LocalHelper.setLocale(LogInPage.this, "en");
            resources = context.getResources();
            txt1.setText(resources.getString(R.string.login));
            name.setText(resources.getString(R.string.name));
            password.setText(resources.getString(R.string.password));
            txt2.setText(resources.getString(R.string.question1));
            createaccount.setText(resources.getString(R.string.account));
            str="ENGLISH";




        }

        if(languages.equals("اردو"))
        {
            context = LocalHelper.setLocale(LogInPage.this, "an");
            resources = context.getResources();
            txt1.setText(resources.getString(R.string.login));
            name.setText(resources.getString(R.string.name));
            password.setText(resources.getString(R.string.password));
            txt2.setText(resources.getString(R.string.question1));
            createaccount.setText(resources.getString(R.string.account));
            str="اردو";


        }





        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this,CreateMAccount.class));
                finish();
            }
        });

    }
    public void login(View view)
    {
        db=dbHelper.getReadableDatabase();
        if(name.equals("") || password.equals(""))
        {
            Toast.makeText(LogInPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String[] columns= {"Email","Name","Password"};
            String[] values={name.getText().toString(),password.getText().toString()};
            Cursor cursor=db.query("MilkMan",columns,"Name=? AND Password=?",values,null,null,null);
            if (cursor != null)
            {
                if(cursor.moveToFirst())
                {
                   val= cursor.getString(0);
                    Toast.makeText(this, "values "+val, Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(LogInPage.this, AddMilkInfo.class);
                    intent.putExtra("val1",val);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LogInPage.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}
package com.example.chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageNavigation extends AppCompatActivity {
    TextView language_dialog,text1;
    boolean lang_selected;
    Context context;
    Resources resources;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_navigation);
        language_dialog = (TextView)findViewById(R.id.dialog_language);
        text1=(TextView)findViewById(R.id.text1);


        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Language = {"ENGLISH", "اردو"};
                final int checkedItem;
                if(lang_selected)
                {
                    checkedItem=0;
                }else
                {
                    checkedItem=1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(LanguageNavigation.this);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(LanguageNavigation.this,""+which,Toast.LENGTH_SHORT).show();
                                language_dialog.setText(Language[which]);
                                lang_selected= Language[which].equals("ENGLISH");
                                //if user select prefered language as English then
                                if(Language[which].equals("ENGLISH"))
                                {

                                    context = LocalHelper.setLocale(LanguageNavigation.this, "en");
                                    resources = context.getResources();
                                    text1.setText(resources.getString(R.string.language));
                                    str="ENGLISH";


                                }
                                //if user select prefered language as Hindi then
                                if(Language[which].equals("اردو"))
                                {
                                    context = LocalHelper.setLocale(LanguageNavigation.this, "an");
                                    resources = context.getResources();
                                    text1.setText(resources.getString(R.string.language));
                                    str="اردو";



                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
    public void function(View view)
    {
        Intent intent = new Intent(LanguageNavigation.this, MainActivity2.class);
        //intent.putExtra("language",str);
        startActivity(intent);

    }
}
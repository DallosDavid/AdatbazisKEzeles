package com.example.adatbaziskezeles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RogzitesAct extends AppCompatActivity {

    EditText editTextnev,editTextemail,editTextjegy;
    Button btnRogzit,btnVissza;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);

          init();
          inClickLisener();


    }

    private void inClickLisener() {

        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                adatRogzites();
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visza = new Intent(RogzitesAct.this,MainActivity.class);
                startActivity(visza);;
                finish();

            }
        });

    }

    private void adatRogzites() {
        String nev=editTextnev.getText().toString().trim();
        String email=editTextemail.getText().toString().trim();
        String jegy=editTextjegy.getText().toString().trim();

        if (nev.isEmpty()){
            Toast.makeText(this,"Név meg adaás kotelezo",Toast.LENGTH_LONG).show();
            return;
        }
        if (email.isEmpty()){
            Toast.makeText(this,"Email meg adaás kotelezo",Toast.LENGTH_LONG).show();
            return;
        }

        if (jegy.isEmpty()){
        Toast.makeText(this,"jegy meg adaás kotelezo",Toast.LENGTH_LONG).show();
            return;
        }
        Integer jegyszam=Integer.parseInt(jegy);
        if (jegyszam<0 || jegyszam >5)
        {
            Toast.makeText(this,"Jegy 1 ás 5 kozot kell leni",Toast.LENGTH_LONG).show();

        }
       boolean sikeres = adatbazis.adatRogzités(nev,email,jegy);
        if (sikeres)
        {
         Toast.makeText(this,"Sikeres adat rog",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Sikertelen adat rog",Toast.LENGTH_LONG).show();
        }


    }

    private void init() {
        editTextnev= (EditText) findViewById(R.id.edittext_nev_rogzites);
        editTextemail= (EditText)findViewById(R.id.edittext_email_rogzites);
        editTextjegy= (EditText)findViewById(R.id.edittext_erdemjegy_rogzites);

        btnRogzit= (Button) findViewById(R.id.btn_rogzit);
        btnVissza= (Button) findViewById(R.id.btn_vissza_rogzites);
        adatbazis = new DBHelper(RogzitesAct.this);

    }
}
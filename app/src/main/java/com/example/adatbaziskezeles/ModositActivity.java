package com.example.adatbaziskezeles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModositActivity extends AppCompatActivity {
    EditText editTextID, editTextnev,editTextemail,editTextjegy;
    Button btnModosit,btnVissza;
    DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modosit);
        inti();

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visza= new Intent(ModositActivity.this,MainActivity.class);
                startActivity(visza);
                finish();
            }
        });
        btnModosit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adatmodositas();
            }
        });


    }

    private void Adatmodositas() {
        String nev=editTextnev.getText().toString().trim();
        String email=editTextemail.getText().toString().trim();
        String jegy=editTextjegy.getText().toString().trim();
        String id= editTextID.getText().toString().trim();

        if (id.isEmpty()){
            Toast.makeText(this,"ID meg adaás kotelezo",Toast.LENGTH_LONG).show();
            return;
        }
        if (!adatbazis.IdEllen(id))
        {
            Toast.makeText(this, "Nicns iylen id", Toast.LENGTH_SHORT).show();
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
        boolean sikeres = adatbazis.adatMOdositas(id,nev,email,jegy);
        if (sikeres)
        {
            Toast.makeText(this,"Sikeres adat rog",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Sikertelen adat rog",Toast.LENGTH_LONG).show();
        }
    }

    private void inti() {
        editTextID = (EditText) findViewById(R.id.edittext_id_modosit);
        editTextnev= (EditText) findViewById(R.id.edittext_nev_modosit);
        editTextemail= (EditText)findViewById(R.id.edittext_email_modosit);
        editTextjegy= (EditText)findViewById(R.id.edittext_erdemjegy_modosit);

        btnModosit= (Button) findViewById(R.id.btn_modosit);
        btnVissza= (Button) findViewById(R.id.btn_vissza_modosit);
        adatbazis = new DBHelper(ModositActivity.this);

    }
}
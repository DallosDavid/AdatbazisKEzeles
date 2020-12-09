package com.example.adatbaziskezeles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TorlesAct extends AppCompatActivity {

    EditText editTextID;
    Button btn_torles,btn_visza;
    DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torles);
        inti();
        btn_visza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TorlesAct.this,MainActivity.class));
                finish();
            }
        });

        btn_torles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatTorles();
            }
        });
    }

    private void adatTorles() {
        String id= editTextID.getText().toString().trim();
        if (id.isEmpty())
        {
            Toast.makeText(this, "Adjon meg egy ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!adatbazis.IdEllen(id))
        {
            Toast.makeText(this, "Nincs ilyen idju adatok", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatbazis.adatTorels(id)){
            Toast.makeText(this, "Sikeres torles", Toast.LENGTH_SHORT).show();
            return;
        }else
        {
            Toast.makeText(this, "Sikertelen torlés", Toast.LENGTH_SHORT).show();
        }

    }

    private void inti() {
        btn_torles =(Button)findViewById(R.id.btn_torles);
        btn_visza = (Button)findViewById(R.id.btn_vissza_torles);
        editTextID = (EditText) findViewById(R.id.edit_id_torless);
        adatbazis = new DBHelper(TorlesAct.this);
    }
}
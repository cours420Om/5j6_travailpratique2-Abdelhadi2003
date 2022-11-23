package com.example.tp2mejdoubiabdelhadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityGestionBd extends AppCompatActivity {

    Button btn_deconnexion, btn_modifierProfil;
    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_bd);
        bdAuth = FirebaseAuth.getInstance();
        btn_deconnexion = findViewById(R.id.btn_deconnexion);
        btn_modifierProfil = findViewById(R.id.btn_modifierProfil);
        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdAuth.signOut();
                Intent intention = new Intent(ActivityGestionBd.this, MainActivity.class);
                startActivity(intention);
                finish();
            }
        });

        btn_modifierProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(ActivityGestionBd.this, ActivityGestionProfil.class);
                startActivity(intention);
                finish();
            }
        });
    }
}
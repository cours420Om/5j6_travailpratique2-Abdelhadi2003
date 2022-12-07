package com.example.tp2mejdoubiabdelhadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityGestionBd extends AppCompatActivity {

    Button btn_deconnexion, btn_gestionProfil, btn_ajouterCommande, btn_afficherCommande;
    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_bd);
        bdAuth = FirebaseAuth.getInstance();
        btn_deconnexion = findViewById(R.id.btn_deconnexion);
        btn_gestionProfil = findViewById(R.id.btn_gestionProfil);
        btn_ajouterCommande = findViewById(R.id.btn_ajouterCommande);
        btn_afficherCommande = findViewById(R.id.btn_afficherCommande);
        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdAuth.signOut();
                Intent intention = new Intent(ActivityGestionBd.this, MainActivity.class);
                startActivity(intention);
                finish();
            }
        });

        btn_gestionProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(ActivityGestionBd.this, ActivityGestionProfil.class);
                startActivity(intention);
            }
        });

        btn_ajouterCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention_ajouterCommande = new Intent(ActivityGestionBd.this, ActivityAjouterCommande.class);
                startActivity(intention_ajouterCommande);
            }
        });

        btn_afficherCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(ActivityGestionBd.this, ActivityGestionProfil.class);
                startActivity(intention);
            }
        });
    }
}
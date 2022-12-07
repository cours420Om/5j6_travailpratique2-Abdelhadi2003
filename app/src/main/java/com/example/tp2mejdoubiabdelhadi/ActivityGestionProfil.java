package com.example.tp2mejdoubiabdelhadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityGestionProfil extends AppCompatActivity {

    Button btn_sauvegarder;
    FirebaseAuth bdAuth;
    EditText et_modificationCourriel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_profil);
        btn_sauvegarder = findViewById(R.id.btn_sauvegarder);
        bdAuth = FirebaseAuth.getInstance();
        et_modificationCourriel = findViewById(R.id.et_modificationCourriel);
        et_modificationCourriel.setText(bdAuth.getCurrentUser().getEmail());
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdAuth.getCurrentUser().updateEmail(et_modificationCourriel.getText().toString());
                Toast.makeText(getApplicationContext(), "Vos modifications sont sauvegardées", Toast.LENGTH_LONG).show();
                Intent intentionProfil = new Intent(ActivityGestionProfil.this, ActivityGestionBd.class);
                startActivity(intentionProfil);
            }
        });
    }
}
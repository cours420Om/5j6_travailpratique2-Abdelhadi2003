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
import com.google.firebase.auth.UserProfileChangeRequest;

public class ActivityGestionProfil extends AppCompatActivity {

    Button btn_sauvegarder;
    FirebaseAuth bdAuth;
    EditText et_modificationCourriel, et_nomComplet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_profil);
        btn_sauvegarder = findViewById(R.id.btn_sauvegarder);
        bdAuth = FirebaseAuth.getInstance();
        et_modificationCourriel = findViewById(R.id.et_modificationCourriel);
        et_nomComplet = findViewById(R.id.et_nomComplet);
        et_modificationCourriel.setText(bdAuth.getCurrentUser().getEmail());
        et_nomComplet.setText(bdAuth.getCurrentUser().getDisplayName());
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdAuth.getCurrentUser().updateEmail(et_modificationCourriel.getText().toString());
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(et_nomComplet.getText().toString())
                        .build();
                bdAuth.getCurrentUser().updateProfile(profileUpdates);
                Toast.makeText(getApplicationContext(), "Vos modifications sont sauvegardées", Toast.LENGTH_LONG).show();
                Intent intentionProfil = new Intent(ActivityGestionProfil.this, ActivityGestionBd.class);
                startActivity(intentionProfil);
            }
        });
    }
}
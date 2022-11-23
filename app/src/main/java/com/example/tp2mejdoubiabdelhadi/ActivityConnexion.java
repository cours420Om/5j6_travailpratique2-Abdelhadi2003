package com.example.tp2mejdoubiabdelhadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityConnexion extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp;
    Button btn_seconnecter;
    FirebaseAuth bdAuth;
    Dialog bteDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        tiet_courriel = findViewById(R.id.tiet_courriel);
        tiet_mdp = findViewById(R.id.tiet_mdp);
        btn_seconnecter = findViewById(R.id.btn_seconnecter);

        bdAuth = FirebaseAuth.getInstance();

        btn_seconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();

                if(Patterns.EMAIL_ADDRESS.matcher(courriel).matches() && mdp.length() >= 10) {
                    bdAuth.signInWithEmailAndPassword(courriel, mdp)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        FirebaseUser usager = bdAuth.getCurrentUser();
                                        Intent intention = new Intent(ActivityConnexion.this, ActivityGestionBd.class);
                                        startActivity(intention);
                                        finish();
                                    } else {
                                        Toast.makeText(ActivityConnexion.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else if(!Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                    tiet_courriel.setError("Veuillez entrer un courriel valide");
                    tiet_courriel.requestFocus();
                } else {
                    tiet_mdp.setError("Le mot de passe doit avoir 10 caractères et plus");
                    tiet_mdp.requestFocus();
                }
            }
        });

    }
}
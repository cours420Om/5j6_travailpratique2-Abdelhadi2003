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

public class MainActivity extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp, tiet_mdpConfirmation;
    Button btn_inscription, btn_connexion;
    FirebaseAuth bdAuth;
    Dialog bteDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiet_courriel = findViewById(R.id.tiet_courriel);
        tiet_mdp = findViewById(R.id.tiet_mdp);
        tiet_mdpConfirmation = findViewById(R.id.tiet_confirmationMdp);
        btn_inscription = findViewById(R.id.btn_inscription);
        btn_connexion = findViewById(R.id.btn_connexion);

        bdAuth = FirebaseAuth.getInstance();

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConnexion = new Intent(MainActivity.this, ActivityConnexion.class);
                startActivity(intentConnexion);
            }
        });

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();
                String mdp_confirmation = tiet_mdpConfirmation.getText().toString();

                if(Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                    if(mdp.matches(mdp_confirmation) && mdp.length() >= 10) {
                        //  InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        //  imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        bdAuth.createUserWithEmailAndPassword(courriel, mdp)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Utilisateur créé", Toast.LENGTH_SHORT).show();
                                            FirebaseUser usager = bdAuth.getCurrentUser();
                                            if(usager!=null) {
                                                Intent intention = new Intent(MainActivity.this, ActivityGestionBd.class);
                                                startActivity(intention);
                                                finish();
                                            }
                                        } else {
                                            Toast.makeText(MainActivity.this, "Erreur d'authentification", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else if(mdp.length() < 10) {
                        tiet_mdp.setError("Le mot de passe doit contenir 10 caractères et plus");
                        tiet_mdp.requestFocus();
                    } else {
                        tiet_mdpConfirmation.setError("Les mots de passe sont différents");
                        tiet_mdpConfirmation.requestFocus();
                    }
                } else {
                    tiet_courriel.setError("Veuillez entrer un courriel valide");
                    tiet_courriel.requestFocus();
                }
            }
        });

    }
}

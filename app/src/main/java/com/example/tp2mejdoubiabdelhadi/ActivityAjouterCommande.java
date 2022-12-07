package com.example.tp2mejdoubiabdelhadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tp2mejdoubiabdelhadi.databinding.ActivityAjouterCommandeBinding;
import com.example.tp2mejdoubiabdelhadi.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityAjouterCommande extends AppCompatActivity {

    ActivityAjouterCommandeBinding binding;
    String nomPlat, descriptionIngredient, boisson, sauce;

    FirebaseDatabase bd;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ajouter_commande);
        binding = ActivityAjouterCommandeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSauvegarderCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomPlat = binding.etNomPlat.getText().toString();
                descriptionIngredient = binding.etDescriptionIngredient.getText().toString();
                boisson = binding.etBoisson.getText().toString();
                sauce = binding.etSauce.getText().toString();

                if(!nomPlat.isEmpty() && !descriptionIngredient.isEmpty() && !sauce.isEmpty() && !boisson.isEmpty()) {
                    Commande usager = new Commande(nomPlat, descriptionIngredient, boisson, sauce);
                    bd = FirebaseDatabase.getInstance();
                    ref = bd.getReference("Commandes");
                    ref.child(nomPlat).setValue(usager)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    binding.etNomPlat.setText("");
                                    binding.etDescriptionIngredient.setText("");
                                    binding.etBoisson.setText("");
                                    binding.etSauce.setText("");
                                    Toast.makeText(ActivityAjouterCommande.this, "Commande ajoutée", Toast.LENGTH_SHORT).show();
                                    Intent intention = new Intent(ActivityAjouterCommande.this, ActivityGestionBd.class);
                                    startActivity(intention);
                                    finish();
                                }
                            });
                } else {
                    Toast.makeText(ActivityAjouterCommande.this, "Vous ne devez pas laisser des champs vides dans votre commande", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
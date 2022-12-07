package com.example.tp2mejdoubiabdelhadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp2mejdoubiabdelhadi.databinding.ActivityAfficherCommandeBinding;
import com.example.tp2mejdoubiabdelhadi.databinding.ActivityAjouterCommandeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityAfficherCommande extends AppCompatActivity {

    private ListView lv_commande;
    private ArrayList<Commande> listeCommande;
    DatabaseReference reference;
    Button btn_supprimer;
    ActivityAfficherCommandeBinding binding;
    String nomPlat, descriptionIngredient, boisson, sauce;
    FirebaseDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAfficherCommandeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_afficher_commande);
        btn_supprimer = findViewById(R.id.btn_supprimer);
        lv_commande = findViewById(R.id.lv_commande);
        listeCommande = new ArrayList<Commande>();
        initialisationListe();

        binding.btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_commande.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        lv_commande.setSelector(android.R.color.holo_orange_light);
                        //reference = FirebaseDatabase.getInstance().getReference("Commandes");
                        bd = FirebaseDatabase.getInstance();
                        reference = bd.getReference("Commandes");

                       //reference.child(usager).removeValue()
                        reference.child(nomPlat).removeValue()
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(ActivityAfficherCommande.this, "La commande a été supprimé", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ActivityAfficherCommande.this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
                                        }
                                   }
                               });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }

    private void initialisationListe() {
        final ArrayAdapter<Commande> adapter = new ArrayAdapter<Commande>(this,
                android.R.layout.simple_list_item_1, listeCommande);
        reference = FirebaseDatabase.getInstance().getReference("Commandes");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listeCommande.add(snapshot.getValue(Commande.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                listeCommande.remove(snapshot.getValue(Commande.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv_commande.setAdapter(adapter);
    }
}
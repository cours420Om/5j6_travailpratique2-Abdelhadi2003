package com.example.tp2mejdoubiabdelhadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    String nomPlat;
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

        lv_commande.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomPlat = lv_commande.getItemAtPosition(i).toString();

            }
        });

        binding.btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerteDialogueBuilder = new AlertDialog.Builder(ActivityAfficherCommande.this);
                alerteDialogueBuilder.setMessage("Êtes-vous sur de vouloir supprimer cette commande?");

                alerteDialogueBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bd = FirebaseDatabase.getInstance();
                        reference = bd.getReference("Commandes");
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
                });
                alerteDialogueBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialogue = alerteDialogueBuilder.create();
                alertDialogue.show();

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
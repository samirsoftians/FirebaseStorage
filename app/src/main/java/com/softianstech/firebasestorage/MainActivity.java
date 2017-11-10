package com.softianstech.firebasestorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    Spinner spinner;

    DatabaseReference databaseReference;

    ListView listView;

    List<Artist> artistList;

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();

                for (DataSnapshot artistSnapshort : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshort.getValue(Artist.class);
                    artistList.add(artist);
                }


                ArtistList adaptor =new ArtistList(MainActivity.this,artistList);
                listView.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=(ListView) findViewById(R.id.list_item);
        databaseReference= FirebaseDatabase.getInstance().getReference("artists");

        button=(Button) findViewById(R.id.button);
        spinner=(Spinner) findViewById(R.id.spinner);
        editText=(EditText) findViewById(R.id.editText);

        artistList= new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addArtist();

            }
        });


    }

    private void addArtist()
    {
        String name=editText.getText().toString();
        String genere=spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name))
        {
            String id =databaseReference.push().getKey();
            Artist artist=new Artist(id,name,genere);
            databaseReference.child(id).setValue(artist);
            Toast.makeText(this, "added successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "please enter a name", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.softianstech.firebasestorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    Spinner spinner;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("artists");

        button=(Button) findViewById(R.id.button);
        spinner=(Spinner) findViewById(R.id.spinner);
        editText=(EditText) findViewById(R.id.editText);

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

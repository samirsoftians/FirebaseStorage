package com.softianstech.firebasestorage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 11/11/2017.
 */

public class ArtistList extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context,List<Artist> artistList)
    {
        super(context,R.layout.list_layout,artistList);
this.context=context;
this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listviewitem=layoutInflater.inflate(R.layout.list_layout,null,true);

        TextView name=(TextView) listviewitem.findViewById(R.id.name);
        TextView genere=(TextView) listviewitem.findViewById(R.id.genere);

        Artist artist=artistList.get(position);

        name.setText(artist.getName());
        genere.setText(artist.getGenre());

        return listviewitem ;
    }
}

package com.example.udomiba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Pet> petList;

    public CustomListViewAdapter(Context context, List<Pet> petList) {
        this.context = context;
        this.petList = petList;
    }

    @Override
    public int getCount() {
        return petList.size();
    }

    @Override
    public Object getItem(int i) {
        return petList.get(i);
    }

    /***
     * possible error here
     */

    @Override
    public long getItemId(int i) {
        return petList.indexOf(petList.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list, viewGroup, false);
        Pet pet = petList.get(i);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView description = (TextView) view.findViewById(R.id.description);

        image.setImageResource(pet.getPhoto());
//        image.setImageURI();
        name.setText(pet.getName());
        description.setText(pet.getDescription());

        return view;
    }
}

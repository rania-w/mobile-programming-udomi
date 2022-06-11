package com.example.udomiba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class MissingPetsFragment extends Fragment {

    ListView listView;
    SearchView searchView;
    public static final String EXTRA_ID="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_missing, container, false);

        /*listView= view.findViewById(R.id.searchresults);
        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(getActivity(), searchResults());
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(onItemClickListener);*/
        return view;
    }

    /*private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Pet pet = (Pet) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), ShowPet.class);
            intent.putExtra(EXTRA_ID, pet.getPetId());
            startActivity(intent);
        }
    };*/

    /*public List<Pet> searchResults(){
        List<Pet> petList = UdomiDatabase.getInstance(getContext()).petDAO().getSpecies(searchView.getQuery().toString());
        return petList;
    }*/
}

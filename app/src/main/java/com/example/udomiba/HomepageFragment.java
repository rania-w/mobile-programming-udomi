package com.example.udomiba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class HomepageFragment extends Fragment {
    public static final String EXTRA_ID = "";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String BIRTHDATE = "EXTRA_BIRTHDATE";
    public static final String VACCINATED = "EXTRA_VACCINATED";
    public static final String GENDER = "EXTRA_GENDER";
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.list_view_container);

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(getActivity(), getPetList());
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Pet pet = (Pet) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), ShowPet.class);
            intent.putExtra(EXTRA_ID, pet.getPetId());


            startActivity(intent);
        }
    };

    private List<Pet> getPetList() {

        List<Pet> petList = UdomiDatabase.getInstance(getActivity()).petDAO().getAll();
        return petList;
    }


}

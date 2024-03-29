package com.example.udomiba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyPetsFragment extends Fragment {
    ListView listView;
    Button button;

    public static final String EXTRA_ID = "";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String BIRTHDATE = "EXTRA_BIRTHDATE";
    public static final String VACCINATED = "EXTRA_VACCINATED";
    public static final String GENDER = "EXTRA_GENDER";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mypets, container, false);

        listView = view.findViewById(R.id.my_pets_list);

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(getActivity(), getPetList());
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(onItemClickListener);

        button = view.findViewById(R.id.add_pet_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAddPet();
            }
        });
        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Pet pet = (Pet) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), MyPet.class);
            intent.putExtra(EXTRA_ID, pet.getPetId());


            startActivity(intent);
        }
    };

    private List<Pet> getPetList() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        int userId = bundle.getInt(LoginPage.EXTRA_ID);
        List<Pet> petList = UdomiDatabase.getInstance(getContext()).petDAO().getMine(userId);

        return petList;
    }

    public void moveToAddPet() {
        Intent intent;
        intent = new Intent(getActivity(), AddPet.class);
        Intent prev = getActivity().getIntent();
        Bundle b = prev.getExtras();
        int uid = b.getInt(String.valueOf(LoginPage.EXTRA_ID));
        intent.putExtra(EXTRA_ID, uid);
        startActivity(intent);
    }
}

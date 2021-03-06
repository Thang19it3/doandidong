package com.example.googlemap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class mapFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterTT myAdapter;
    ArrayList<TinTuc> list;
    Button btt;
    TinTuc tinTuclist;

    public mapFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        btt = (Button) view.findViewById(R.id.buttontt);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleviewtt);
        database = FirebaseDatabase.getInstance().getReference("news");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapter = new MyAdapterTT(getActivity(),list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TinTuc t = dataSnapshot.getValue(TinTuc.class);
                    list.add(t);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangbai();
            }
        });
        return view;
    }


    public void dangbai() {
        ThemTTFragment fr123 = new ThemTTFragment();
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr123);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
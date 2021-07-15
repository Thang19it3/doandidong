package com.example.googlemap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemTTFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemTTFragment extends Fragment implements ValueEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btdang;
    private EditText edcontent;
    private TextView namedt;
    private String content;
    private DatabaseReference mDatabase,reference;
    ArrayList<TinTuc> list;

    public ThemTTFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThemTTFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThemTTFragment newInstance(String param1, String param2) {
        ThemTTFragment fragment = new ThemTTFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_t_t, container, false);
        btdang = (Button) view.findViewById(R.id.button4);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        namedt = (TextView) view.findViewById(R.id.nameedt);
        edcontent = (EditText) view.findViewById(R.id.contentTT);
        btdang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangbai1();
            }
        });
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(this);
        return view;
    }

    public void dangbai1() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        String postday = sdf1.format(new Date());
        content = edcontent.getText().toString();
        mDatabase = FirebaseDatabase.getInstance().getReference("news");
        String newsId = mDatabase.push().getKey();
        TinTuc tinTuc =
                new TinTuc(newsId,
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),content,postday,
                        namedt.getText().toString()
                );
        mDatabase.child(newsId).setValue(tinTuc);

        mapFragment fr = new mapFragment();
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        User userprofile = snapshot.getValue(User.class);
        if(userprofile != null)
        {
            String fullname = userprofile.fullname;
            namedt.setText(fullname);
        }
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {
        Toast.makeText(getActivity(),"fiald",Toast.LENGTH_LONG).show();
    }
}
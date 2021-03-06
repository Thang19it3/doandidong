package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFra extends Fragment implements View.OnClickListener, ValueEventListener {


    private static FirebaseUser user;
    private static DatabaseReference reference;
    private static  String userId;
    private TextView name;
    private TextInputEditText fullname1,age1,email1,pass;
    private Button button,imageBtn,button2;
    public ProfileFra() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile2, container, false);
        Button btn = (Button)v.findViewById(R.id.logout);
        button = (Button) v.findViewById(R.id.button3);
        button2 = (Button) v.findViewById(R.id.lichsu);
        btn.setOnClickListener(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId= user.getUid();
        name = (TextView) v.findViewById(R.id.fullnamepr);
        fullname1 = (TextInputEditText) v.findViewById(R.id.fullname);
        age1 = (TextInputEditText) v.findViewById(R.id.age);
        email1 = (TextInputEditText) v.findViewById(R.id.email);
        pass = (TextInputEditText) v.findViewById(R.id.pass);
        imageBtn = (Button) v.findViewById(R.id.image) ;
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Image.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullname1.getText().toString().isEmpty() || age1.getText().toString().isEmpty() || email1.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),"Kh??ng ???????c ????? tr???ng",Toast.LENGTH_LONG).show();
                    return;
                }
                FirebaseAuth.getInstance().getCurrentUser().updateEmail(email1.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                                User user1 = new User(fullname1.getText().toString(),age1.getText().toString(),email1.getText().toString()) ;
                                mDatabase.child(user.getUid()).setValue(user1);
                                if(pass.getText().toString().isEmpty() == false) {
                                    FirebaseAuth.getInstance().getCurrentUser().updatePassword(pass.getText().toString());
                                }
                                Toast.makeText(getActivity(), "C???p nh???p th??nh c??ng", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "C???p nh???p th???t b???i", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        reference.child(userId).addListenerForSingleValueEvent(this);
        return  v;
    }

    public void lichsu() {
        BookingFragment fr123 = new BookingFragment();
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr123);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(),Login_viethan.class));
    }

    @Override
    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        User userprofile = snapshot.getValue(User.class);
        if(userprofile != null)
        {
            String fullname = userprofile.fullname;
            String email = userprofile.email;
            String age = userprofile.age;
            name.setText(fullname);
            fullname1.setText(fullname);
            age1.setText(age);
            email1.setText(email);
        }
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {
        Toast.makeText(getActivity(),"fiald",Toast.LENGTH_LONG).show();
    }

}
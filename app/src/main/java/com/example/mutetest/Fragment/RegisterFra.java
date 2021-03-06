package com.example.mutetest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mutetest.Activity.VerifyOTP;
import com.example.mutetest.ConnectionClass;
import com.example.mutetest.R;
import com.example.mutetest.otherfiles.SmsBackgrond;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFra extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Activity referenceActivity;
    View parentHolder;
    EditText name,mobile,password;
    Spinner gender;
    Button register;
    ConnectionClass connectionClass=new ConnectionClass();
    int size=0;

    public RegisterFra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFra.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFra newInstance(String param1, String param2) {
        RegisterFra fragment = new RegisterFra();
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
        referenceActivity=getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_register, container,false);

        name=(EditText) parentHolder.findViewById(R.id.name);
        gender=(Spinner)parentHolder.findViewById(R.id.gender);
        mobile=(EditText)parentHolder.findViewById(R.id.mobilenumber);
        password=(EditText)parentHolder.findViewById(R.id.password);
        register=(Button)parentHolder.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length()<4){
                    Toast.makeText(referenceActivity,"Your name must cantain 4 latter",Toast.LENGTH_SHORT).show();
                }else if(gender.getSelectedItem().toString().equals("select gender")){
                    Toast.makeText(referenceActivity,"Please select your gender",Toast.LENGTH_SHORT).show();
                }else if(mobile.getText().length()!=10){
                    Toast.makeText(referenceActivity,"Invalid number!!",Toast.LENGTH_SHORT).show();
                }else if(password.getText().length()<8 || password.getText().length()>12){
                    Toast.makeText(referenceActivity,"your password must in between 8 to 12 digits or latter",Toast.LENGTH_SHORT).show();
                }else {
                    Random random = new Random();
                    String otp = String.format("%04d", random.nextInt(10000));
                    SmsBackgrond backgroudWorker = new SmsBackgrond(getContext());
//                    Toast.makeText(referenceActivity,otp,Toast.LENGTH_SHORT).show();
                    backgroudWorker.execute(mobile.getText().toString(), otp);
                    Intent i = new Intent(referenceActivity, VerifyOTP.class);
                    i.putExtra("name", name.getText().toString());
                    i.putExtra("gender", gender.getSelectedItem().toString());
                    i.putExtra("mobile", mobile.getText().toString());
                    i.putExtra("password", password.getText().toString());
                    i.putExtra("otp", otp);
                    i.putExtra("activity","Home");
                    referenceActivity.startActivity(i);
                    referenceActivity.finish();
                }
            }
        });

        // Inflate the layout for this fragment
        return parentHolder;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

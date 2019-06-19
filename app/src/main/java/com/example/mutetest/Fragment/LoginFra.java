package com.example.mutetest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutetest.Activity.ForgetPassword;
import com.example.mutetest.Activity.Home;
import com.example.mutetest.ConnectionClass;
import com.example.mutetest.R;
import com.example.mutetest.otherfiles.SharedPreferencesUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFra extends Fragment implements View.OnClickListener {
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
    EditText mobile,password;
    TextView forget;
    Button login;
    ImageView google,facebook,insta;
    ConnectionClass connectionClass=new ConnectionClass();
    SharedPreferencesUser user;

    public LoginFra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFra.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFra newInstance(String param1, String param2) {
        LoginFra fragment = new LoginFra();
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
        // Inflate the layout for this fragment
        referenceActivity=getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_login, container,false);
        mobile=(EditText)parentHolder.findViewById(R.id.mobilenumber);
        password=(EditText)parentHolder.findViewById(R.id.password);
        forget=(TextView)parentHolder.findViewById(R.id.forget);
        login=(Button)parentHolder.findViewById(R.id.login);
        google=(ImageView)parentHolder.findViewById(R.id.google);
        facebook=(ImageView)parentHolder.findViewById(R.id.facebook);
        insta=(ImageView)parentHolder.findViewById(R.id.insta);
        user=new SharedPreferencesUser(referenceActivity);

        google.setOnClickListener(this);
        facebook.setOnClickListener(this);
        insta.setOnClickListener(this);
        forget.setOnClickListener(this);

        login.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                try{
                    Connection connection=connectionClass.CONN();
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery("select * from users where user_mobile='"+ mobile.getText().toString() + "' and password='"+password.getText().toString()+"'");
                    if(resultSet.next()){
                        user.setName(resultSet.getString(2));
                        user.setMobile(resultSet.getString(3));
                        user.setGender(resultSet.getString(5));
                        user.setOtp(resultSet.getString(6));
                        Intent gotohome=new Intent(referenceActivity, Home.class);
                        referenceActivity.startActivity(gotohome);
                        referenceActivity.finish();
                    }else{
                        Toast.makeText(getActivity(),"Incurrect Username password",Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLException ex){
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget:
                Intent gotoforget=new Intent(referenceActivity, ForgetPassword.class);
                referenceActivity.startActivity(gotoforget);
                referenceActivity.finish();
                break;
                default:
                    Toast.makeText(getActivity(),"Sorry this feature is not working !! we will add it as soon as posible",Toast.LENGTH_SHORT).show();
        }

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

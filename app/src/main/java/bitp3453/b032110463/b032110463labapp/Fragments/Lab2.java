package bitp3453.b032110463.b032110463labapp.Fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import bitp3453.b032110463.b032110463labapp.Lab2Camera;
import bitp3453.b032110463.b032110463labapp.R;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab2Binding;

//Lab 2 Excercise converted from activity to fragment to ease navigation
public class Lab2 extends Fragment {
    FragmentLab2Binding binding;
    byte[] img = null;
    public Lab2() {
        // Required empty public constructor
    }
    public Lab2(byte[] img){
        this.img = img;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLab2Binding.inflate(inflater,container,false);
        if(img !=null){
            Log.d("gotimgg","s");
            binding.img1.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        }
        binding.btnClickMe.setOnClickListener(this::fnClickBtn1);
        binding.btnCam.setOnClickListener(this::btnNextClick);
        return binding.getRoot();
    }

    public void fnClickBtn1(View view) {
        String name = binding.txtInName.getText().toString();
        int year = Integer.parseInt(binding.txtInYearOfBirth.getText().toString());
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = curYear - year;
        binding.txtVwAge.setText("Welcome " + name + " born in " + year + "\nCurrent Age: " + age );
    }

    public void btnNextClick(View view){
        Intent intent = new Intent(getActivity(), Lab2Camera.class);
        String strMsg = binding.txtInName.getText().toString();
        intent.putExtra("varStr1",strMsg);
        startActivity(intent);
    }
}
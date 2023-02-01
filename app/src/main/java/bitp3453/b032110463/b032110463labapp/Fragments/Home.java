package bitp3453.b032110463.b032110463labapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bitp3453.b032110463.b032110463labapp.R;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentHomeBinding;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab2Binding;

public class Home extends Fragment {
    FragmentHomeBinding binding;
    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
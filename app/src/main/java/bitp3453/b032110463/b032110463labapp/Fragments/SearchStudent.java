package bitp3453.b032110463.b032110463labapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bitp3453.b032110463.b032110463labapp.Model.Student;
import bitp3453.b032110463.b032110463labapp.R;
import bitp3453.b032110463.b032110463labapp.SQlite.DBStudent;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab7Binding;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentSearchStudentBinding;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchStudent extends Fragment {
    FragmentSearchStudentBinding binding;
    DBStudent dbStudent;

    public SearchStudent() {
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
        binding = FragmentSearchStudentBinding.inflate(inflater,container,false);
        dbStudent = new DBStudent(getContext());
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    searchStud();
                } catch (Exception e) {
                    e.printStackTrace();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Not Found")
                            .setContentText("No student with no. : "+ binding.edtStudID.getText().toString())
                            .show();
                }
            }
        });
        return binding.getRoot();
    }
    private void searchStud() throws Exception {
        Student res = dbStudent.getStudent(binding.edtStudID.getText().toString());
        binding.txtVwStudName2.setText(res.getStrFullname());
        binding.txtVwStudGender.setText(res.getStrGender());
        binding.txtVwStudNo.setText(res.getStrStudNo());
        binding.txtVwStudState.setText(res.getStrState());
    }
}
package bitp3453.b032110463.b032110463labapp.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import bitp3453.b032110463.b032110463labapp.Classes.StudentAdapter;
import bitp3453.b032110463.b032110463labapp.Model.Student;
import bitp3453.b032110463.b032110463labapp.R;
import bitp3453.b032110463.b032110463labapp.SQlite.DBStudent;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab2Binding;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab4Binding;
import cn.pedant.SweetAlert.SweetAlertDialog;


//Lab 4 + 5
public class Lab4 extends Fragment implements TextWatcher {
    DatePickerDialog datePicker;
    private FragmentLab4Binding binding;
    private DBStudent dbStudent;

    private Student student;
    private Vector<Student> students;
    private StudentAdapter studentAdapter;
    public Lab4() {
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
        binding = FragmentLab4Binding.inflate(inflater,container,false);

        //validation starts
        binding.edtFullName.addTextChangedListener(this);
        binding.edtStudentNumber.addTextChangedListener(this);
        binding.edtEmail.addTextChangedListener(this);
        binding.edtBirthDate.addTextChangedListener(this);
        binding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View view, View view1) {
                if(view instanceof EditText){
                    if(((EditText) view).getText().toString().isEmpty()){
                        ((EditText) view).setError("this field must not be empty");
                    }
                }
            }
        });

        binding.radGgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                validateStudent();
            }
        });
        binding.spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validateStudent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                validateStudent();
            }
        });
        //validation end

        //date picker listeners
        binding.edtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInvokeDatePicker();
            }
        });
        binding.edtBirthDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)   fnInvokeDatePicker();
            }
        });

        binding.fabAddStudent.setEnabled(false);
        binding.fabAddStudent.setOnClickListener(this::fnAddStudent);


        //recycler view setup
        students = new Vector<>();
        studentAdapter = new StudentAdapter(getLayoutInflater(),students);
        binding.rcvStudent.setAdapter(studentAdapter);
        binding.rcvStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Confirm Delete?")
                        .setContentText("Student: " + students.get(viewHolder.getAdapterPosition()).getStrFullname() + "("+students.get(viewHolder.getAdapterPosition()).getStrStudNo()+")")
                        .setCancelText("Yes")
                        .setConfirmText("No")
                        .showCancelButton(true)
                        .setCancelClickListener(
                                new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dbStudent.fnDelStudent(students.get(viewHolder.getAdapterPosition()).getStrStudNo());
                                        students.remove(viewHolder.getAdapterPosition());
                                        studentAdapter.notifyDataSetChanged();
                                        sweetAlertDialog.dismiss();
                                    }
                                }
                        ) // reverse sbb somehow kiri die cancel button
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                studentAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
        });
        itemTouchhelper.attachToRecyclerView(binding.rcvStudent);
        dbStudent = new DBStudent(getActivity());
        loadStudents();
        return binding.getRoot();
    }


    private void fnAddStudent(View view){
        String fullname = binding.edtFullName.getText().toString();
        String studNo = binding.edtStudentNumber.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String birth = binding.edtBirthDate.getText().toString();
        String state = binding.spState.getSelectedItem().toString();
        String gender = "";
        if(binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        student = new Student(fullname,studNo,email,gender,birth,state);
        float res = dbStudent.fnInsertStudent(student);
        if(res == -1.0){
            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Invalid registration")
                    .show();
        }

        loadStudents();
        //students.add(student);
       // studentAdapter.notifyItemInserted(students.size());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        validateStudent();
    }
    private void validateStudent(){
        System.out.println (String.valueOf( binding.spState.getSelectedItemPosition()));
        if (!binding.edtFullName.getText().toString().isEmpty() &&
                !binding.edtStudentNumber.getText().toString().isEmpty() &&
                !binding.edtBirthDate.getText().toString().isEmpty() &&
                !binding.edtEmail.getText().toString().isEmpty() &&
                (binding.rbMale.isChecked() || binding.rbFemale.isChecked()) &&
                binding.spState.getSelectedItemPosition() != 0
        ) {
            binding.fabAddStudent.setEnabled(true);
        }
        else{
            binding.fabAddStudent.setEnabled(false);
        }
    }

    private void fnInvokeDatePicker(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                binding.edtBirthDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                binding.edtBirthDate.setError(null);
            }
        }, year, month, day);

        datePicker.show();

    }


    private void loadStudents(){
        students.clear();
        List<Student> tmp = dbStudent.fnGetAllStudent();
        students.addAll(tmp);
        studentAdapter.notifyDataSetChanged();
    }
}
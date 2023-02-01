package bitp3453.b032110463.b032110463labapp.Classes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bitp3453.b032110463.b032110463labapp.Model.Student;
import bitp3453.b032110463.b032110463labapp.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvName,tvStudNo,tvGender,tvBirth,tvState;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvName = itemView.findViewById(R.id.tvName);
        this.tvStudNo =  itemView.findViewById(R.id.tvStudNo);
        this.tvGender =  itemView.findViewById(R.id.tvGender);
        this.tvBirth =  itemView.findViewById(R.id.tvBirth);
        this.tvState =  itemView.findViewById(R.id.tvState);
    }

    public void setStudent(Student student){
        tvName.setText(student.getStrFullname());
        tvStudNo.setText(student.getStrStudNo());
        tvGender.setText(student.getStrGender());
        tvBirth.setText(student.getStrBirthdate());
        tvState.setText(student.getStrState());
    }


}
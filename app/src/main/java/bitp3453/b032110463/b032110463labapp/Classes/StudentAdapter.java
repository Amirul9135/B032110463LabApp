package bitp3453.b032110463.b032110463labapp.Classes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import bitp3453.b032110463.b032110463labapp.Model.Student;
import bitp3453.b032110463.b032110463labapp.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Vector<Student> students;


    public StudentAdapter(LayoutInflater layoutInflater, Vector<Student> students) {
        this.layoutInflater = layoutInflater;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(layoutInflater.inflate(R.layout.student_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.setStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void removeItem(int position) {
        students.remove(position);
        notifyItemRemoved(position);
    }
}

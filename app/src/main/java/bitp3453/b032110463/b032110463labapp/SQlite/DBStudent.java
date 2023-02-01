package bitp3453.b032110463.b032110463labapp.SQlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import bitp3453.b032110463.b032110463labapp.Model.Student;

public class DBStudent extends SQLiteOpenHelper {

    public static final String dbName = "dbstudent";
    public static final String tbStudentName = "students";
    public static final String colStudNo="stud_no";
    public static final String colStudName="stud_name";
    public static final String colStudGender="stud_gender";
    public static final String colStudState="stud_state";
    public static final String colStudDOB="stud_dob";

    public static final String strCreateTbStudent = "CREATE TABLE " + tbStudentName + " (" + colStudNo + " TEXT PRIMARY KEY, " + colStudName + " TEXT, "
            + colStudGender + " TEXT, " + colStudState + " TEXT, " + colStudDOB + " DATE) ";
    public static final String strDropTbStudent = "DROP TABLE IF EXIST " + tbStudentName;

    public DBStudent(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }
    public DBStudent(Context context){
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlldb) {
        sqlldb.execSQL(strCreateTbStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public float fnInsertStudent(Student newStd){
        Log.d("test","in student insert");
        float result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colStudNo,newStd.getStrStudNo());
        values.put(colStudName,newStd.getStrFullname());
        values.put(colStudDOB,newStd.getStrBirthdate());
        values.put(colStudGender,newStd.getStrGender());
        values.put(colStudState,newStd.getStrState());

        result = db.insert(tbStudentName,null,values);
        Log.d("rest",Float.toString(result) );
        return  result;
    }
    @SuppressLint("Range")
    public List<Student> fnGetAllStudent(){
        List<Student> allStd  = new ArrayList<Student>();
        String strSel = "Select * From " + tbStudentName;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSel, null);
        if(cursor.moveToFirst()){
            do{
                Student tmpstd = new Student();
                tmpstd.setStrStudNo(cursor.getString(cursor.getColumnIndex(colStudNo)));
                tmpstd.setStrFullname(cursor.getString(cursor.getColumnIndex(colStudName)));
                tmpstd.setStrBirthdate(cursor.getString(cursor.getColumnIndex(colStudDOB)));
                tmpstd.setStrState(cursor.getString(cursor.getColumnIndex(colStudState)));
                tmpstd.setStrGender(cursor.getString(cursor.getColumnIndex(colStudGender)));
                allStd.add(tmpstd);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return  allStd;
    }

    public int fnDelStudent(String studentId){
        int result = this.getWritableDatabase().delete(tbStudentName,colStudNo + " = ?",new String[] {studentId});
        return  result;
    }

    @SuppressLint("Range")
    public Student getStudent(String studentId) throws Exception {
        Student student = new Student();
        String strsql = "SELECT * FROM " + tbStudentName + " WHERE " + colStudNo + " = '" + studentId + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(strsql,null);
        if(cursor != null && cursor.moveToFirst()){
            student.setStrStudNo(cursor.getString( cursor.getColumnIndex(colStudNo)));
            student.setStrFullname(cursor.getString( cursor.getColumnIndex(colStudName)));
            student.setStrState(cursor.getString( cursor.getColumnIndex(colStudState)));
            student.setStrGender(cursor.getString( cursor.getColumnIndex(colStudGender)));
            cursor.close();
        }
        else{
            throw new Exception();
        }
        return  student;
    }


}

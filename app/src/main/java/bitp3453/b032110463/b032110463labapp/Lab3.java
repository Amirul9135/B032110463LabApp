package bitp3453.b032110463.b032110463labapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import bitp3453.b032110463.b032110463labapp.databinding.ActivityLab3Binding;

public class Lab3 extends AppCompatActivity {

    ActivityLab3Binding binding;
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"This happen when app pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"This happen when app stop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"This happen when app destroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"This happen when app restart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"This happen when app resume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"This happen when app start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLab3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(this,"This happen when app created",Toast.LENGTH_SHORT).show();
    }
}
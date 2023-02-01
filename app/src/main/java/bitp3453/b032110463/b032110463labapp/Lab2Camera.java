package bitp3453.b032110463.b032110463labapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import bitp3453.b032110463.b032110463labapp.databinding.ActivityLab2CameraBinding;

public class Lab2Camera extends AppCompatActivity {
    ActivityLab2CameraBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLab2CameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String strMsg = getIntent().getStringExtra("varStr1");
        binding.tvDetail.setText("Welcome to second activity " + strMsg);
        binding.imgProfile.setOnClickListener(this::fnTakePic);
        binding.btnBackToFirst.setOnClickListener(this::backToLab2);
    }

    public void fnTakePic(View view){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //views.tvGreet.setText(views.tvGreet.getText().toString() + " This is your image");
                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                binding.imgProfile.setImageBitmap(bp);
                Toast tcancel = Toast.makeText(getApplicationContext(),"Image loaded",Toast.LENGTH_SHORT);
                tcancel.show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast tcancel = Toast.makeText(getApplicationContext(),"Camera canceled",Toast.LENGTH_SHORT);
                tcancel.show();
            }
        }
    }

    private void backToLab2(View view){
        Intent intent = new Intent(this,MainActivity.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        binding.imgProfile.buildDrawingCache();
        binding.imgProfile.getDrawingCache().compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] barray = stream.toByteArray();
        intent.putExtra("Lab2",barray);
        startActivity(intent);
    }
}
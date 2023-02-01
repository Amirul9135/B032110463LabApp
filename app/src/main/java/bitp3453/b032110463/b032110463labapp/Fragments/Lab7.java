package bitp3453.b032110463.b032110463labapp.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import bitp3453.b032110463.b032110463labapp.Classes.LoadingOverlay;
import bitp3453.b032110463.b032110463labapp.R;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab4Binding;
import bitp3453.b032110463.b032110463labapp.databinding.FragmentLab7Binding;

public class Lab7 extends Fragment {
    FragmentLab7Binding binding;
    Executor executor;
    Handler handler;
    Bitmap bitmap = null;
    public Lab7() {
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
        binding = FragmentLab7Binding.inflate(inflater,container,false);
        executor =  Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        binding.btnAsyncTask.setOnClickListener(this::FetchImg);
        return binding.getRoot();
    }


    private void FetchImg(View view){

        LoadingOverlay loader = new LoadingOverlay(getActivity());

        loader.show();
        ConnectivityManager manager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL imageUrl = new URL("https://ftmk.utem.edu.my/web/wp-content/uploads/2020/02/cropped-Logo-FTMK.png");
                        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                        conn.setDoInput(true);
                        conn.connect();
                        InputStream ins = conn.getInputStream();
                        BitmapFactory.Options opt = new BitmapFactory.Options();
                        opt.inPreferredConfig = Bitmap.Config.RGB_565;
                        bitmap = BitmapFactory.decodeStream(ins,null,opt);
                    }catch (MalformedURLException e) {
                        loader.dismiss();
                        e.printStackTrace();
                    } catch (IOException e) {
                        loader.dismiss();
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.imgVwSelfie.setImageBitmap(bitmap);
                            loader.dismiss();
                        }
                    });

                }
            });
        }
        else{
            Toast.makeText(getActivity(),"NO network", Toast.LENGTH_SHORT).show();
            loader.dismiss();
        }
    }


}
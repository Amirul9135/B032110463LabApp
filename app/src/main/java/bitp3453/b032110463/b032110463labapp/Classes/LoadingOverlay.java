package bitp3453.b032110463.b032110463labapp.Classes;

import android.app.Activity;
import android.app.AlertDialog;

import bitp3453.b032110463.b032110463labapp.R;


public class LoadingOverlay {
    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingOverlay(Activity activity){
        this.activity = activity;
    }
    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(activity.getLayoutInflater().inflate(R.layout.loading,null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }
    public void dismiss(){
        alertDialog.dismiss();
    }
}

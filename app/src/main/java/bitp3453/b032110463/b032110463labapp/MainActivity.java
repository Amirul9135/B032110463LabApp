package bitp3453.b032110463.b032110463labapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;

import bitp3453.b032110463.b032110463labapp.Fragments.Home;
import bitp3453.b032110463.b032110463labapp.Fragments.Lab2;
import bitp3453.b032110463.b032110463labapp.Fragments.Lab4;
import bitp3453.b032110463.b032110463labapp.Fragments.Lab7;
import bitp3453.b032110463.b032110463labapp.Fragments.SearchStudent;
import bitp3453.b032110463.b032110463labapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FragmentManager frm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().hasExtra("Lab2")){
            Log.d("open","l2");
            openFragment(new Lab2(getIntent().getByteArrayExtra("Lab2")));
        }
        //drawer setup--------------------------------------
        frm = getSupportFragmentManager();
        setSupportActionBar(binding.mytb.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = binding.myDrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        binding.mytb.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        navigationView = binding.navMenu;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        openFragment(new Home());
                        break;
                    case R.id.nav_l3:
                        Intent intent = new Intent(getApplication(),Lab3.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_l2:
                        openFragment(new Lab2());
                        break;
                    case R.id.nav_l4:
                        openFragment(new Lab4());
                        break;
                    case R.id.nav_l7:
                        openFragment(new Lab7());
                        break;
                    case R.id.nav_search:
                        openFragment(new SearchStudent());
                        break;
                }
                Log.d("click",""+item.getTitle());
                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });

        //drawer setup END--------------------------------------
    }

    private void openFragment(Fragment fragment){
        frm.beginTransaction().replace(binding.container.getId(),fragment).commit();
    }
}
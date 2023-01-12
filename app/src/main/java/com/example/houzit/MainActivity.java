package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.houzit.databinding.ActivityMainBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ShortlistFragment shortlistFragment =new ShortlistFragment();
    PlansFragment plansFragment = new PlansFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout =findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        return true;
                    case R.id.bottom_plans:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, plansFragment).commit();
                        return true;
                    case R.id.bottom_shortlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, shortlistFragment).commit();
                        return true;
                    case R.id.bottom_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, profileFragment).commit();
                        return true;

                }
                return false;
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){

                    case R.id.nav_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
//                    case R.id.nav_settings:
//                        Toast.makeText(MainActivity.this, "Settings is clicked", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.nav_shortlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, shortlistFragment).commit();
                        break;
                    case R.id.nav_plans:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, plansFragment).commit();
                        break;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, profileFragment).commit();
                        break;
                    case R.id.nav_login:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), SigninPage.class));
                        finish();
                        Toast.makeText(MainActivity.this, "logged out successfully", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, AboutUs.class));
                        break;
                    case R.id.nav_share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this this cool application");
                        intent.putExtra(Intent.EXTRA_TEXT, "Your application link is here");
                        startActivity(Intent.createChooser(intent, "Share Via"));
                        Toast.makeText(MainActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
}
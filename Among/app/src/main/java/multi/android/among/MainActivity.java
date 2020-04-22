package multi.android.among;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import multi.android.among.page.CommunityFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CommunityFragment communityFragment = new CommunityFragment();
    TabLayout tabLayout;
    // Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navi);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_container,communityFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item1) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.content_container, communityFragment).commit();
                }

                return false;
            }
        });
    }



    }


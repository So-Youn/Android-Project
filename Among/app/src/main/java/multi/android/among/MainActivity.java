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

import java.lang.reflect.Array;
import java.util.ArrayList;

import multi.android.among.page.CommunityFragment;
import multi.android.among.page.HealthFragment;
import multi.android.among.page.NurseFragment;
import multi.android.among.page.PolicyFragment;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    CommunityFragment communityFragment = new CommunityFragment();
    NurseFragment nurseFragment = new NurseFragment();
    HealthFragment healthFragment = new HealthFragment();
    PolicyFragment policyFragment = new PolicyFragment();
    TabLayout tabLayout;
    // Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navi);
        tabLayout = findViewById(R.id.function_tab);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item1) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, nurseFragment).commit();
                }else if(menuItem.getItemId()==R.id.item2){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, nurseFragment).commit();
                }else if(menuItem.getItemId()==R.id.item3){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, communityFragment).commit();
                }

                return false;
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment = null;
                if(position==0){
                    fragment = policyFragment;
                }else if(position ==1){
                    fragment = healthFragment;
                }else {
                    fragment = nurseFragment;
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.function_container,fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}






package multi.android.support_lib.fragment.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import multi.android.support_lib.R;

public class MainTest extends AppCompatActivity {
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);
    }
    public void btn_click(View v){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

       switch (v.getTag().toString()){
           case "0" :
               transaction.replace(R.id.container,firstFragment);
               break;
           case "1":
               transaction.replace(R.id.container,secondFragment);
               break;
           case "2" :
               transaction.replace(R.id.container,thirdFragment);
               break;
       }
        transaction.commit();
    }
}


package multi.android.support_lib.fragment.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import multi.android.support_lib.R;

public class FragmentMain extends AppCompatActivity  {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear02);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        firstFragment = new FirstFragment();

    }
    //event에 붙이려면 view 객체를 반드시 받아주어야 한다.
    public void btn_click(View view){
        setFragment(view.getTag().toString());
    }
    public void setFragment(String idx){
        Log.d("fragment",idx);
        FragmentManager fragmentManager = getSupportFragmentManager();
        //프래그먼트의 변화를 관리하는 객체
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (idx){
            case "ViewFragment1":
                //교체할 영역이어디인지 명시 - layout id : container
                transaction.replace(R.id.container,firstFragment);
                break;
            case "ViewFragment2":
                transaction.replace(R.id.container,secondFragment);
                break;
            case "ViewFragment3":
                transaction.replace(R.id.container,thirdFragment);
        }
        transaction.commit();
    }
}


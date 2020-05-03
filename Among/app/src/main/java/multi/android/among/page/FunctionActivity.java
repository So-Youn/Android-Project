package multi.android.among.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import multi.android.among.R;
import multi.android.among.page.calendar.CalendarFragment;


public class FunctionActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    CommunityFragment communityFragment = new CommunityFragment();
    ChattingFragment chattingFragment = new ChattingFragment();
    FriendFragment friendFragment = new FriendFragment();
    PolicyFragment policyFragment = new PolicyFragment();
    CalendarFragment calendarFragment = new CalendarFragment();

    //RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_main);
        //getToken();
        bottomNavigationView = findViewById(R.id.bottom_navi);
       // tabLayout = findViewById(R.id.function_tab);
        //  recyclerView = findViewById(R.id.list);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item1) {

                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, policyFragment).commitAllowingStateLoss();
                    return true;
                }else if(menuItem.getItemId()==R.id.item2){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, friendFragment).commitAllowingStateLoss();
                    return true;
                }else if(menuItem.getItemId()==R.id.item3){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, chattingFragment).commitAllowingStateLoss();
                    return true;
                }else if(menuItem.getItemId()==R.id.item4){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.function_container, calendarFragment).commitAllowingStateLoss();
                    return true;
                }

                return false;
            }
        });



    }
    public void btn_register(View view){
        Intent intent = new Intent(FunctionActivity.this,boardWrite.class);
        startActivity(intent);
    }
   /* private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        //토큰을 가져오다 실패하면 실행하게 된다.
                        if (!task.isSuccessful()) {
                            Log.d("myfcm", "getInstanceId failed", task.getException());
                            return;
                        }
                        //new Instance ID Token
                        String token = task.getResult().getToken();
                        String id = task.getResult().getId();
                        Log.d("myfcm", token);
                        new SendTokenThread(token);
                        Thread sendThread = new Thread(new SendTokenThread(token));
                        sendThread.start();

                    }
                });
    }
    class SendTokenThread extends Thread{
        String token;
        public SendTokenThread(String token) {
            this.token = token;
        }

        @Override
        public void run() {
            super.run();
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder = builder.url("http://70.12.224.47:8088/among/fcm/fcm_check?token="+token);
                Request request = builder.build();
                Call newcall = client.newCall(request);
                newcall.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}






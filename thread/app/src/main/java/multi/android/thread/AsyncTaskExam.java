package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskExam extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Button button1;
    Button button2;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_exam);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button);
        button2 =findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);

    }
    public void ck_bt(View view){
        AsyncExam asyncExam = new AsyncExam();
        asyncExam.execute(1,2);
    }
    class AsyncExam extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected void onPreExecute() {
            Log.d("myasync", "onPreExecute 호출");
            if(button1.isSelected()){
                button1.setEnabled(false);
                button2.setEnabled(true);
                progressBar.setMax(50);
            }

        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int odd = integers[0];
            int even = integers[1];
            int sum = 0;
            for(int i = 1;i<50;i++){
                SystemClock.sleep(100);
                sum = sum + i;
                publishProgress(i);

            }
            return sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(Integer.toString(values[0]));

            if(values[0]%2==0){
                imageView.setImageResource(R.drawable.d1);
            }else{
                imageView.setImageResource(R.drawable.d2);
            }
        }

        @Override
        protected void onPostExecute(Integer sum) {
            super.onPostExecute(sum);
            textView.setText(Integer.toString(sum));
        }
    }
}

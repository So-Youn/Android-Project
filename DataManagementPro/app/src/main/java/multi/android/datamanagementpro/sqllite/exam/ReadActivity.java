package multi.android.datamanagementpro.sqllite.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import multi.android.datamanagementpro.R;


public class ReadActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Intent intent = getIntent();

		TextView t = (TextView)findViewById(R.id.show);
		Product data = intent.getParcelableExtra("data");

		t.setText(data.get_id()+","+data.getName()+","+data.getPrice());
	
	}

}

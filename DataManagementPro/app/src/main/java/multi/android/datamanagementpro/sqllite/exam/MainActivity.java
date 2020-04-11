package multi.android.datamanagementpro.sqllite.exam;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import multi.android.datamanagementpro.R;


public class MainActivity extends
		AppCompatActivity implements AdapterView.OnItemClickListener,OnClickListener {
	DBHandler handler;
	EditText edtName;
	EditText edtSu;
	EditText edtPrice;
	ListView listview;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.btnIns).setOnClickListener(this);
		findViewById(R.id.btnResult).setOnClickListener(this);
		findViewById(R.id.btnResult2).setOnClickListener(this);
		findViewById(R.id.btnSearch).setOnClickListener(this);
		listview = findViewById(R.id.list);
		listview.setOnItemClickListener(this);

		edtName = (EditText)findViewById(R.id.edtName);
		edtSu = (EditText)findViewById(R.id.edtSu);
		edtPrice = (EditText)findViewById(R.id.edtPrice);
		handler = new DBHandler(this);


	}


	@Override
	public void onClick(View v) {
        if(v.getId()==R.id.btnIns){
            handler.insert(edtName.getText().toString(),edtSu.getText().toString(),
					edtPrice.getText().toString());
        }else if(v.getId()==R.id.btnResult){
			ArrayList data = handler.resultAll(edtName.getText().toString(),edtSu.getText().toString(),
					edtPrice.getText().toString());
			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, data);
			listview.setAdapter(adapter);
		}else if(v.getId()==R.id.btnResult2){
			ArrayList cursor =new DBHandler.resultTotal(edtName.getText().toString(),edtSu.getText().toString(),
					edtPrice.getText().toString());

			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, cursor);

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}


}




















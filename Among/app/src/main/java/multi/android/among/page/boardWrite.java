package multi.android.among.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import multi.android.among.R;

public class boardWrite extends AppCompatActivity {
    Spinner sortSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        sortSpinner =findViewById(R.id.spinner_sort);
        ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort, android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

    }
}

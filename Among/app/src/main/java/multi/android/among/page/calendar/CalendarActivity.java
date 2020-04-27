package multi.android.among.page.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import multi.android.among.R;

public class CalendarActivity extends AppCompatActivity {
    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calendar_view);

    }
}

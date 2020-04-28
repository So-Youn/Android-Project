package multi.android.among.page.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import multi.android.among.R;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewGroup = (View)LayoutInflater.from(getContext()).inflate(R.layout.fragment_calendar, container, false);


        return viewGroup;
    }
}

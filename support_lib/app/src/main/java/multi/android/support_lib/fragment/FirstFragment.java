package multi.android.support_lib.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import multi.android.support_lib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }
    // onAttach : 프래그먼트가 액티비티에 Attach될 때 호출
    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("lifecycle","fragment=========onAttach");
        super.onAttach(context);
    }
    // onCreate : 초기화 작업 - View 초기화 작업 x , 값을 초기화해서 넘겨주거나 해야할 때 초기화
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //super 먼저 call
        Log.d("lifecycle","fragment=========onCreate");
    }
    //fragment뷰가 만들어질 때 호출되는 메소드
    //액티비티에 배치될 때 호출되는 메소드 - view를 그리기 위해서 호출하는 메소드
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("lifecycle","fragment=========onCreateView");
        return inflater.inflate(R.layout.fragment_first2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("lifecycle","fragment=========onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("lifecycle","fragment=========onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }
    //onStart - 사용자가 fragment를 볼 수 있는 시점
    @Override
    public void onStart() {
        Log.d("lifecycle","fragment=========onStart");
        super.onStart();
    }
    //onResume - 사용자와 상호작용이 가능한 상태
    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle","fragment=========onResume");
    }
    //프래그먼트를 다른 액티비티가 가리기 시작할 때 호출
    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle","fragment=========onPause");

    }
    //프래그먼트 완전히 덮어서 보이지 않을 때
    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle","fragment=========onStop");
    }
    //프래그먼트에서 뷰를 지우는 작업
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("lifecycle","fragment=========onDestroyView");

    }
    //모두 지워지고 호출
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "fragment=========onDestroy");
    }
    //액티비티에서 프래그먼트가 교체될 때
    @Override
    public void onDetach() {
        Log.d("lifecycle","fragment=========onDetach");
        super.onDetach();
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();

    }



}

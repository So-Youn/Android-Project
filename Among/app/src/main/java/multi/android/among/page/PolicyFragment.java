package multi.android.among.page;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import multi.android.among.R;
import multi.android.among.page.design.PolicyViewAdapter;
import multi.android.among.page.design.PolicyViewItem;

public class PolicyFragment extends Fragment  {
    RecyclerView card_list;

    public PolicyFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_policy, container, false);
        card_list = (RecyclerView)viewGroup.findViewById(R.id.card_list);
        List<PolicyViewItem> recycle_card_data = new ArrayList<PolicyViewItem>();
        PolicyViewItem[] item = new PolicyViewItem[6];
        item[0] = new PolicyViewItem(R.drawable.together,"정책 1","간단요약1");
        item[1] = new PolicyViewItem(R.drawable.together,"정책 2","간단요약2");
        item[2] = new PolicyViewItem(R.drawable.together,"정책 3","간단요약3");
        item[3] = new PolicyViewItem(R.drawable.together,"정책 4","간단요약4");
        item[4] = new PolicyViewItem(R.drawable.together,"정책 5","간단요약5");
        item[5] = new PolicyViewItem(R.drawable.together,"정책 6","간단요약6");

        for(int i=0;i<6;i++){
            recycle_card_data.add(item[i]);
        }
        PolicyViewAdapter adapter = new PolicyViewAdapter(this,
                R.layout.fragment_policy,recycle_card_data);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        card_list.setHasFixedSize(true);
        card_list.setLayoutManager(manager); //vertical Linearlayout에 셋팅
        //4. Recycler와 Adapter를 연결
        card_list.setAdapter(adapter);
        return viewGroup;
    }


}

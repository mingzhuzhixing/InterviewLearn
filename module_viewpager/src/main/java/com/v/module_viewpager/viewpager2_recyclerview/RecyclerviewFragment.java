package com.v.module_viewpager.viewpager2_recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_recyclerview.base.BaseRvAdapter;
import com.v.module_recyclerview.base.BaseRvHolder;
import com.v.module_recyclerview.decoration.VerticalDividerItemDecoration;
import com.v.module_viewpager.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewFragment extends Fragment {
    private List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new VerticalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.transparent)
                .sizeResId(R.dimen.y40)
                .build();
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));


        for (int i = 0; i < 10; i++) {
            list.add("item_" + i);
        }
        adapter.setData(list);

        return view;
    }

    public class MyRecyclerAdapter extends BaseRvAdapter<String> {

        public MyRecyclerAdapter(Activity mCtx) {
            super(mCtx);
        }

        @Override
        public void convert(BaseRvHolder holder, String s, int layoutType, int position) {
            holder.setText(R.id.tv_content, s);
        }

        @Override
        public int getLayoutAsViewType(String s, int position) {
            return R.layout.item_recyclerview;
        }
    }
}

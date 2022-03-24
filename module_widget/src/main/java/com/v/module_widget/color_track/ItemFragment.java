package com.v.module_widget.color_track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.v.module_widget.R;

public class ItemFragment extends Fragment {
    private static final String KEY_TITLE = "title";

    public static ItemFragment newInstance(String tabTitle) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, tabTitle);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);

        String title = getArguments() != null ? getArguments().getString(KEY_TITLE) : "";
        tvTitle.setText(title);
        return view;


    }
}

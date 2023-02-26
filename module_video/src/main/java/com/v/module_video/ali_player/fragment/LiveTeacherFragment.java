package com.v.module_video.ali_player.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.v.module_video.R;

/**
 * ClassName: LiveMessageFragment
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/14 6:21 下午
 */
public class LiveTeacherFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live_teacher, container, false);
    }
}

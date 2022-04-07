package com.v.module_dialog.system_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.v.module_dialog.R;
import com.v.module_recyclerview.decoration.GridItemCenterDecoration;
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration;
import com.v.module_recyclerview.decoration.VerticalDividerItemDecoration;
import com.v.module_utils.DensityUtils;
import com.v.module_utils.ScreenUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralSystemDialog extends Dialog {
    private Map<Integer, ShareFeatureModel.ShareFeatureBean> mFeatureBeanMap;
    private final Context mContext;
    private final List<ShareChannelBean> mList;

    /**
     * 表格 线性
     */
    private int mStyle = 0;

    /**
     * 列数
     */
    private int mSpanCount = 1;

    public GeneralSystemDialog(@NonNull Context context, List<ShareChannelBean> list) {
        super(context, R.style.bottomDialog);
        this.mContext = context;
        this.mList = list;
    }

    public GeneralSystemDialog(@NonNull Context context, List<ShareChannelBean> list, int style, int spanCount) {
        super(context, R.style.bottomDialog);
        this.mContext = context;
        this.mList = list;
        this.mStyle = style;
        this.mSpanCount = spanCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_general_system);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        initRecyclerView(mContext, recyclerView, mList);

        TextView btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        initWindow();
    }

    /**
     * 初始化 RecyclerView
     */
    private void initRecyclerView(Context context, RecyclerView recyclerView, List<ShareChannelBean> list) {
        if (mFeatureBeanMap == null || mFeatureBeanMap.size() == 0) {
            mFeatureBeanMap = createBaseFeatureMap(context);
        }

        if (mStyle == 1) {
            GridLayoutManager layoutManager = new GridLayoutManager(context, mSpanCount);
            // layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);  mSpanCount是行数 否则是列数
            recyclerView.setLayoutManager(layoutManager);
            //设置分割线
            RecyclerView.ItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.transparent)
                    .sizeResId(R.dimen.y40)
                    .build();
            recyclerView.addItemDecoration(itemDecoration);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            //设置分割线
            RecyclerView.ItemDecoration itemDecoration = new VerticalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.transparent)
                    .sizeResId(R.dimen.y80)
                    .showFirstDivider(R.dimen.y40)
                    .showLastDivider(R.dimen.y40)
                    .build();
            recyclerView.addItemDecoration(itemDecoration);
        }
        GeneralSystemAdapter adapter = new GeneralSystemAdapter(context, list, mFeatureBeanMap, mStyle);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initWindow() {
        if (getWindow() == null) {
            return;
        }
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.BottomDialogAnimationStyle);
    }

    /**
     * @param context 上下文
     * @return 基础的功能样式
     */
    public static Map<Integer, ShareFeatureModel.ShareFeatureBean> createBaseFeatureMap(Context context) {
        return createDefault(context, "ys_feature" + File.separator + "Features.json");
    }

    /**
     * @param context  上下文
     * @param fileName 资源文件名
     * @return 默认支持的所有样式
     */
    private static Map<Integer, ShareFeatureModel.ShareFeatureBean> createDefault(Context context, String fileName) {
        HashMap<Integer, ShareFeatureModel.ShareFeatureBean> map = new HashMap<>(16);
        Gson gson = new Gson();
        ShareFeatureModel featuresModel = gson.fromJson(getJson(context, fileName), ShareFeatureModel.class);
        for (ShareFeatureModel.ShareFeatureBean bean : featuresModel.data) {
            map.put(bean.type, bean);
        }
        return map;
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assets = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assets.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            bf.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }
        return "";
    }
}

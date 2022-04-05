package com.v.module_dialog.system_dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_dialog.R;
import com.v.module_utils.DensityUtils;
import com.v.module_utils.ResourcesUtil;

import java.util.List;
import java.util.Map;

public class GeneralSystemAdapter extends RecyclerView.Adapter<GeneralSystemAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;

    private List<ShareChannelBean> mList;

    private Map<Integer, ShareFeatureModel.ShareFeatureBean> mElements;
    private Context mContext;

    public GeneralSystemAdapter(Context context, List<ShareChannelBean> list, Map<Integer, ShareFeatureModel.ShareFeatureBean> elements) {
        layoutInflater = LayoutInflater.from(context);
        this.mList = list;
        this.mContext = context;
        this.mElements = elements;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_general_system, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ShareChannelBean bean = mList.get(position);
        if (bean == null || bean.shareType <= 0) {
            return;
        }
        viewHolder.llIcon.setOnClickListener(bean.onClickListener);
        if (mElements.containsKey(bean.shareType)) {
            ShareFeatureModel.ShareFeatureBean element = mElements.get(bean.shareType);
            if (element == null) {
                return;
            }
            viewHolder.tvIconText.setText(element.typeName);
            if (!TextUtils.isEmpty(element.textColor)) {
                viewHolder.tvIconText.setTextColor(Color.parseColor(element.textColor));
            }
            if (!TextUtils.isEmpty(element.textSize)) {
                viewHolder.tvIconText.setTextSize(TypedValue.COMPLEX_UNIT_PX, DensityUtils.sp2px(mContext, Float.parseFloat(element.textSize)));
            }
            Drawable icon = ContextCompat.getDrawable(mContext, ResourcesUtil.getDrawable(mContext, element.icon));
            viewHolder.ivIcon.setImageDrawable(icon);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llIcon;
        ImageView ivIcon;
        TextView tvIconText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llIcon = itemView.findViewById(R.id.ll_icon);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvIconText = itemView.findViewById(R.id.tv_icon_text);
        }
    }
}

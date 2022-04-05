package com.v.module_dialog.system_dialog;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShareFeatureModel {
    public StyleBean style;
    public List<ShareFeatureBean> data;

    public static class StyleBean {
        @SerializedName("text_size")
        public String textSize;
        @SerializedName("text_color")
        public String textColor;
    }

    public static class ShareFeatureBean {
        public int type;
        @SerializedName("type_name")
        public String typeName;
        public String name;
        public String icon;
        @SerializedName("sub_icon")
        public String subIcon;

        public String textColor;
        public String textSize;
    }

}

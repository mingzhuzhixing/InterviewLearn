package com.v.module_recyclerview.swipe_refresh_layout.refreshview;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class OnRefreshTextHelper {
    private ArrayList<String> textList = new ArrayList<>();
    private final String text1 = "有书，终身教育服务平台";
    private final String text2 = "读一本好书，就是和许多高尚的人谈话";
    private final String text3 = "读完的书成就期待的自己";
    private final String text4 = "有书，终身教育服务平台";
    private final String text5 = "喜欢的内容，您可以“加入学习”哦";
    private final String text6 = "腹中“有书”气自华";
    private final String text7 = "没有星星的夜晚，有书给你最温暖的陪伴";
    private final String text8 = "开通有书VIP，海量听书免费听";
    private final String text9 = "用有书，和三千万书友一起终身学习";
    private final String text10 = "读书使人明智，有书的人生不再寂寞";

    public OnRefreshTextHelper() {
        init();
    }

    private void init() {
        textList.clear();
        textList.add(text1);
        textList.add(text2);
        textList.add(text3);
        textList.add(text4);
        textList.add(text5);
        textList.add(text6);
        textList.add(text7);
        textList.add(text8);
        textList.add(text9);
        textList.add(text10);
    }

    public String getText() {
        int index = new Random().nextInt(textList.size());
        String text = textList.get(index);
        textList.remove(index);
        if (textList.isEmpty()) {
            init();
        }
        return text;
    }
}

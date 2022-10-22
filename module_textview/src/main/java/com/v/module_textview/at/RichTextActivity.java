package com.v.module_textview.at;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.textutillib.EmojiLayout;
import com.shuyu.textutillib.RichEditBuilder;
import com.shuyu.textutillib.RichEditText;
import com.shuyu.textutillib.RichTextBuilder;
import com.shuyu.textutillib.RichTextView;
import com.shuyu.textutillib.SmileUtils;
import com.shuyu.textutillib.listener.OnEditTextUtilJumpListener;
import com.shuyu.textutillib.listener.SpanAtUserCallBack;
import com.shuyu.textutillib.listener.SpanCreateListener;
import com.shuyu.textutillib.listener.SpanTopicCallBack;
import com.shuyu.textutillib.listener.SpanUrlCallBack;
import com.shuyu.textutillib.model.TopicModel;
import com.shuyu.textutillib.model.UserModel;
import com.shuyu.textutillib.span.ClickAtUserSpan;
import com.shuyu.textutillib.span.ClickTopicSpan;
import com.shuyu.textutillib.span.LinkSpan;
import com.v.module_textview.R;
import com.v.module_textview.R2;
import com.v.module_utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RichTextActivity extends AppCompatActivity {

    public final static int REQUEST_USER_CODE_INPUT = 1111;
    public final static int REQUEST_USER_CODE_CLICK = 2222;
    public final static int REQUEST_TOPIC_CODE_INPUT = 3333;
    public final static int REQUEST_TOPIC_CODE_CLICK = 4444;

    @BindView(R2.id.emoji_edit_text)
    RichEditText richEditText;

    @BindView(R2.id.emoji_show_bottom)
    ImageView emojiShowBottom;

    @BindView(R2.id.emojiLayout)
    EmojiLayout emojiLayout;

    @BindView(R2.id.activity_main)
    RelativeLayout activityMain;

    @BindView(R2.id.emoji_show_at)
    ImageView emojiShowAt;

    @BindView(R2.id.rich_text)
    TextView richText;

    @BindView(R2.id.rich_text_2)
    RichTextView richTextView;

    List<TopicModel> topicModels = new ArrayList<>();

    List<UserModel> nameList = new ArrayList<>();

    List<TopicModel> topicModelsEd = new ArrayList<>();

    List<UserModel> nameListEd = new ArrayList<>();

    String insertContent = "这是测试文本#话题话题#哟 www.baidu.com " +
            " 来@某个人  @22222 @kkk " +
            " 好的,来几个表情[e2][e4][e55]，最后来一个电话 13245685478";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEmoji();
        setContentView(R.layout.activity_rich_text);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 处理自己的表情
     */
    private void initEmoji() {
        List<Integer> data = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (int i = 1; i < 64; i++) {
            int resId = getResources().getIdentifier("e" + i, "drawable", getPackageName());
            data.add(resId);
            strings.add("[e" + i + "]");
        }
        /**初始化为自己的**/
        SmileUtils.addPatternAll(SmileUtils.getEmoticons(), strings, data);
    }

    private void initView() {
        emojiLayout.setEditTextSmile(richEditText);
        RichEditBuilder richEditBuilder = new RichEditBuilder();
        richEditBuilder.setEditText(richEditText)
                .setTopicModels(topicModelsEd)
                .setUserModels(nameListEd)
                .setColorAtUser("#FF00C0")
                .setColorTopic("#F0F0C0")
                .setEditTextAtUtilJumpListener(new OnEditTextUtilJumpListener() {
                    @Override
                    public void notifyAt() {
                        goToUserList(RichTextActivity.this, RichTextActivity.REQUEST_USER_CODE_INPUT);
                    }

                    @Override
                    public void notifyTopic() {
                        goToTopicList(RichTextActivity.this, RichTextActivity.REQUEST_TOPIC_CODE_INPUT);
                    }
                })
                .builder();

        resolveRichShow();
    }

    private void initData() {
        nameList.clear();
        topicModels.clear();

        UserModel userModel = new UserModel();
        userModel.setUser_name("22222");
        userModel.setUser_id("2222");
        nameList.add(userModel);
        userModel = new UserModel();
        userModel.setUser_name("kkk");
        userModel.setUser_id("23333");
        nameList.add(userModel);

        TopicModel topicModel = new TopicModel();
        topicModel.setTopicId("333");
        topicModel.setTopicName("话题话题");
        topicModels.add(topicModel);
    }

    private void resolveRichShow() {
        initData();
        String content = "这是测试#话题话题#文本哟 www.baidu.com " +
                "\n来@某个人  @22222 @kkk " +
                "\n好的,来几个表情[e2][e4][e55]，最后来一个电话 13245685478";

        SpanUrlCallBack spanUrlCallBack = new SpanUrlCallBack() {
            @Override
            public void phone(View view, String phone) {
                Toast.makeText(view.getContext(), phone + " 被点击了", Toast.LENGTH_SHORT).show();
                if (view instanceof TextView) {
                    ((TextView) view).setHighlightColor(Color.TRANSPARENT);
                }
            }

            @Override
            public void url(View view, String url) {
                Toast.makeText(view.getContext(), url + " 被点击了", Toast.LENGTH_SHORT).show();
                if (view instanceof TextView) {
                    ((TextView) view).setHighlightColor(Color.TRANSPARENT);
                }
            }
        };

        SpanAtUserCallBack spanAtUserCallBack = new SpanAtUserCallBack() {
            @Override
            public void onClick(View view, UserModel userModel1) {
                Toast.makeText(view.getContext(), userModel1.getUser_name() + " 被点击了", Toast.LENGTH_SHORT).show();
                if (view instanceof TextView) {
                    ((TextView) view).setHighlightColor(Color.TRANSPARENT);
                }
            }
        };

        SpanTopicCallBack spanTopicCallBack = new SpanTopicCallBack() {
            @Override
            public void onClick(View view, TopicModel topicModel) {
                Toast.makeText(view.getContext(), topicModel.getTopicName() + " 被点击了", Toast.LENGTH_SHORT).show();
                if (view instanceof TextView) {
                    ((TextView) view).setHighlightColor(Color.TRANSPARENT);
                }
            }
        };
        RichTextBuilder richTextBuilder = new RichTextBuilder(this);
        richTextBuilder.setContent(content)
                .setAtColor(Color.RED)
                .setLinkColor(Color.BLUE)
                .setTopicColor(Color.YELLOW)
                .setListUser(nameList)
                .setListTopic(topicModels)
                .setTextView(richText)
                .setNeedUrl(true)
                .setNeedNum(true)
                .setEmojiSize(DensityUtils.dip2px(this, 5))
                //.setVerticalAlignment(CenteredImageSpan.ALIGN_CENTER)
                .setSpanAtUserCallBack(spanAtUserCallBack)
                .setSpanUrlCallBack(spanUrlCallBack)
                .setSpanTopicCallBack(spanTopicCallBack)
                //自定义span，如果不需要可不设置
                .setSpanCreateListener(spanCreateListener)
                .build();

        //直接使用RichTextView
        richTextView.setAtColor(Color.RED);
        richTextView.setTopicColor(Color.BLUE);
        richTextView.setLinkColor(Color.YELLOW);
        richTextView.setNeedNumberShow(true);
        richTextView.setNeedUrlShow(true);
        richTextView.setSpanAtUserCallBackListener(spanAtUserCallBack);
        richTextView.setSpanTopicCallBackListener(spanTopicCallBack);
        richTextView.setSpanUrlCallBackListener(spanUrlCallBack);
        //所有配置完成后才设置text
        richTextView.setRichText(content, nameList, topicModels);
    }

    public static void goToUserList(Activity activity, int code) {
        Intent intent = new Intent(activity, UserListActivity.class);
        activity.startActivityForResult(intent, code);
    }


    public static void goToTopicList(Activity activity, int code) {
        Intent intent = new Intent(activity, TopicListActivity.class);
        activity.startActivityForResult(intent, code);
    }

    private SpanCreateListener spanCreateListener = new SpanCreateListener() {
        @Override
        public ClickAtUserSpan getCustomClickAtUserSpan(Context context, UserModel userModel, int color, SpanAtUserCallBack spanClickCallBack) {
            return new CustomClickAtUserSpan(context, userModel, color, spanClickCallBack);
        }

        @Override
        public ClickTopicSpan getCustomClickTopicSpan(Context context, TopicModel topicModel, int color, SpanTopicCallBack spanTopicCallBack) {
            return new CustomClickTopicSpan(context, topicModel, color, spanTopicCallBack);
        }

        @Override
        public LinkSpan getCustomLinkSpan(Context context, String url, int color, SpanUrlCallBack spanUrlCallBack) {
            return new CustomLinkSpan(context, url, color, spanUrlCallBack);
        }
    };

    @OnClick({R2.id.emoji_show_bottom, R2.id.emoji_show_at, R2.id.insert_text_btn, R2.id.emoji_show_topic, R2.id.emoji_edit_text})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.emoji_show_bottom) {
            emojiLayout.hideKeyboard();
            if (emojiLayout.getVisibility() == View.VISIBLE) {
                emojiLayout.setVisibility(View.GONE);
            } else {
                emojiLayout.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.emoji_show_at) {
            goToUserList(RichTextActivity.this, RichTextActivity.REQUEST_USER_CODE_CLICK);
        } else if (id == R.id.insert_text_btn) {
            nameListEd.clear();
            topicModelsEd.clear();
            //如果是一次性插入的，记得补上@
            UserModel userModel = new UserModel();
            userModel.setUser_name("@22222");
            userModel.setUser_id("2222");
            nameListEd.add(userModel);
            userModel = new UserModel();
            userModel.setUser_name("@kkk");
            userModel.setUser_id("23333");
            nameListEd.add(userModel);
            //如果是一次性插入的，记得补上#和#
            TopicModel topicModel = new TopicModel();
            topicModel.setTopicId("333");
            topicModel.setTopicName("#话题话题#");
            topicModelsEd.add(topicModel);
            richEditText.resolveInsertText(RichTextActivity.this, insertContent, nameListEd, topicModelsEd);
            //获取原始数据可以通过以下获取
            richEditText.getRealTopicList();
            richEditText.getRealUserList();
            Log.e(this.getClass().getName(), richEditText.getRealText());
        } else if (id == R.id.emoji_show_topic) {
            goToTopicList(RichTextActivity.this, RichTextActivity.REQUEST_TOPIC_CODE_CLICK);
        } else if (id == R.id.emoji_edit_text) {
            emojiLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 自定义显示可点击的@某人
     */
    public class CustomClickAtUserSpan extends ClickAtUserSpan {

        public CustomClickAtUserSpan(Context context, UserModel userModel, int color, SpanAtUserCallBack spanClickCallBack) {
            super(context, userModel, color, spanClickCallBack);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
            //间隔线
            ds.setStyle(Paint.Style.STROKE);
            PathEffect effects = new DashPathEffect(new float[]{1, 1}, 1);
            ds.setPathEffect(effects);
            ds.setStrokeWidth(5);
        }
    }

    /**
     * 自定义话题#点击回调
     */
    public class CustomClickTopicSpan extends ClickTopicSpan {

        public CustomClickTopicSpan(Context context, TopicModel topicModel, int color, SpanTopicCallBack spanTopicCallBack) {
            super(context, topicModel, color, spanTopicCallBack);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
            ds.setStyle(Paint.Style.FILL_AND_STROKE);
            PathEffect effects = new DashPathEffect(new float[]{1, 1}, 1);
            ds.setPathEffect(effects);
            ds.setStrokeWidth(2);
        }
    }

    /**
     * 自定义显示可点击的手机号码和URL
     */
    public class CustomLinkSpan extends LinkSpan {

        public CustomLinkSpan(Context context, String url, int color, SpanUrlCallBack spanUrlCallBack) {
            super(context, url, color, spanUrlCallBack);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
            ds.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_USER_CODE_CLICK:
                    richEditText.resolveAtResult((UserModel) data.getSerializableExtra(UserListActivity.DATA));
                    break;

                case REQUEST_USER_CODE_INPUT:
                    richEditText.resolveAtResultByEnterAt((UserModel) data.getSerializableExtra(UserListActivity.DATA));
                    break;

                case REQUEST_TOPIC_CODE_INPUT:
                    richEditText.resolveTopicResultByEnter((TopicModel) data.getSerializableExtra(TopicListActivity.DATA));
                    break;

                case REQUEST_TOPIC_CODE_CLICK:
                    richEditText.resolveTopicResult((TopicModel) data.getSerializableExtra(TopicListActivity.DATA));
                    break;
            }
        }
    }
}
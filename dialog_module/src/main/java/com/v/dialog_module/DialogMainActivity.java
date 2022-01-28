package com.v.dialog_module;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.v.dialog_module.bean.DialogBean;
import com.v.dialog_module.databinding.ActivityDialogMainBinding;
import com.v.dialog_module.dialog.loading.ViewLoading;
import com.v.dialog_module.dialog.menu.CustomBottomDialog;
import com.v.dialog_module.dialog.menu.CustomItem;
import com.v.dialog_module.dialog.menu.OnItemClickListener;
import com.v.dialog_module.dialog.select.CustomSelectDialog;
import com.v.dialog_module.fragment.BaseDialogFragment;
import com.v.dialog_module.fragment.BottomDialogFragment;
import com.v.dialog_module.fragment.CustomDialogFragment;
import com.v.dialog_module.popup.CustomPop;
import com.v.dialog_module.popup.CustomPopupWindow;
import com.v.dialog_module.snackbar.SnackBarUtils;
import com.v.dialog_module.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DialogMainActivity
 * Description:
 *
 * @author zhuming
 * @package_name com.v.dialog_module
 * @date 2022/1/27 4:26 下午
 */
public class DialogMainActivity extends AppCompatActivity implements View.OnClickListener {
    public ActivityDialogMainBinding mBinding;
    private CustomPopupWindow popWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_main);
        setListener();
    }

    private void setListener() {
        mBinding.tv1.setOnClickListener(this);
        mBinding.tv21.setOnClickListener(this);
        mBinding.tv22.setOnClickListener(this);
        mBinding.tv23.setOnClickListener(this);
        mBinding.tv24.setOnClickListener(this);
        mBinding.tv25.setOnClickListener(this);
        mBinding.tv26.setOnClickListener(this);
        mBinding.tv31.setOnClickListener(this);
        mBinding.tv32.setOnClickListener(this);
        mBinding.tv33.setOnClickListener(this);
        mBinding.tv4.setOnClickListener(this);
        mBinding.tv51.setOnClickListener(this);
        mBinding.tv52.setOnClickListener(this);
        mBinding.tv61.setOnClickListener(this);
        mBinding.tv62.setOnClickListener(this);
        mBinding.tv7.setOnClickListener(this);
        mBinding.tv81.setOnClickListener(this);
        mBinding.tv82.setOnClickListener(this);
        mBinding.tv83.setOnClickListener(this);
        mBinding.tv9.setOnClickListener(this);
        mBinding.tv101.setOnClickListener(this);
        mBinding.tv102.setOnClickListener(this);
        mBinding.tv103.setOnClickListener(this);
        mBinding.tv104.setOnClickListener(this);
        mBinding.tv105.setOnClickListener(this);
        mBinding.tv106.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_1) {
            showCustomDialog();
        } else if (id == R.id.tv_21) {
            ToastUtils.setToastBackColor(this.getResources().getColor(R.color.gray_black));
            ToastUtils.showToast("自定义吐司");
        } else if (id == R.id.tv_22) {
            ToastUtils.setToastBackColor(this.getResources().getColor(R.color.color_7f000000));
            ToastUtils.showRoundRectToast("自定义吐司");
        } else if (id == R.id.tv_23) {
            ToastUtils.setToastBackColor(this.getResources().getColor(R.color.color_000000));
            ToastUtils.showRoundRectToast("吐司一下", "他发的撒经济法的解放军");
        } else if (id == R.id.tv_24) {
            ToastUtils.showRoundRectToast(R.layout.view_layout_toast_delete);
        } else if (id == R.id.tv_25) {
            ToastUtils.showRoundRectToast(R.layout.view_layout_toast_load);
        } else if (id == R.id.tv_26) {
            ToastUtils.Builder builder = new ToastUtils.Builder(this.getApplication());
            builder
                    .setDuration(Toast.LENGTH_SHORT)
                    .setFill(false)
                    .setGravity(Gravity.CENTER)
                    .setOffset(0)
                    .setDesc("内容内容")
                    .setTitle("标题")
                    .setTextColor(Color.WHITE)
                    .setBackgroundColor(this.getResources().getColor(R.color.blackText))
                    .build()
                    .show();
        } else if (id == R.id.tv_31) {
            CustomPop customPop = new CustomPop(this);
            customPop.setDelayedMsDismiss(2500);
            customPop.setBgAlpha(0.5f);
            customPop.showAsDropDown(mBinding.tv33, 0, -mBinding.tv33.getMeasuredHeight() - mBinding.tv33.getHeight());
        } else if (id == R.id.tv_32) {
            showPopupWindow1();
        } else if (id == R.id.tv_33) {
            showPopupWindow2();
        } else if (id == R.id.tv_4) {
            showCustomBottomDialog();
        } else if (id == R.id.tv_51) {
            showDialogFragment();
        } else if (id == R.id.tv_52) {
            showDialogFragment2();
        } else if (id == R.id.tv_61) {
            showCustomDialog2();
        } else if (id == R.id.tv_62) {
            showCustomDialog3();
        } else if (id == R.id.tv_7) {
            showCustomDialog4();
        } else if (id == R.id.tv_81) {
            ViewLoading.show(this);
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ViewLoading.dismiss(DialogMainActivity.this);
                }
            }.start();
        } else if (id == R.id.tv_82) {
            ViewLoading.show(this, "加载中");
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ViewLoading.dismiss(DialogMainActivity.this);
                }
            }.start();
        } else if (id == R.id.tv_83) {
            ViewLoading.show(this, "加载中", true);
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ViewLoading.dismiss(DialogMainActivity.this);
                }
            }.start();
        } else if (id == R.id.tv_9) {
            //startActivity(new Intent(this, TestActivity.class));
        } else if (id == R.id.tv_101) {
            SnackBarUtils.showSnackBar(this, "滚犊子");
        } else if (id == R.id.tv_102) {
            SnackBarUtils.showSnackBar(this, "滚犊子", "ACTION", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showRoundRectToast("滚犊子啦？");
                }
            });
        } else if (id == R.id.tv_103) {
            SnackBarUtils.showSnackBar(this, "滚犊子", "ACTION", R.drawable.icon_cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showRoundRectToast("滚犊子啦？");
                }
            });
        } else if (id == R.id.tv_104) {
            SnackBarUtils.builder()
                    .setView(view)
                    .setText("滚犊子")
                    .setDuration(SnackBarUtils.DurationType.LENGTH_SHORT)
                    .build()
                    .show();
        } else if (id == R.id.tv_105) {
            Snackbar snackbar = SnackBarUtils.builder()
                    .setView(view)
                    .setText("滚犊子")
                    .setDuration(SnackBarUtils.DurationType.LENGTH_SHORT)
                    .build();
            snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    Log.d("DialogMainActivity", "onDismissed");
                }

                @Override
                public void onShown(Snackbar transientBottomBar) {
                    super.onShown(transientBottomBar);
                    Log.d("DialogMainActivity", "onShown");
                }
            });
            snackbar.show();
        } else if (id == R.id.tv_106) {
            SnackBarUtils.builder()
                    .setBackgroundColor(this.getResources().getColor(R.color.color_7f000000))
                    .setTextSize(14)
                    .setTextColor(this.getResources().getColor(R.color.white))
                    .setTextTypefaceStyle(Typeface.BOLD)
                    .setText("滚犊子")
                    .setMaxLines(4)
                    .centerText()
                    .setActionText("收到")
                    .setActionTextColor(this.getResources().getColor(R.color.color_f25057))
                    .setActionTextSize(16)
                    .setActionTextTypefaceStyle(Typeface.BOLD)
                    .setActionClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showRoundRectToast("滚犊子啦？");
                        }
                    })
                    .setIcon(R.drawable.icon_cancel)
                    .setActivity(DialogMainActivity.this)
                    .setDuration(SnackBarUtils.DurationType.LENGTH_INDEFINITE)
                    .build()
                    .show();
        }
    }


    /**
     * 两种方式
     */
    private void showDialogFragment() {
        final BottomDialogFragment dialog = new BottomDialogFragment(BaseDialogFragment.Local.BOTTOM);
        dialog.setFragmentManager(getSupportFragmentManager());
        dialog.setViewListener(new BottomDialogFragment.ViewListener() {
            @Override
            public void bindView(View v) {
                TextView tv_cancel = (TextView) v.findViewById(R.id.tv_cancel);
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissDialogFragment();
                    }
                });
            }
        });
        dialog.setLayoutRes(R.layout.dialog_bottom_layout);
        dialog.setDimAmount(0.5f);
        dialog.setTag("BottomDialog");
        dialog.setCancelOutside(true);
        //这个高度可以自己设置，十分灵活
        //dialog.setHeight(getScreenHeight() / 2);
        dialog.show();
    }


    private void showDialogFragment2() {
        final List<DialogBean> list = new ArrayList<>();
        for(int a=0 ; a<20 ; a++){
            DialogBean dialogBean = new DialogBean("ooo","杨充","title");
            list.add(dialogBean);
        }

        BottomDialogFragment.create(getSupportFragmentManager())
                .setViewListener(new BottomDialogFragment.ViewListener() {
                    @Override
                    public void bindView(View v) {
                        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        ImageView ivDownload = (ImageView) v.findViewById(R.id.iv_download);

                        recyclerView.setLayoutManager(new LinearLayoutManager(DialogMainActivity.this));
                        DialogListAdapter mAdapter = new DialogListAdapter(DialogMainActivity.this, list);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new DialogListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                            }
                        });
                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.iv_cancel) {
                                } else if (id == R.id.iv_download) {
                                    ToastUtils.showToast("下载");
                                }
                            }
                        };
                        ivCancel.setOnClickListener(listener);
                        ivDownload.setOnClickListener(listener);
                    }
                })
                .setLayoutRes(R.layout.dialog_bottom_layout_list)
                .setDimAmount(0.5f)
                .setTag("BottomDialog")
                .setCancelOutside(true)
                .setHeight(getScreenHeight() / 2)
                .show();
    }


    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        names.add("其他");
        CustomSelectDialog dialog = new CustomSelectDialog(this, R.style.transparentFrameWindowStyle, new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DialogMainActivity.this, names.get(position), Toast.LENGTH_SHORT).show();
            }
        }, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //判断activity是否finish
        if (!this.isFinishing()) {
            dialog.show();
        }
    }

    private void showCustomBottomDialog() {
        new CustomBottomDialog(DialogMainActivity.this)
                .title("这个是标题")
                .setCancel(true,"取消选择")
                .orientation(CustomBottomDialog.VERTICAL)
                .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                    @Override
                    public void click(CustomItem item) {

                    }
                })
                .show();
    }


    /**
     * 这个位置可以自定义的，上下左右都行，很灵活
     */
    private void showPopupWindow1() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_layout,null);
        //处理popWindow 显示内容,自定义布局
        handleLogic(contentView);
        //处理popWindow 显示内容,recycleView
        //handleListView(contentView);
        //创建并显示popWindow
        popWindow = new CustomPopupWindow.PopupWindowBuilder(this)
                //.setView(R.layout.pop_layout)
                .setView(contentView)
                //设置是否可以设置焦点
                .setFocusable(true)
                //弹出popWindow时，背景是否变暗
                .enableBackgroundDark(true)
                //控制亮度
                .setBgDarkAlpha(0.5f)
                //设置是否可以点击弹窗外部消失
                .setOutsideTouchable(true)
                //设置动画
                .setAnimationStyle(R.style.popWindowStyle)
                //设置弹窗关闭监听
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //对话框销毁时
                    }
                })
                //创建弹窗
                .create()
                //传入x，y值位置展示
                .showAsDropDown(mBinding.tv33,0,10);
    }


    private void showPopupWindow2() {
        CustomPopupWindow popWindow =
                new CustomPopupWindow.PopupWindowBuilder(this)
                        .setView(R.layout.view_pop_custom)
                        .setBgDarkAlpha(0.5f)
                        .create();
        popWindow .showAsDropDown(mBinding.tv33,0,  - (mBinding.tv33.getHeight() + popWindow.getHeight()));
        //popWindow.showAtLocation(mButton1, Gravity.NO_GRAVITY,0,0);
    }


    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView           view
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popWindow!=null){
                    popWindow.dismiss();
                }
                String showContent = "";
                int id = v.getId();
                if (id == R.id.menu1) {
                    showContent = "点击 Item菜单1";
                } else if (id == R.id.menu2) {
                    showContent = "点击 Item菜单2";
                }
                ToastUtils.showRoundRectToast(showContent);
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
    }


    private void showCustomDialog2() {
        final CustomDialogFragment dialog = new CustomDialogFragment();
        //CustomDialogFragment customDialogFragment = CustomDialogFragment.create(getSupportFragmentManager());
        dialog.setFragmentManager(getSupportFragmentManager());
        dialog.setTitle("这个是是标题");
        dialog.setContent("这个是弹窗的内容");
        dialog.setCancelContent("取消");
        dialog.setOkContent("确定");
        dialog.setDimAmount(0.0f);
        dialog.setTag("BottomDialog");
        dialog.setCancelOutside(true);
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment.dismissDialogFragment();
                ToastUtils.showRoundRectToast("取消了");
            }
        });
        dialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment.dismissDialogFragment();
                ToastUtils.showRoundRectToast("确定了");
            }
        });
        //这个高度可以自己设置，十分灵活
        //dialog.setHeight(getScreenHeight() / 2);
        dialog.show();
    }


    private void showCustomDialog3() {
        CustomDialogFragment
                .create(getSupportFragmentManager())
                .setTitle("这个是是标题")
                .setContent("这个是弹窗的内容")
                .setOtherContent("其他")
                .setCancelContent("取消")
                .setOkContent("确定")
                .setDimAmount(0.2f)
                .setTag("dialog")
                .setCancelOutside(true)
                .setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("取消了");
                    }
                })
                .setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("确定了");
                    }
                })
                .setOtherListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("其他内容");
                    }
                })
                .show();

    }

    private void showCustomDialog4() {
        CustomDialogFragment
                .create(getSupportFragmentManager())
                .setTitle("这个是是标题")
                .setContent("这个是弹窗的内容这个是弹窗的内容这个是弹窗的内容这个是弹窗的内容")
                //.setCancelContent("取消")
                .setOkContent("确定")
                .setDimAmount(0.2f)
                .setTag("dialog")
                .setCancelOutside(true)
                .setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("取消了");
                    }
                })
                .setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("确定了");
                    }
                })
                .setOtherListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialogFragment.dismissDialogFragment();
                        ToastUtils.showRoundRectToast("其他内容");
                    }
                })
                .show();

    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高px
     */
    public int getScreenHeight() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}

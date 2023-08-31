package com.v.module_camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.v.module_dialog.toast.ToastUtils;
import com.v.module_glide.GlideUtils;

import java.io.File;
import java.util.List;

public class TakePhoneActivity extends AppCompatActivity {

    public int REQUEST_CODE_ALBUM = 111;
    public int REQUEST_CODE_CAPTURE_CROP = 222;
    public int OPEN_CAMERA = 444;

    private ImageView iv_avatar;

    private File imageCropFile;
    private Uri cameraContentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_phone);
        iv_avatar = findViewById(R.id.iv_avatar);
    }

    public void takePhone(View view) {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!XXPermissions.isGranted(this, permissions)) {
            requestPermission(permissions);
        } else {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraintent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            // 指定调用相机拍照后照片的储存路径
            File file = new File(FileUtils.getAppRootDirPath(this) + File.separator + "capture"+ File.separator + System.currentTimeMillis() + ".jpg");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cameraContentUri = FileProvider.getUriForFile(this, "com.v.interviewlearn.fileprovider", file);
                cameraintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                cameraContentUri = Uri.fromFile(file);
            }
            Log.i("zm1234", "takePhone cameraContentUri:"+cameraContentUri.getPath());
            cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, cameraContentUri);
            startActivityForResult(cameraintent, OPEN_CAMERA);
        }
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_ALBUM);
    }

    private void requestPermission(String[] permissions){
        XXPermissions.with(this)
                // 申请多个权限
                .permission(permissions)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        if (!allGranted) {
                            ToastUtils.showToast("获取部分权限成功，但部分权限未正常授予");
                            return;
                        }
                        ToastUtils.showToast("获取录音和日历权限成功");
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            ToastUtils.showToast("被永久拒绝授权，请手动授予录音和日历权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(TakePhoneActivity.this, permissions);
                        } else {
                            ToastUtils.showToast("获取录音和日历权限失败");
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == OPEN_CAMERA){
            Log.i("zm1234", "onActivityResult 相机拍照回调");
            gotoCrop(cameraContentUri);//调用剪裁
        }  else if (requestCode == REQUEST_CODE_ALBUM) {
            Log.i("zm1234", "onActivityResult 打开系统裁剪");
            if (data != null && data.getData() != null) {
                //打开系统裁剪
                gotoCrop(data.getData());
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CAPTURE_CROP) {
            //显示页面上
            if (imageCropFile != null && imageCropFile.getAbsolutePath() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (FileUtils.uri != null) {
                        // 通过存储的uri 查询File
                        imageCropFile = FileUtils.getCropFile(this, FileUtils.uri);
                        Log.i("zm1234", "onActivityResult 111: "+imageCropFile.getAbsolutePath()+" 是否存在："+imageCropFile.exists()+" uri："+FileUtils.uri.getPath());
                        GlideUtils.loadImage(this, imageCropFile.getAbsolutePath(), iv_avatar);
                    }
                } else {
                    Log.i("zm1234", "onActivityResult 222: "+imageCropFile.getAbsolutePath()+" 是否存在："+imageCropFile.exists());
                    GlideUtils.loadImage(this, imageCropFile.getAbsolutePath(), iv_avatar);
                }
            }
        }
    }

    private void gotoCrop(Uri sourceUri) {
        imageCropFile = FileUtils.createImageFile(this, true);
        if (imageCropFile != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.putExtra("crop", "true");

            intent.putExtra("aspectX", 1);    //X方向上的比例
            intent.putExtra("aspectY", 1);    //Y方向上的比例
            intent.putExtra("outputX", 256);  //裁剪区的宽
            intent.putExtra("outputY", 256); //裁剪区的高
            intent.putExtra("scale ", true);  //是否保留比例
            intent.putExtra("return-data", false);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.setDataAndType(sourceUri, "image/*"); //设置数据源
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.uri);
            } else {
                Uri imgCropUri = Uri.fromFile(imageCropFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgCropUri);
            }
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CROP);
        }
    }

    public void audioPhoto(View view) {
        XXPermissions.with(this)
                // 申请多个权限
                .permission(Manifest.permission.RECORD_AUDIO)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        if (!allGranted) {
                            ToastUtils.showToast("获取部分权限成功，但部分权限未正常授予");
                            return;
                        }
                        ToastUtils.showToast("获取录音和日历权限成功");
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            ToastUtils.showToast("被永久拒绝授权，请手动授予录音和日历权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(TakePhoneActivity.this, permissions);
                        } else {
                            ToastUtils.showToast("获取录音和日历权限失败");
                        }
                    }
                });
    }
}
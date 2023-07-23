package com.v.module_flutter.helper;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.plugin.common.MethodChannel;

public interface MethodChannelListener extends MethodChannel.Result {
    @Override
    default void error(@NonNull String errorCode, @Nullable String errorMessage, @Nullable Object errorDetails) {
        Log.e("zm1234", "MyFlutterActivity error() errorCode:" + errorCode + " errorMessage:" + errorMessage + " errorDetails:" + errorDetails);
    }

    @Override
    default void notImplemented() {
        Log.w("zm1234", "MyFlutterActivity notImplemented()");
    }
}

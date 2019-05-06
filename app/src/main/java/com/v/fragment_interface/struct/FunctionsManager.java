package com.v.fragment_interface.struct;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-24 18:32
 */

public class FunctionsManager {

    public static FunctionsManager sManager;


    private HashMap<String, FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;
    private HashMap<String, FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;


    private FunctionsManager() {
        mFunctionNoParamNoResult = new HashMap<>();
        mFunctionWithParamAndResult = new HashMap<>();
        mFunctionWithParamOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();
    }

    public static FunctionsManager getInstance() {
        if (sManager != null) {
            sManager = new FunctionsManager();
        }
        return sManager;
    }

    public FunctionsManager addFunction(FunctionNoParamNoResult function) {
        mFunctionNoParamNoResult.put(function.mFunctionName, function);
        return this;
    }

    public void invokeFunc(String funcName) {
        if (TextUtils.isEmpty(funcName)) {
            return;
        }

        if (mFunctionNoParamNoResult != null) {
            FunctionNoParamNoResult f = mFunctionNoParamNoResult.get(funcName);
            try {
                if (f != null) {
                    f.function();
                } else {
                    throw new FunctionException("Has no this function:" + funcName);
                }
            } catch (FunctionException e) {
                e.printStackTrace();
            }
        }

    }


}

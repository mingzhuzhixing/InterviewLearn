package com.v.url_module;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PoolViews
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.module_live.like_view
 * @date 2022/1/27 10:36 上午
 */
public class PoolViews {
    private PoolViews() {
    }

    public interface Pool<T> {

        @Nullable
        T acquire();

        boolean release(@NonNull T instance);
    }

    public static class SimplePool<T> implements Pool<T> {
        /**
         * 待使用的 和 正在使用的
         */
        private final List<T> mList;

        public SimplePool() {
            mList = new ArrayList<>();
        }

        @Override
        public T acquire() {
            Log.i("ImageView", "---acquire-mReleaseList.size()-->" + mList.size());
            if (mList.size() > 0) {
                T instance = mList.get(0);
                mList.remove(0);
                return instance;
            }
            return null;
        }

        @Override
        public boolean release(@NonNull T instance) {
            if (!isInPool(instance)) {
                mList.add(instance);
                Log.i("ImageView", "---release-22--mReleaseList.size()-->" + mList.size());
                return true;
            }
            return false;
        }

        /**
         * 当前对象是否在释放的池子中
         */
        private boolean isInPool(@NonNull T instance) {
            return mList.contains(instance);
        }
    }
}

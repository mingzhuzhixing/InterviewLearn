package com.v.url_module.like_view;

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
        T acquire(T t);

        boolean release(@NonNull T instance);
    }

    public static class SimplePool<T> implements Pool<T> {
        /**
         * 待使用的 和 正在使用的
         */
        private final List<T> mList;

        private int mPoolSize;

        public SimplePool() {
            mList = new ArrayList<>();
        }

        @Override
        public T acquire(T t) {
            if (mPoolSize > 0) {
                final int lastPooledIndex = mPoolSize - 1;
                T instance = (T) mList.get(lastPooledIndex);
                mPoolSize--;
                return instance;
            } else {
                mList.add(mPoolSize, t);
                mPoolSize++;
                return t;
            }
        }

        @Override
        public boolean release(@NonNull T instance) {
            if (isInPool(instance)) {
                //throw new IllegalStateException("Already in the pool!");
            }
            if (mPoolSize < mList.size()) {
                mPoolSize++;
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

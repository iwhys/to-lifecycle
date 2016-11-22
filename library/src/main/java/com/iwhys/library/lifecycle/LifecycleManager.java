package com.iwhys.library.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Description: 生命周期监听器{@link LifecycleListener}管理者
 * 客户通过调用{@link #add(LifecycleListener)}实现与特定上下文生命周期的联动
 * 其中{@link #mPriorityLifecycleListeners}记录了带有优先级的监听器{@link LifecycleListener.Priority}
 * 优先级越高越先执行生命周期的回调方法
 *
 * @author devil
 */
public class LifecycleManager {

    /**
     * 生命周期监听器容器
     */
    private final ArrayList<LifecycleListener> mLifecycleListeners = new ArrayList<>();

    /**
     * 带优先级的生命周期监听器容器
     */
    private final ArrayList<LifecycleListener.Priority> mPriorityLifecycleListeners = new ArrayList<>();

    /**
     * 获取生命周期管理者对象
     *
     * @param activity activity
     */
    public static LifecycleManager from(Activity activity) {
        return ManagerRetriever.getInstance().get(activity);
    }

    /**
     * 获取生命周期管理者对象
     *
     * @param fragment fragment
     */
    public static LifecycleManager from(Fragment fragment) {
        return ManagerRetriever.getInstance().get(fragment);
    }

    /**
     * 添加生命周期监听器
     *
     * @param lifecycleListener 监听器
     */
    public void add(LifecycleListener lifecycleListener) {
        if (lifecycleListener instanceof LifecycleListener.Priority) {
            mPriorityLifecycleListeners.add((LifecycleListener.Priority) lifecycleListener);
            Collections.sort(mPriorityLifecycleListeners);
        } else {
            mLifecycleListeners.add(lifecycleListener);
        }
    }

    /**
     * 移除生命周期监听器
     *
     * @param lifecycleListener 监听器
     */
    public void remove(LifecycleListener lifecycleListener) {
        if (lifecycleListener instanceof LifecycleListener.Priority) {
            mPriorityLifecycleListeners.remove(lifecycleListener);
        } else {
            mLifecycleListeners.remove(lifecycleListener);
        }
    }

    /**
     * 清空所有生命周期监听器
     */
    public void clear() {
        mLifecycleListeners.clear();
        mPriorityLifecycleListeners.clear();
    }

    /**
     * 以下方法跟生命周期监听器{@link LifecycleListener}对应，由{@link LifecycleFragment}对象调用，实现生命周期联动功能
     */

    void onCreate(Bundle savedInstanceState) {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onCreate(savedInstanceState);
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onCreate(savedInstanceState);
        }
    }

    void onStart() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onStart();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onStart();
        }
    }

    void onResume() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onResume();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onResume();
        }
    }

    void onConfigurationChanged(Configuration newConfig) {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onConfigurationChanged(newConfig);
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onConfigurationChanged(newConfig);
        }
    }

    void onPause() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onPause();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onPause();
        }
    }

    void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onSaveInstanceState(outState);
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onSaveInstanceState(outState);
        }
    }

    void onStop() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onStop();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onStop();
        }
    }

    void onDestroy() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onDestroy();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onDestroy();
        }
    }

    void onTrimMemory(int level){
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onTrimMemory(level);
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onTrimMemory(level);
        }
    }

    void onLowMemory() {
        for (int i = 0; i < mPriorityLifecycleListeners.size(); i++) {
            mPriorityLifecycleListeners.get(i).onLowMemory();
        }
        for (int i = 0; i < mLifecycleListeners.size(); i++) {
            mLifecycleListeners.get(i).onLowMemory();
        }
    }
}

package com.iwhys.library.lifecycle;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Description: 生命周期调度者，特定对象与Activity/Fragment生命周期联动最终都是通过当前类实现的
 *
 * @author devil
 */
@SuppressLint("ValidFragment")
class InnerLifecycleFragment extends Fragment {

    /**
     * 生命周期监听器管理类
     */
    private final LifecycleManager mLifecycleManager = new LifecycleManager();

    /**
     * 外部获取生命周期监听器管理类
     */
    LifecycleManager getLifecycleManager() {
        return mLifecycleManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleManager.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleManager.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleManager.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mLifecycleManager.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPause() {
        super.onPause();
        mLifecycleManager.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLifecycleManager.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        mLifecycleManager.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mLifecycleManager.onDestroy();
        mLifecycleManager.clear();
        super.onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mLifecycleManager.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mLifecycleManager.onLowMemory();
    }
}

package com.iwhys.library.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.HashMap;

import static android.R.attr.key;

/**
 * Description: 单例,用来创建或者从对应的Activity/Fragment中获取要参数生命周期联动的对象
 *
 * @author devil
 */
class InnerManagerRetriever implements Handler.Callback {

    private final static String TAG = "lifecycle";

    /**
     * 特定的生命周期Fragment的TAG
     */
    private static final String LIFECYCLE_FRAGMENT_TAG = "lifecycle_fragment_tag";
    /**
     * 过程中移除缓存的fragment的消息标志
     */
    private static final int REMOVE = 1;

    /**
     * 等待提交被提交到Activity的Fragment容器
     */
    private final HashMap<FragmentManager, InnerLifecycleFragment> mPendingLifecycleFragments = new HashMap<>();

    /**
     * 发送消息handler
     */
    private final Handler mHandler = new Handler(this);

    /**
     * 获取实例
     */
    static InnerManagerRetriever getInstance(){
        return ClassHolder.INSTANCE;
    }

    /**
     * 静态容器
     */
    private final static class ClassHolder{
        private final static InnerManagerRetriever INSTANCE = new InnerManagerRetriever();
    }

    /**
     * 构造方法
     */
    private InnerManagerRetriever(){
    }

    /**
     * 从Activity中获取对应的生命周期管理者对象
     * @param activity 特定界面的activity
     * @return 生命周期管理者对象
     */
    LifecycleManager get(Activity activity) {
        if (isActivityDestroyed(activity)) {
            return null;
        }
        FragmentManager fm = activity.getFragmentManager();
        return getManager(fm);
    }

    /**
     * 从Fragment中获取对应的生命周期管理者对象
     * @param fragment 特定界面的fragment
     * @return 生命周期管理者对象
     */
    LifecycleManager get(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null || isActivityDestroyed(activity)) {
            Log.w(TAG, "注意：Fragment还没有附加到Activity，无法关联生命周期，声明周期管理对象为null");
            return null;
        }
        return get(activity);
    }

    /**
     * 利用FragmentManager从activity获取或者为Activity绑定一个生命周期Fragment，并获取其中的生命周期管理者对象
     * @param fm fragment管理器
     * @return 生命周期管理者对象
     */
    private LifecycleManager getManager(FragmentManager fm){
        InnerLifecycleFragment fragment = (InnerLifecycleFragment) fm.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
        if (fragment == null) {
            fragment = mPendingLifecycleFragments.get(fm);
            if (fragment == null) {
                fragment = new InnerLifecycleFragment();
                mPendingLifecycleFragments.put(fm, fragment);
                fm.beginTransaction().add(fragment, LIFECYCLE_FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(REMOVE, fm).sendToTarget();
            }
        }
        return fragment.getLifecycleManager();
    }

    /**
     * 处理消息
     */
    @Override
    public boolean handleMessage(Message msg) {
        boolean handled = true;
        Object removed = null;
        if(msg.what == REMOVE){
            FragmentManager fm = (FragmentManager) msg.obj;
            removed = mPendingLifecycleFragments.remove(fm);
        } else {
            handled = false;
        }
        if (handled &&  removed == null){
            Log.w(TAG, "注意：移除指定的生命周期Fragment失败");
        }
        return handled;
    }

    /**
     * 17一下的api默认Activity没有销毁，需要自行判断
     * @param activity activity
     */
    private static boolean isActivityDestroyed(Activity activity) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed();
    }

}

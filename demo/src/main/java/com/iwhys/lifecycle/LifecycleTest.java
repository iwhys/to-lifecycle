package com.iwhys.lifecycle;

import android.content.res.Configuration;
import android.os.Bundle;

import com.iwhys.library.lifecycle.LifecycleListener;
import com.iwhys.library.lifecycle.LifecycleManager;


/**
 * Description: 测试生命周期
 *
 * @author devil
 */
public class LifecycleTest {

    public LifecycleTest(final MainActivity activity){
        LifecycleListener lifecycleListener = new LifecycleListener.Adapter() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                activity.printText("LifecycleTest->onCreate(): ", false);
            }

            @Override
            public void onStart() {
                activity.printText("LifecycleTest->onStart(): ", false);
            }

            @Override
            public void onResume() {
                activity.printText("LifecycleTest->onResume(): ", false);
            }

            @Override
            public void onPause() {
                activity.printText("LifecycleTest->onPause(): ", false);
            }

            @Override
            public void onSaveInstanceState(Bundle outState) {
                activity.printText("LifecycleTest->onSaveInstanceState(): ", false);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                activity.printText("LifecycleTest->onConfigurationChanged(): ", false);
            }

            @Override
            public void onStop() {
                activity.printText("LifecycleTest->onStop(): ", false);
            }

            @Override
            public void onDestroy() {
                activity.printText("LifecycleTest->onDestroy(): ", false);
            }

            @Override
            public void onLowMemory() {
                activity.printText("LifecycleTest->onLowMemory(): ", false);
            }

            @Override
            public void onTrimMemory(int level) {
                activity.printText("LifecycleTest->onTrimMemory(): ", false);
            }
        };
        LifecycleListener lifecycleListenerPriority = new LifecycleListener.Priority() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                activity.printText("LifecycleTest->onCreate(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onStart() {
                activity.printText("LifecycleTest->onStart(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onResume() {
                activity.printText("LifecycleTest->onResume(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onPause() {
                activity.printText("LifecycleTest->onPause(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                activity.printText("LifecycleTest->onConfigurationChanged(): ", false);
            }

            @Override
            public void onSaveInstanceState(Bundle outState) {
                activity.printText("LifecycleTest->onSaveInstanceState(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onStop() {
                activity.printText("LifecycleTest->onStop(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onDestroy() {
                activity.printText("LifecycleTest->onDestroy(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onLowMemory() {
                activity.printText("LifecycleTest->onLowMemory(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            public void onTrimMemory(int level) {
                activity.printText("LifecycleTest->onTrimMemory(): Priority：" + getPriority() + " --- ", false);
            }

            @Override
            protected int getPriority() {
                return 9999;
            }
        };
        LifecycleManager.getInstance(activity).add(lifecycleListener);
        LifecycleManager.getInstance(activity).add(lifecycleListenerPriority);
    }

}

package com.iwhys.library.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Description: 生命周期监听器
 *
 * @author devil
 */
public interface LifecycleListener {

    /**
     * Callback for when {@link Fragment#onCreate(Bundle)} or {@link Activity#onCreate(Bundle)} is called.
     */
    void onCreate(Bundle savedInstanceState);

    /**
     * Callback for when {@link Fragment#onStart()}} or {@link Activity#onStart()} is called.
     */
    void onStart();

    /**
     * Callback for when {@link Fragment#onResume()}} or {@link Activity#onResume()} is called.
     */
    void onResume();

    /**
     * Callback for when {@link Fragment#onConfigurationChanged(Configuration)}} or {@link Activity#onConfigurationChanged(Configuration)} is called.
     */
    void onConfigurationChanged(Configuration newConfig);

    /**
     * Callback for when {@link Fragment#onPause()}} or {@link Activity#onPause()} is called.
     */
    void onPause();

    /**
     * Callback for when {@link Fragment#onSaveInstanceState(Bundle)}} or {@link Activity#onSaveInstanceState(Bundle)} is called.
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * Callback for when {@link Fragment#onStop()}} or {@link Activity#onStop()} is called.
     */
    void onStop();

    /**
     * Callback for when {@link Fragment#onDestroy()}} or {@link Activity#onDestroy()} is called.
     */
    void onDestroy();

    /**
     * Callback for when {@link Fragment#onTrimMemory(int)}} or {@link Activity#onTrimMemory(int)} is called.
     */
    void onTrimMemory(int level);

    /**
     * Callback for when {@link Fragment#onLowMemory()}} or {@link Activity#onLowMemory()} is called.
     */
    void onLowMemory();


    /**
     * Listener Adapter
     */
    abstract class Adapter implements LifecycleListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onResume() {
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onPause(){
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
        }

        @Override
        public void onStop() {
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public void onTrimMemory(int level) {
        }

        @Override
        public void onLowMemory() {
        }

    }


    /**
     * Listener with priority
     */
    abstract class Priority extends Adapter implements Comparable<Priority> {

        protected int getPriority(){
            return 0;
        }

        @Override
        public int compareTo(Priority priority) {
            return priority.getPriority() - getPriority();
        }
    }

}

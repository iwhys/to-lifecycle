package com.iwhys.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        new LifecycleTest(this);
        printText("MainActivity->onCreate(): ", true);
    }

    @Override
    public void onStart() {
        super.onStart();
        printText("MainActivity->onStart(): ", true);
    }

    @Override
    public void onResume() {
        super.onResume();
        printText("MainActivity->onResume(): ", true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        printText("MainActivity->onRequestPermissionsResult(): ", true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        printText("MainActivity->onActivityResult(): ", true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        printText("MainActivity->onResume(): ", true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        printText("MainActivity->onPause(): ", true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        printText("MainActivity->onSaveInstanceState(): ", true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        printText("MainActivity->onStop(): ", true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printText("MainActivity->onDestroy(): ", true);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        printText("MainActivity->onLowMemory(): ", true);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        printText("MainActivity->onTrimMemory(): ", true);
    }

    public void printText(String text, boolean nextLine){
        mTextView.append("\n");
        if (nextLine){
            mTextView.append("\n");
        }
        mTextView.append(text + SystemClock.uptimeMillis());
    }
}

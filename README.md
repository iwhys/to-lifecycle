# to-lifecycle
An useful library lets Object get the lifecycle of Activity/Fragment.

## Usage

This repository can be found on JitPack:

https://jitpack.io/#iwhys/to-lifecycle

1) Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

2) Add the dependency:
```
dependencies {
    compile 'com.github.iwhys:to-lifecycle:v1.0.0'
}
```

3) Any Object to get the lifecycle of Activity/Fragment, only need implements the interface LifecycleListener, or extends its Adapter(LifecycleListener.Adapter/LifecycleListener.Priority), and then add to the LifecycleManager.
With the adapter class, you only need implement the method follow your needs. 
```
public class ToGetLifecycleObject extends LifecycleListener.Adapter {

    public ToGetLifecycleObject(Activity activity) {
        LifecycleManager.getInstance(activity).add(this);
    }
    
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
    public void onPause() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int level) {
    }

}
```
4) See the demo for more details, and good luck.

## Thanks
* glide
https://github.com/bumptech/glide

## License
```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
```
package test.bwie.com.myshoppingcard;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 黑白 on 2017/9/9.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initXUtils();
    }

    private void initXUtils() {
        x.Ext.init(this);
    }
}

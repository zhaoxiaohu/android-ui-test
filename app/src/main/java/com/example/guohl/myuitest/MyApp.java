package com.example.guohl.myuitest;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class MyApp extends Application {

    private static final String TAG = MyApp.class.getSimpleName();


    private static MyApp context;

    public static MyApp getInst() {
        return context;
    }

    /*
         * (non-Javadoc)
         *
         * @see android.app.Application#onCreate()
         */
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        setDefaultUncaughtExceptionHandler();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(2)
                .memoryCacheSize(4 * 1024 * 1024)
                // 4 Mb
                .discCacheSize(128 * 1024 * 1024)
                // 128 Mb
                /*.denyCacheImageMultipleSizesInMemory()*/
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context, 30 * 1000, 30 * 1000)) // connectTimeout (30 s), readTimeout (30 s)超时时间
                /*
                 * .imageDownloader( new
				 * ExtendedImageDownloader(getApplicationContext()))
				 */
                // Not necessary in common.
                // remove .enableLogging()
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private static void setDefaultUncaughtExceptionHandler() {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    // e.printStackTrace();
                    Log.e("Uncaught Exception 0",
                            t.toString() + " " + e);
                }
            });
        } catch (SecurityException e) {
            // e.printStackTrace();
            Log.e("Uncaught Exception xx",
                    e.toString());
        }
    }

}

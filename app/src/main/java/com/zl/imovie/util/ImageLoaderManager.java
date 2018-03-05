package com.zl.imovie.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zl.imovie.R;

import java.net.ContentHandler;

/**
 * @author zl
 * @function 初始化UniverImageLoader，并用来加载网络图片
 */

public class ImageLoaderManager {

    //标明最多可以有多少条线程
    private static final int THREAD_COUNT = 4;
    //标明图片加载的优先级
    private static final int PRIORITY = 2;
    //标明最多缓存多少图片
    private static final int DISK_CACHE_SIZE = 50 * 1024;
    //连接超时时间
    private static final int CONNECTION_TIME_OUT = 5 * 1000;
    //读取超时时间
    private static final int READ_TIME_OUT = 30 * 1000;

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    /**
     * 单例模式的私有构造方法
     *
     * @param context
     */
    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT) //配置图片下载线程的最大数量
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)
                .denyCacheImageMultipleSizesInMemory() //防止缓存多套尺寸的图片到内存中
                .memoryCache(new WeakMemoryCache()) //使用弱引用内存缓存
                .diskCacheSize(DISK_CACHE_SIZE) //硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO) //图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions()) //默认图片加载配置
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))
                .writeDebugLogs() //debug环境会输出日志
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    public static ImageLoaderManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 默认的图片显示Options,可设置图片的缓存策略，编解码方式等，非常重要
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new
                DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_user_avatar)
                .showImageOnFail(R.drawable.default_user_avatar)
                .cacheInMemory(true) //设置下载的图片是否缓存在内存中, 重要，否则图片不会缓存到内存中
                .cacheOnDisk(true) //设置下载的图片是否缓存在SD卡中, 重要，否则图片不会缓存到硬盘中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565) //设置图片的解码类型//
                .decodingOptions(new BitmapFactory.Options()) //设置图片的解码配置
                .resetViewBeforeLoading(true) //设置图片在下载前是否重置，复位
                .build();
        return options;
    }

    public void displayImage(){

    }

}

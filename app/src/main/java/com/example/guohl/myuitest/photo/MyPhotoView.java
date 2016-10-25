package com.example.guohl.myuitest.photo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guohl.myuitest.R;
import com.example.guohl.myuitest.imageloader.DisplayImgOpt;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guohl on 2016/10/25.
 */

public class MyPhotoView extends ViewGroup {

    private Context context;
    private final static int MAX_COUNT = 9;
    private List<String> urlList = new ArrayList<>(MAX_COUNT);
    private List<ImageView> imageViewList = new ArrayList<>(MAX_COUNT);
    //
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    //
    private static final String tag = MyPhotoView.class.getSimpleName();

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),
                attrs);
    }

    public MyPhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPhotoView(Context context) {
        this(context, null);
    }

    public MyPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);//必须

        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyPhotoView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            if (urlList.size() >= 9) {
                break;
            }

            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyPhotoView_url0: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(0, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url1: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(1, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url2: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(2, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url3: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(3, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url4: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(4, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url5: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(5, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url6: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(6, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url7: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(7, s);
                    }
                    break;
                }
                case R.styleable.MyPhotoView_url8: {
                    String s = a.getString(attr);
                    if (!TextUtils.isEmpty(s)) {
                        urlList.add(8, s);
                    }
                    break;
                }

            }
        }
        a.recycle();      /**      * 获得绘制文本的宽和高      */

        Log.e(tag, "urls: " + Arrays.toString(urlList.toArray()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(tag, "onMeasure");

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = widthSize;
        int height = heightSize;

        //TODO 缓存View--不多次创建
        removeAllViews();
        if (widthMode == MeasureSpec.EXACTLY) {

            Log.e(tag, "MeasureSpec.EXACTLY");

            // 子view测量
            int size = urlList.size();
            if (size > 0) {
                if (size == 1) {

                    ImageView v;
                    try {
                        v = imageViewList.get(0);
                    } catch (IndexOutOfBoundsException e) {
                        v = new ImageView(context);
                        imageViewList.add(0, v);
                    }
                    LayoutParams params = v.getLayoutParams();
                    if (params == null) {
                        params = new ViewGroup.LayoutParams(widthSize, heightSize);
                    } else {
                        params.width = widthSize;
                        params.height = heightSize;
                    }
                    v.setLayoutParams(params);
                    addView(v);

                    // 1 item view
                    width = widthSize;
                    height = heightSize;
                } else {
                    int viewItemWidth = widthSize / 3;
                    int viewItemHeight = heightSize / 3;

                    for (int i = 0; i < size; i++) {

                        ImageView v;
                        try {
                            v = imageViewList.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            v = new ImageView(context);
                            imageViewList.add(i, v);
                        }
//                        ImageView v = new ImageView(context);
                        LayoutParams params = v.getLayoutParams();
                        if (params == null) {
                            params = new ViewGroup.LayoutParams(viewItemWidth, viewItemHeight);
                        } else {
                            params.width = viewItemWidth;
                            params.height = viewItemHeight;
                        }
                        v.setLayoutParams(params);
                        addView(v);
                    }
                    // 2--9 item view
                    width = widthSize;
                    height = ((size - 1) / 3 + 1) * viewItemHeight;
                }
            }
        } else {
            //TODO 更多测量
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(tag, "onLayout");
        int count = getChildCount();
        if (1 == count) {
            ImageView view = (ImageView) getChildAt(0);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            view.layout(0, 0, params.width, params.height);
            Log.e(tag, "view " + 0 + ": width: " + params.width+ "  height: " + params.height);
            return;
        } else {

            int viewItemWidth = getWidth() / 3;
            int viewItemHeight = getHeight() / 3;
            int row = 0;//行
            int columns = 0;//列
            Log.e(tag, "onLayout width: " + getWidth()+ " height: " + getHeight()
                    + " ,item width: " + viewItemWidth + " itemheight: " + viewItemHeight);

            for (int i = 0; i < count; i++) {
                row = i / 3;
                columns = i % 3;

                ImageView view = (ImageView) getChildAt(i);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                int left = columns * viewItemWidth;
                int top = row * viewItemHeight;
                int right = columns * viewItemWidth + params.width;
                int bottom = row * viewItemHeight + params.height;
                view.layout(left,top,right,bottom);

                Log.e(tag, "view " + i + ": " + left+ " " + top+ " " + right + " " + bottom);
            }
        }
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {

        Log.e(tag, "onDraw");

        super.onDraw(canvas);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView view = (ImageView) getChildAt(i);
            Boolean b = (Boolean) view.getTag();

            if (b == null || b.equals(Boolean.FALSE)) {
                view.setTag(Boolean.TRUE);

                LayoutParams params = view.getLayoutParams();
//                imageLoader.displayImage(urlList.get(i), view);
                imageLoader.displayImage(urlList.get(i), view, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        ((ImageView) view).setImageBitmap(loadedImage);
                        view.postInvalidate();
                        Log.e(tag, "CCCCCCCCCC " + imageUri + " data: " + ((loadedImage == null) ? "null" : loadedImage.toString()));
                    }
                });
            }
        }
    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//
//        if (imageLoader != null) {
//            imageLoader.stop();
//        }
//    }
}

package com.example.guohl.myuitest.customview.ViewGroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guohl.myuitest.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guohl on 2016/10/25.
 */

public class MyPhotoView extends ViewGroup {

    private static final String tag = MyPhotoView.class.getSimpleName();

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;
    private final static int MAX_COUNT = 9;
    private List<String> urlList = new ArrayList<>(MAX_COUNT);
    private List<ImageView> imageViewList = new ArrayList<>(MAX_COUNT);

    private int mViewItemWidth;
    private int mViewItemHeight;
    private final static int DIVIDER_SPACING_DEFAULT = 2;//dp
    private int mDividerSpacing = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, DIVIDER_SPACING_DEFAULT, getResources().getDisplayMetrics());
    ;// 图片间距

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

            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyPhotoView_dividerSpacing: {
                    mDividerSpacing = a.getDimensionPixelOffset(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, DIVIDER_SPACING_DEFAULT, getResources().getDisplayMetrics()));
//                    Log.i(tag, "MyPhotoView_dividerSpacing defValue: " + defValue +" mDividerSpacing: " + mDividerSpacing);
                    break;
                }
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(tag, "onMeasure");

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = widthSize;
        int height = heightSize;

        Log.i(tag, "onMeasure widthSize: " + widthSize + " heightSize: " + heightSize);

        removeAllViews();
        if (widthMode == MeasureSpec.EXACTLY) {

            Log.i(tag, "MeasureSpec.EXACTLY");

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
                    setImageViewDisplayAttribute(v);
                    addView(v);

                    // 1 item view
                    width = widthSize;
                    height = heightSize;
                } else {
                    mViewItemWidth = (widthSize - 2 * mDividerSpacing) / 3;
                    mViewItemHeight = (heightSize - 2 * mDividerSpacing) / 3;

                    for (int i = 0; i < size; i++) {

                        ImageView v;
                        try {
                            v = imageViewList.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            v = new ImageView(context);
                            imageViewList.add(i, v);
                        }
                        setImageViewDisplayAttribute(v);
                        LayoutParams params = v.getLayoutParams();
                        if (params == null) {
                            params = new ViewGroup.LayoutParams(mViewItemWidth, mViewItemHeight);
                        } else {
                            params.width = mViewItemWidth;
                            params.height = mViewItemHeight;
                        }
                        v.setLayoutParams(params);
                        addView(v);
                    }
                    // 2--9 item view
                    width = widthSize;
                    height = ((size - 1) / 3 + 1) * mViewItemHeight + ((size - 1) / 3) * mDividerSpacing;
                }
            }
        } else {
            //TODO 更多测量
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(tag, "onLayout");
        int count = getChildCount();
        if (1 == count) {
            ImageView view = (ImageView) getChildAt(0);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            view.layout(0, 0, params.width, params.height);
            Log.i(tag, "view " + 0 + ": width: " + params.width + "  height: " + params.height);
            return;
        } else {

            int row = 0;//行
            int columns = 0;//列
            Log.i(tag, "onLayout width: " + getWidth() + " height: " + getHeight()
                    + " ,item width: " + mViewItemWidth + " itemheight: " + mViewItemHeight);

            for (int i = 0; i < count; i++) {
                row = i / 3;
                columns = i % 3;

                ImageView view = (ImageView) getChildAt(i);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                int left = columns * (mViewItemWidth + mDividerSpacing);
                int top = row * (mViewItemHeight + mDividerSpacing);
                int right = left + params.width;
                int bottom = top + params.height;
                view.layout(left, top, right, bottom);

//                Log.i(tag, "view " + i + ": " + left + " " + top + " " + right + " " + bottom);
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

        Log.i(tag, "onDraw");

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
                    }
                });
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i(tag, "onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.i(tag, "onDetachedFromWindow");
        super.onDetachedFromWindow();
        if (imageLoader != null) {
            imageLoader.stop();
        }
    }

    private void setImageViewDisplayAttribute(ImageView v) {
//        v.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        v.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}

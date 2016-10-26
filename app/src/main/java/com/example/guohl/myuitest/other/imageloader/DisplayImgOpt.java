package com.example.guohl.myuitest.other.imageloader;

import android.graphics.Bitmap;

import com.example.guohl.myuitest.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class DisplayImgOpt {
	private static DisplayImageOptions options;
	private static DisplayImageOptions thumbnailOptions;
	
	public static DisplayImageOptions getOptions() {
		if (null == options) {
			options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.imageloder_default_image_loading)
					.showImageForEmptyUri(R.drawable.imageloder_default_image_empty)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.displayer(new FadeInBitmapDisplayer(200))
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.showImageOnLoading(R.drawable.imageloder_default_image_loading)
					.showImageOnFail(R.drawable.imageloder_default_image_loading_fail)
//					.showImageForEmptyUri(R.drawable.pic_loading_empty_uri)
					.build();
		}

		return options;
	}
	
	public static DisplayImageOptions getThumbnailOptions() {
		if (null == thumbnailOptions) {
			thumbnailOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.imageloder_default_image_empty)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.build();
		}

		return thumbnailOptions;
	}
}

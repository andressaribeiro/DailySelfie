package com.coursera.dailyselfie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelfieViewAdapter extends BaseAdapter {

	private static final String SELFIE_STORAGE = "/DCIM/Daily";
	ViewHolder holder = new ViewHolder();
	private final List<SelfieRecord> mItems = new ArrayList<SelfieRecord>();
	private final Context mContext;

	public SelfieViewAdapter(Context context) {
		mContext = context;
		addAllViews();
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final SelfieRecord currentRecord = mItems.get(position);

		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		RelativeLayout itemLayout = (RelativeLayout) convertView;

		if (convertView == null) {
			itemLayout = (RelativeLayout) inflater.inflate(R.layout.daily_selfie_view, parent, false);
			holder.selfieImage = (ImageView) itemLayout.findViewById(R.id.selfie_image);
			holder.selfieTitle = (TextView) itemLayout.findViewById(R.id.selfie_title);
			itemLayout.setTag(holder);
		}

		holder = (ViewHolder) itemLayout.getTag();
		holder.selfieImage.setImageBitmap(currentRecord.getSelfieBitmap());
		holder.selfieTitle.setText(currentRecord.getSelfieTitle());

		return itemLayout;
	}

	private Bitmap setPicture(String path) {
		int scaleFactor = 5;

		BitmapFactory.Options bmOptions = new BitmapFactory.Options();

		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);

		return bitmap;
	}

	public void addAllViews() {
		File folder = getAlbumDirectory();
		File[] fileList = folder.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			String filePath = fileList[i].getAbsolutePath();
			String selfieName = filePath.substring(31, 50);

			SelfieRecord listItem = new SelfieRecord();
			Bitmap mBitmap = setPicture(filePath);
			listItem.setSelfieBitmap(mBitmap);
			listItem.setSelfieTitle(selfieName);

			add(listItem);
		}
	}

	private File getAlbumDirectory() {
		File storageDir = null;

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

			storageDir = getStorageDirectory();

			if (storageDir != null) {
				if (!storageDir.mkdirs()) {
					if (!storageDir.exists()) {
						Log.d("Daily", "failed to create directory");
						return null;
					}
				}
			}

		} else {
			Log.v(mContext.getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
		}

		return storageDir;
	}

	public File getStorageDirectory() {
		return new File(Environment.getExternalStorageDirectory() + SELFIE_STORAGE);
	}

	public void add(SelfieRecord item) {
		mItems.add(item);
		notifyDataSetChanged();
	}

	static class ViewHolder {
		ImageView selfieImage;
		TextView selfieTitle;
	}

}

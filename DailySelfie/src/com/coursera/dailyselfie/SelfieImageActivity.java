package com.coursera.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class SelfieImageActivity extends Activity {

	ImageView image;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity);

		Intent intent = getIntent();
		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("bitmap");

		image = (ImageView) findViewById(R.id.imageView2);
		image.setImageBitmap(bitmap);

	}

}

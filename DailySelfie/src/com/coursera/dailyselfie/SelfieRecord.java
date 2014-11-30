package com.coursera.dailyselfie;

import android.graphics.Bitmap;

public class SelfieRecord {

	private String selfieTitle;
	private Bitmap selfieBitmap;

	public SelfieRecord(String selfieTitle) {
		this.selfieTitle = selfieTitle;
	}

	public SelfieRecord() {
	}

	public String getSelfieTitle() {
		return selfieTitle;
	}

	public void setSelfieTitle(String selfieTitle) {
		this.selfieTitle = selfieTitle;
	}

	public Bitmap getSelfieBitmap() {
		return selfieBitmap;
	}

	public void setSelfieBitmap(Bitmap selfieBitmap) {
		this.selfieBitmap = selfieBitmap;
	}

	@Override
	public String toString() {
		return String.format("Selfie Title: %s", selfieTitle);

	}
}

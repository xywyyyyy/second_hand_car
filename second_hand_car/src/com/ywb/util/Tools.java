package com.ywb.util;

import android.os.Environment;

public class Tools {
	/**
	 * ºÏ≤È «∑Ò¥Ê‘⁄SDø®
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}

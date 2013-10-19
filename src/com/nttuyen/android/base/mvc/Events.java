package com.nttuyen.android.base.mvc;

import com.nttuyen.android.base.Callback;

/**
 * @author nttuyen266@gmail.com
 */
public class Events {
	public static void on(Model model, String event, Callback callback) {
		model.register(event, callback);
	}
	public static void off(Model model, String event, Callback callback) {
		model.unregister(event, callback);
	}
	public static void off(Model model, String event) {
		model.unregister(event);
	}
}

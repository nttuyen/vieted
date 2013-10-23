package com.nttuyen.android.base.mvc;

import com.nttuyen.android.base.Callback;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nttuyen266@gmail.com
 */
public class Events {
	public static void on(Model model, String event, Callback callback) {
		if(model != null && callback != null) {
			model.on(event, callback);
		}
	}

	public static void on(Model model, String event, String callback, Object context) {
		if(model == null || callback == null || context == null || event == null) {
			return;
		}

		on(model, event, Callback.MethodCallback.newInstance(callback, context));
	}

	public static void on(Model model, String event, Method method, Object context) {
		if(model != null && method != null && context != null) {
			on(model, event, new Callback.MethodCallback(method, context));
		}
	}

	public static void off(Model model, String event) {
		if(model != null) {
			model.off(event);
		}
	}
	public static void off(Model model) {
		if(model != null) {
			model.off();
		}
	}
}

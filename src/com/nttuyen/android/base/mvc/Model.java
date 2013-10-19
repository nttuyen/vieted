package com.nttuyen.android.base.mvc;

import com.nttuyen.android.base.Callback;

import java.util.*;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class Model {
	private Map<String, Set<Callback>> handlers = new HashMap<String, Set<Callback>>();

	void register(String event, Callback callback) {
		Set<Callback> set = null;
		if(!handlers.containsKey(event)) {
			set = new LinkedHashSet<Callback>();
		} else {
			set = this.handlers.get(event);
		}

		set.add(callback);
		handlers.put(event, set);
	}
	void unregister(String event, Callback callback) {
		if(handlers.containsKey(event)) {
			return;
		}

		Set<Callback> set = handlers.get(event);
		set.remove(callback);
		handlers.put(event, set);
	}
	void unregister(String event) {
		if(handlers.containsKey(event)) {
			return;
		}
		handlers.remove(event);
	}

	protected void trigger(String eventName, Object... params) {
		if(!handlers.containsKey(eventName)) {
			return;
		}
		Set<Callback> set = handlers.get(eventName);
		for(Callback callback : set) {
			callback.execute(params);
		}
	}

	public abstract void fetch();
	public abstract void save();

	public static abstract class Collection extends Model {

	}
}

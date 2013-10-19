package com.vieted.android.app.v2.model;

import android.os.AsyncTask;
import com.nttuyen.android.base.mvc.Events;
import com.nttuyen.android.base.mvc.Model;

/**
 * @author nttuyen266@gmail.com
 */
public class VoidModel extends Model{
	@Override
	public void fetch() {
		//This is temporary block
		AsyncTask<Void, Void, Void> async = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				try {
					Thread.sleep(10000);
				} catch (Exception e){}
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				trigger(Events.ON_READY, VoidModel.this);
			}
		};

		this.trigger(Events.ON_START_LOADING, this);
		async.execute(null);
		//End temporary block

		//Code should be
		//this.trigger(Events.ON_READY);
	}

	@Override
	public void save() {
		throw new UnsupportedOperationException("un-supported");
	}
}

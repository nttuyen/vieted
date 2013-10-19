package com.vieted.android.app.v2.model;

import com.nttuyen.android.base.mvc.RestModel;
import com.vieted.android.app.Const;

/**
 * @author nttuyen266@gmail.com
 */
public class VietEdModel extends RestModel {
	private String id;
	private String name;
	private String url;

	@Override
	public String url() {
		return Const.VIETED_API_URL_BASE + url;
	}

	@Override
	public void save() {
		throw new UnsupportedOperationException();
	}
}

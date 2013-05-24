package com.vieted.android.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/23/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class VietEdLectureView extends RelativeLayout {

    protected LayoutInflater mInflater;

    public VietEdLectureView(Context context) {
        super(context);
    }

    public VietEdLectureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


}

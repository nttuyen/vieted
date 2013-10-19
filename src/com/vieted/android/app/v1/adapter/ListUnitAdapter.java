package com.vieted.android.app.v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServiceException;
import com.nttuyen.android.base.service.observer.ServiceObserver;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.dto.Course;
import com.vieted.android.app.v1.dto.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class ListUnitAdapter extends VietEdListBaseAdapter<Unit> {
    public ListUnitAdapter(Context context, Service<Map<String, String>, Course> service) {
        super(context, null);
        //this.mLayoutInflater = LayoutInflater.from(context);

        this.service = service;
        if(this.service != null ) {
            try {
                Course course = (Course)service.get();
                this.items = course.getSyllabus().getUnits();
                if(this.items == null) {
                    this.items = Collections.EMPTY_LIST;
                }
            } catch (ServiceException e) {
                this.items = Collections.EMPTY_LIST;
            }
            this.count = this.items.size();

            this.service.registerObserver(new ServiceObserver() {
                @Override
                public void onReady(Service service) {
                    try {
                        Course course = (Course)service.get();
                        items = course.getSyllabus().getUnits();
                        if(items == null) {
                            items = Collections.EMPTY_LIST;
                        }
                    } catch (ServiceException e) {
                        items = Collections.EMPTY_LIST;
                    }
                    count = items.size();
                    notifyDataSetChanged();
                }

                @Override
                public void onChange(Service service) {
                    onReady(service);
                }
                @Override
                public void onInit(Service service) {}
                @Override
                public void onStart(Service service) {}
                @Override
                public void onCancel(Service service) {}
                @Override
                public void onFinish(Service service) {}
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView== null)
            vi= mLayoutInflater.inflate(R.layout.item_row_rating_bar,null);
        TextView name = (TextView) vi.findViewById(R.id.listRateText1);
        TextView percent = (TextView) vi.findViewById(R.id.listRateText2);
        RatingBar ratingBar = (RatingBar) vi.findViewById(R.id.ratingbar);

        name.setText(this.items.get(position).getName());
        percent.setText(this.items.get(position).getProgress() + "%");
        ratingBar.setClickable(false);
        ratingBar.setRating((float)this.items.get(position).getStars());
        return vi;
    }
}

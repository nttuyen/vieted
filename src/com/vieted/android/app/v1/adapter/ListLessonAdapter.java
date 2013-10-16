package com.vieted.android.app.v1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServiceException;
import com.nttuyen.android.base.service.observer.ServiceObserver;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.dto.Course;
import com.vieted.android.app.v1.dto.Lesson;
import com.vieted.android.app.v1.dto.Unit;

import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListLessonAdapter extends VietEdListBaseAdapter<Lesson>{
    public ListLessonAdapter(Context context, Service<Map<String, String>, Unit> service) {
        super(context, null);

        this.service = service;
        if(this.service != null ) {
            try {
                Unit unit = (Unit)service.get();
                this.items = unit.getLessons();
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
                        Unit unit = (Unit)service.get();
                        items = unit.getLessons();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
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

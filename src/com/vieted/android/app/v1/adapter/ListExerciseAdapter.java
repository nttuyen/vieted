package com.vieted.android.app.v1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.nttuyen.android.base.service.Service;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.VietEdState;
import com.vieted.android.app.v1.dto.Exercise;
import com.vieted.android.app.v1.dto.Lesson;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class ListExerciseAdapter extends VietEdListBaseAdapter<Exercise> {

    public ListExerciseAdapter(Context context, Service service) {
        super(context, null);
        VietEdState state = VietEdState.getInstance();
        Lesson lesson = state.getCurrentLesson();
        this.items = lesson.getExercises();
        this.count = this.items.size();
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
        percent.setText("");
        ratingBar.setClickable(false);
        ratingBar.setRating((float)this.items.get(position).getStars());
        return vi;
    }
}

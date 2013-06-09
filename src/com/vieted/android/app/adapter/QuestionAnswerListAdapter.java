package com.vieted.android.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.vieted.android.app.R;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/9/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionAnswerListAdapter extends BaseAdapter {
    private final Context context;
    private final String[] answers;
    private final LayoutInflater inflater;

    public QuestionAnswerListAdapter(Context context, String[] answers) {
        this.context = context;
        this.answers = answers;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return answers.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if(view == null)
            vi = inflater.inflate(R.layout.list_row_answer, null);

        CheckedTextView textView = (CheckedTextView)vi.findViewById(R.id.answerText);
        textView.setText(this.answers[i]);
        return vi;
    }


}

package com.vieted.android.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.vieted.android.app.R;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.utils.VietEdState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private final int questionIndex;
    private final Question question;
    private final List<CheckedTextView> views;
    private OnAnswerCompletedListener listener;
    private final LayoutInflater inflater;

    public QuestionAnswerListAdapter(Context context, int questionIndex) {
        this.context = context;
        this.questionIndex = questionIndex;
        this.question = VietEdState.getInstance().getCurrentQuiz().getQuestions().get(questionIndex);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.views = new ArrayList<CheckedTextView>(this.question.getAnswers().length);
    }

    @Override
    public int getCount() {
        return this.question.getAnswers().length;
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
        this.views.add(textView);
        int indexOf = this.views.indexOf(textView);
        final String[] answers = this.question.getAnswers();
        textView.setText(answers[i]);
        if(this.question.isCompleted()) {
            boolean[] results = this.question.getResults();
            if(results[indexOf]) {
                float[] scores = this.question.getScores();
                if(scores[indexOf] > 0) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tick_green_big, 0, 0, 0);
                } else {
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cross_red_big, 0, 0, 0);
                }
            }
            textView.setEnabled(false);
        }
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckedTextView v = (CheckedTextView)view;
                int index = -1;
                String text = v.getText().toString();
                for(int i = 0; i < answers.length; i++) {
                    if(text.equals(answers[i])) {
                        index = i;
                        break;
                    }
                }
                if(index < 0) return;

                float[] scores = question.getScores();
                boolean[] results = question.getResults();
                results[index] = true;
                question.setResults(results);
                if(scores[index] == 0) {
                    v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cross_red_big, 0, 0, 0);
                } else {
                    v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tick_green_big, 0, 0, 0);
                    boolean hasMoreAnswer = false;
                    for(int i = 0; i < scores.length; i++) {
                        if(i == index || results[i]) continue;
                        if(scores[i] > 0) hasMoreAnswer = true;
                    }
                    if(!hasMoreAnswer) {
                        question.setCompleted(true);
                        if(listener != null) {
                            listener.onAnswerCompleted(questionIndex);
                        }
                        for(CheckedTextView tv : views) {
                            tv.setEnabled(false);
                        }
                    }
                }
                v.setEnabled(false);
            }
        });
        return vi;
    }

    public void setOnAnswerCompletedListener(OnAnswerCompletedListener listener) {
        this.listener = listener;
        if(this.question.isCompleted()) {
            if(this.listener != null) {
                this.listener.onAnswerCompleted(this.questionIndex);
            }
        }
    }

    public static interface OnAnswerCompletedListener {
        public void onAnswerCompleted(int questionIndex);
    }
}

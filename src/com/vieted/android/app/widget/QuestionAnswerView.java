package com.vieted.android.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.androidteam.base.activity.UIContextHelper;
import com.vieted.android.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionAnswerView extends LinearLayout {
    protected LayoutInflater mInflater;
    protected LinearLayout rootView;

    private CheckBox answerCheckbox;
    private LinearLayout answerResult;
    private ImageView answerIconFailure;
    private ImageView answerIconSuccess;
    private TextView answerTextView;

    private boolean checked;

    public QuestionAnswerView(Context context) {
        super(context);
        this.init(context);
    }

    public QuestionAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    private void init(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = (LinearLayout) mInflater.inflate(R.layout.body_lesson_exercise_question_answer, null);

        this.answerCheckbox = (CheckBox)rootView.findViewById(R.id.answerCheckbox);
        this.answerResult = (LinearLayout)rootView.findViewById(R.id.answerResult);
        this.answerIconFailure = (ImageView)rootView.findViewById(R.id.answerIconFailure);
        this.answerIconSuccess = (ImageView)rootView.findViewById(R.id.answerIconSuccess);
        this.answerTextView = (TextView)rootView.findViewById(R.id.answerTextView);

        this.answerResult.setVisibility(View.GONE);
        this.checked = this.answerCheckbox.isChecked();
        this.answerCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                QuestionAnswerView.this.checked = answerCheckbox.isChecked();
            }
        });
        this.answerCheckbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionAnswerView.this.checked = answerCheckbox.isChecked();
            }
        });

        this.addView(rootView);
    }

    public void setAnswerText(String answerText) {
        if(answerText != null) {
            this.answerCheckbox.setText(answerText);
            this.answerTextView.setText(answerText);
        }
    }

    public boolean isChecked() {
        return this.checked;
    }
    public void setChecked(boolean val) {
        this.checked = val;
        this.answerCheckbox.setChecked(val);
    }
    public void setEnable(boolean enable) {
        this.answerCheckbox.setEnabled(enable);
    }

    public void notifyCorrectAnswer(boolean correct) {
        this.answerCheckbox.setVisibility(View.GONE);
        if(this.checked == correct) {
            this.answerIconSuccess.setVisibility(View.VISIBLE);
            this.answerIconFailure.setVisibility(View.GONE);
        } else {
            this.answerIconSuccess.setVisibility(View.GONE);
            this.answerIconFailure.setVisibility(View.VISIBLE);
        }
        this.answerResult.setVisibility(View.VISIBLE);
    }
}

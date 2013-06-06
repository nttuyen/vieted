package com.vieted.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.QuestionPagerAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.widget.QuestionAnswerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener{
    private Button checkButton;
    private Button checkAndNextButton;
    private View root;

    private final QuestionPagerAdapter adapter;
    private final int questionIndex;
    private final Question question;
    private List<QuestionAnswerView> answerViews;

    public static QuestionFragment newInstance(QuestionPagerAdapter adapter, int questionIndex) {
        QuestionFragment fragment = new QuestionFragment(adapter, questionIndex);
        return fragment;
    }

    private QuestionFragment(QuestionPagerAdapter adapter, int questionIndex) {
        this.adapter = adapter;
        this.questionIndex = questionIndex;
        this.question = this.adapter.getQuestionAt(this.questionIndex);
        this.answerViews = new ArrayList<QuestionAnswerView>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.body_lesson_exercise_question, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.questionText);
        messageTextView.setText(question.getQuestionText());

        this.checkAndNextButton = (Button)v.findViewById(R.id.questionCheckandNextButton);
        this.checkAndNextButton.setOnClickListener(this);
        this.checkButton = (Button)v.findViewById(R.id.questionCheckButton);
        this.checkButton.setOnClickListener(this);

        LinearLayout answerLayout = (LinearLayout)v.findViewById(R.id.questionAnswerList);
        answerLayout.removeAllViews();
        for(String ans : question.getAnswers()) {
            QuestionAnswerView answer = new QuestionAnswerView(this.getActivity());
            answer.setAnswerText(ans);
            this.answerViews.add(answer);
            answerLayout.addView(answer);
        }
        return v;
    }

    @Override
    public void onClick(View view) {
        if(adapter.hasMoreQuestion()){
            adapter.allowNext();
        }
    }

    private void score() {
        boolean[] result = question.getResults();

    }
}

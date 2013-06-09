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
import com.vieted.android.app.utils.VietEdState;
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
public class QuestionFragment extends Fragment {
    private View root;

    private int questionIndex;
    private Question question;
    private List<QuestionAnswerView> answerViews;

    public static String ARGUMENT_QUESTION_INDEX = "question_index";

    public static QuestionFragment newInstance(QuestionPagerAdapter adapter, int questionIndex) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_QUESTION_INDEX, questionIndex);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();

        this.questionIndex = args.getInt(ARGUMENT_QUESTION_INDEX);
        this.question = VietEdState.getInstance().getCurrentQuiz().getQuestions().get(questionIndex);
        this.answerViews = new ArrayList<QuestionAnswerView>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(this.root != null) return this.root;

        root = inflater.inflate(R.layout.body_lesson_exercise_question, container, false);
        TextView messageTextView = (TextView) root.findViewById(R.id.questionText);
        messageTextView.setText(question.getQuestionText());

        LinearLayout answerLayout = (LinearLayout) root.findViewById(R.id.questionAnswerList);
        answerLayout.removeAllViews();
        for(String ans : question.getAnswers()) {
            QuestionAnswerView answer = new QuestionAnswerView(this.getActivity());
            answer.setAnswerText(ans);
            this.answerViews.add(answer);
            answerLayout.addView(answer);
        }
        return root;
    }
}

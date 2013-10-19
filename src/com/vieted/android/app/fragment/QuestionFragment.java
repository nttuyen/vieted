package com.vieted.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.QuestionAnswerListAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.utils.VietEdState;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class QuestionFragment extends Fragment {
    private View root;

    private int questionIndex;
    private Question question;
    private OnQuestionCompletedListener listener;

    public static String ARGUMENT_QUESTION_INDEX = "question_index";

    public static QuestionFragment newInstance(int questionIndex) {
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
        outState.clear();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();

        this.questionIndex = args.getInt(ARGUMENT_QUESTION_INDEX);
        this.question = VietEdState.getInstance().getCurrentQuiz().getQuestions().get(questionIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(this.root != null) return this.root;

        root = inflater.inflate(R.layout.body_lesson_exercise_question, container, false);
        TextView messageTextView = (TextView) root.findViewById(R.id.questionText);
        messageTextView.setText(question.getQuestionText());

        final ListView listView = (ListView)root.findViewById(R.id.listViewQuestionAnswer);
        QuestionAnswerListAdapter adapter = new QuestionAnswerListAdapter(this.getActivity(), this.questionIndex);
        listView.setAdapter(adapter);
        adapter.setOnAnswerCompletedListener(new QuestionAnswerListAdapter.OnAnswerCompletedListener() {
            @Override
            public void onAnswerCompleted(int questionIndex) {
                if(listener != null) {
                    listener.onQuestionCompletedListener(question);
                }
            }
        });

        return root;
    }

    public void setOnQuestionCompletedListener(OnQuestionCompletedListener listener) {
        this.listener = listener;
    }
    public interface OnQuestionCompletedListener {
        public void onQuestionCompletedListener(Question question);
    }
}

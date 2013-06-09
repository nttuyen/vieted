package com.vieted.android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.fragment.QuestionFragment;
import com.vieted.android.app.utils.VietEdState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionPagerAdapter extends FragmentPagerAdapter implements Serializable {
    private final List<Question> questions;
    private int currentMaxPage;
    private final Fragment[] fragments;

    public QuestionPagerAdapter(FragmentManager fm) {
        super(fm);
        this.questions = VietEdState.getInstance().getCurrentQuiz().getQuestions();
        int size = questions.size();
        this.fragments = new Fragment[size];
        this.currentMaxPage = 1;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments.length <= position) {
            //TODO: how to do it??
        }
        if(fragments[position] == null) {
            QuestionFragment fragment = QuestionFragment.newInstance(this, position);
            fragments[position] = fragment;
        }
        return fragments[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public int getCount() {
        return this.currentMaxPage;
    }

    public Question getQuestionAt(int index) {
        return this.questions.get(index);
    }

    public boolean hasMoreQuestion() {
        return this.currentMaxPage < this.questions.size();
    }

    public void allowNext() {
        this.currentMaxPage++;
        this.notifyDataSetChanged();
    }
}

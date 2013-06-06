package com.vieted.android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.fragment.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionPagerAdapter extends FragmentPagerAdapter {
    private final List<Question> questions;
    private int currentMaxPage;
    private final List<Fragment> fragments;

    public QuestionPagerAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        this.questions = questions;
        this.fragments = new ArrayList<Fragment>();
        this.currentMaxPage = 1;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments.size() <= position) {
            QuestionFragment fragment = QuestionFragment.newInstance(this, position);
            fragments.add(position, fragment);
        }
        return fragments.get(position);
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

package com.vieted.android.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidteam.base.task.RestAsyncTask;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.QuestionPagerAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.task.VoidTask;
import com.vieted.android.app.utils.DeveloperKey;
import com.vieted.android.app.widget.QuestionAnswerView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/5/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonExerciseFragmentSupportActivity extends VietEdBaseFragmentSupportActivity {
    private ViewPager questionPager;
    private YouTubePlayer player = null;
    private YouTubePlayerSupportFragment youtubeFragment;
    private List<Fragment> questionFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainTask = new VoidTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();
    }

    public void initBodyView() {
        this.setLayoutBody(R.layout.body_lesson_exercise);

        this.youtubeFragment = (YouTubePlayerSupportFragment)this.getSupportFragmentManager().findFragmentById(R.id.youtubePlayerFragment);
        this.youtubeFragment.initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                LessonExerciseFragmentSupportActivity.this.player = youTubePlayer;
                if(!b) {
                    LessonExerciseFragmentSupportActivity.this.player.cueVideo("O52TbIbCEKo");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {}
        });
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(this.youtubeFragment);
        fragmentTransaction.commit();

        this.questionPager = (ViewPager)this.findViewById(R.id.viewpager);

        List<Question> questions = new ArrayList<Question>();
        Question question = new Question();
        question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"});
        question.setQuestionText("Choice the best answer");
        question.setScores(new float[]{0, 0, 0, 1});
        questions.add(question);

        question = new Question();
        question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"});
        question.setQuestionText("Choice the best answer 111");
        question.setScores(new float[]{0, 1, 0, 0});
        questions.add(question);

        question = new Question();
        question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"});
        question.setQuestionText("Choice the best answer 3");
        question.setScores(new float[]{1, 0, 0, 0});
        questions.add(question);

        this.questionPager.setAdapter(new QuestionPagerAdapter(this.getSupportFragmentManager(), questions));
        this.questionPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                FragmentTransaction fragmentTransaction = LessonExerciseFragmentSupportActivity.this.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.show(LessonExerciseFragmentSupportActivity.this.youtubeFragment);
                if(2 == i) {
                    LessonExerciseFragmentSupportActivity.this.player.cueVideo("wKJ9KzGQq0w");
                } else if(1 == i) {
                    LessonExerciseFragmentSupportActivity.this.player.cueVideo("O52TbIbCEKo");
                } else {
                    fragmentTransaction.hide(LessonExerciseFragmentSupportActivity.this.youtubeFragment);
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        this.initBodyView();
    }
}

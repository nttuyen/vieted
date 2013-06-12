package com.vieted.android.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import com.androidteam.base.task.RestAsyncTask;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.QuestionPagerAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.domain.Quiz;
import com.vieted.android.app.task.VoidTask;
import com.vieted.android.app.utils.Const;
import com.vieted.android.app.utils.VietEdState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/5/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonExerciseFragmentSupportActivity extends VietEdBaseFragmentSupportActivity {
    private Quiz quiz;
    private ViewPager questionPager;
    private YouTubePlayer player = null;
    private YouTubePlayerSupportFragment youtubeFragment;
    private QuestionPagerAdapter adapter;

    private Button checkButton;
    private Button checkAndNextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            this.mainTask = new VoidTask();
            this.mainTask.setRestAsyncTaskListener(this);
            this.mainTask.execute();
        } else {
            this.initBodyView();
            this.initQuizYoutubeVideo();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void initBodyView() {
        this.setLayoutBody(R.layout.body_lesson_exercise);

        //Init youtbeFragment :)
        this.youtubeFragment = (YouTubePlayerSupportFragment)this.getSupportFragmentManager().findFragmentById(R.id.youtubePlayerFragment);
        this.youtubeFragment.setRetainInstance(true);
        this.youtubeFragment.initialize(Const.GOOGLE_API_ANDROID_DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                LessonExerciseFragmentSupportActivity.this.player = youTubePlayer;
                VietEdState.getInstance().setCurrentYoutubePlayer(youTubePlayer);
                if(!b) {
                    initQuizYoutubeVideo();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {}
        });
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(this.youtubeFragment);
        fragmentTransaction.commit();

        //Process question pager
        this.questionPager = (ViewPager)this.findViewById(R.id.questionViewPager);

        //TODO: reload quiz here :)
        if(VietEdState.getInstance().getCurrentQuiz() == null) {
            this.quiz = new Quiz();
            VietEdState.getInstance().setCurrentQuiz(this.quiz);
            quiz.setId(100);
            quiz.setDescription("Listen video and choice the best answer in question.");
            quiz.setVideo("wKJ9KzGQq0w");
            //quiz.setVideo(null);

            List<Question> questions = new ArrayList<Question>();
            Question question = new Question();
            question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"});
            question.setQuestionText("Choice the best answer");
            question.setScores(new float[]{0, 0, 0, 1});
            questions.add(question);

            question = new Question();
            question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4", "Answer 5"});
            question.setQuestionText("Choice the best answer 111");
            question.setScores(new float[]{0, 1, 0, 0, 1});
            questions.add(question);

            question = new Question();
            question.setAnswers(new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"});
            question.setQuestionText("Choice the best answer 3");
            question.setScores(new float[]{1, 0, 0, 0});
            questions.add(question);

            quiz.setQuestions(questions);
        } else {
            this.quiz = VietEdState.getInstance().getCurrentQuiz();
        }

        this.adapter = new QuestionPagerAdapter(this.getSupportFragmentManager());
        this.questionPager.setAdapter(this.adapter);
    }

    private void initQuizYoutubeVideo() {
        if(this.player == null) {
            return;
        }

        //Process quiz Video
        FragmentTransaction fragmentTransaction = LessonExerciseFragmentSupportActivity.this.getSupportFragmentManager().beginTransaction();
        if(quiz.getVideo() != null && !quiz.getVideo().isEmpty()) {
            this.player.cueVideo(quiz.getVideo());
            fragmentTransaction.show(LessonExerciseFragmentSupportActivity.this.youtubeFragment);
        } else {
            fragmentTransaction.hide(LessonExerciseFragmentSupportActivity.this.youtubeFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        this.initBodyView();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(adapter.hasMoreQuestion()) {
                adapter.allowNext();
            }
        }
    };
}

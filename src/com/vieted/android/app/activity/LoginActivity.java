package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.task.LoginTask;
import com.vieted.android.app.task.VoidTask;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends VietEdBaseActivity {
    private RestAsyncTask loginTask;
    private EditText editUsername;
    private EditText editPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTextHeader("Login");
        this.setShowButton(BUTTON_BACK, false);
        this.setShowButton(BUTTON_HOME, false);

        this.mainTask = new VoidTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();
    }

    public void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_login);
        this.editUsername = (EditText)this.findViewById(R.id.loginUsername);
        this.editPassword = (EditText)this.findViewById(R.id.loginPassword);
        Button loginButton = (Button)this.findViewById(R.id.loginButtonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                LoginActivity.this.loginTask = new LoginTask(username, password);
                LoginActivity.this.loginTask.setRestAsyncTaskListener(LoginActivity.this);
                LoginActivity.this.loginTask.execute();
            }
        });
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        if(task == this.mainTask) {
            this.initBodyView();
        } else if(task == this.loginTask) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}

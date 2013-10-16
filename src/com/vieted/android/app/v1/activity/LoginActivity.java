package com.vieted.android.app.v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.nttuyen.android.base.service.VoidService;
import com.nttuyen.android.base.utils.UIContextHelper;
import com.vieted.android.app.R;
import com.vieted.android.app.activity.*;
import com.vieted.android.app.v1.VietEdState;
import com.vieted.android.app.v1.dto.LoggedInfo;
import com.vieted.android.app.v1.dto.User;
import com.vieted.android.app.v1.service.CheckHasLoggedInService;
import com.vieted.android.app.v1.service.VietEdService;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends VietEdBaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: We should start service at onStart method
        ServicesHelpers.startService(new CheckHasLoggedInService(), this, new Handler<LoggedInfo>() {
            @Override
            public void handle(LoggedInfo logged) {
                LoginActivity.this.handle(logged);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void handle(LoggedInfo logged) {
        if(logged == null) {
            ServicesHelpers.startService(new VoidService(), LoginActivity.this, new Handler<Void>() {
                @Override
                public void handle(Void value) {
                    LoginActivity.this.handle(value);
                }
            });
        } else {
            this.login(logged.getUsername(), logged.getPassword());
        }
    }

    private void handle(Void value) {
        initBodyView();
    }

    private EditText editUsername;
    private EditText editPassword;
    private void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_login);
        this.editUsername = (EditText) this.findViewById(R.id.loginUsername);
        this.editPassword = (EditText) this.findViewById(R.id.loginPassword);
        Button loginButton = (Button) this.findViewById(R.id.loginButtonLogin);
        Button register = (Button) findViewById(R.id.loginButtonRegister);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                LoginActivity.this.login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("lname", username);
        params.put("pass", password);
        VietEdService service = new VietEdService("/user/login", Request.HTTP_POST, User.class);
        service.init(params);

        ServicesHelpers.startService(service, LoginActivity.this, new Handler<User>() {
            @Override
            public void handle(User user) {
                LoginActivity.this.handle(user);
            }
        });
    }
    private void handle(User user) {
        VietEdState.getInstance().setCurrentUser(user);
        this.go(HomeActivity.class);
    }
}

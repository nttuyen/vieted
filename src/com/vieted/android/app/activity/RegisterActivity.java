package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.task.RegisterTask;
import com.vieted.android.app.task.VoidTask;

@Deprecated
public class RegisterActivity extends VietEdBaseActivity {
	private RestAsyncTask regisTask;
	private EditText txtUsername;
	private EditText txtPassword;
	private EditText txtPasswordRe;
	private EditText txtEmail;
	private Button bttSubmit;
	private CheckBox checkPass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("Register");
		this.setLayoutBody(R.layout.activity_register_account);
		this.setHomeBackAction();
		this.mainTask = new VoidTask();
		this.mainTask.setRestAsyncTaskListener(this);
		this.mainTask.execute();
		this.txtEmail = (EditText) findViewById(R.id.txtRegisterEmail);
		this.txtPassword = (EditText) findViewById(R.id.txtRegisterPass);
		this.txtPasswordRe = (EditText) findViewById(R.id.txtRegisterPassRetype);
		this.txtUsername = (EditText) findViewById(R.id.txtRegisterName);
		this.bttSubmit = (Button) findViewById(R.id.bttRegisterSubmit);
		checkPass = (CheckBox) findViewById(R.id.checkboxPassShowRegis);
		checkPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked == false) {
					txtPassword
							.setTransformationMethod(new PasswordTransformationMethod());
				} else {
					txtPassword.setTransformationMethod(null);
				}

			}
		});

		bttSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = txtUsername.getText().toString().trim();
				String password = txtPassword.getText().toString().trim();
				String passwordRe = txtPasswordRe.getText().toString().trim();
				if(!password.equals(passwordRe)){
					//ActivityUtils.showErrDialog(RegisterActivity.this, "Password and RetypePass are not match!");
					
				}
				else {
					String email = txtEmail.getText().toString().trim();
					RegisterActivity.this.regisTask = new RegisterTask(password,email,username);
					RegisterActivity.this.regisTask.setRestAsyncTaskListener(RegisterActivity.this);
					regisTask.execute();
					
				}
				
				
			}
		});

	
	
	}

	public void initBodyView() {
		
	}

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		if (task == this.regisTask) {
			/*Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);*/
		}

	}

}

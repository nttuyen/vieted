package com.vieted.android.v2.activity;

import android.os.Bundle;
import android.widget.Button;
import com.nttuyen.android.umon.core.ui.UIOnclick;
import com.vieted.android.v2.R;
import com.vieted.android.v2.model.VoidModel;

/**
 * @author nttuyen266@gmail.com
 */
public class HomeActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.actionBar.setTitle("VietEd");

		//Init body view and model
		this.setBodyView(R.layout.activity_body_home);
		this.setModel(new VoidModel());
	}

	@UIOnclick(views = {R.id.buttonMyCourse, R.id.buttonListCourse, R.id.buttonProfile, R.id.buttonPayment})
	public void onButtonClick(Button button) {
		contextHelper.showErrDialog("OnClick", "You have click on button: " + button.getText());
	}
}

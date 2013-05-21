package com.vieted.android.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidteam.base.activity.BaseActivity;
import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class VietEdBaseActivity extends BaseActivity<RestAsyncTask> {
    protected View btnGlobalBack;
    protected View btnGlobalCancel;
    protected View btnGlobalHome;
    protected View btnGlobalVietEdIcon;
    protected TextView txtGlobalHeader;
    protected LinearLayout bodyLayout;

    protected final int BUTTON_BACK = 0;
    protected final int BUTTON_CANCEL = 1;
    protected final int BUTTON_HOME = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        txtGlobalHeader = (TextView) findViewById(R.id.txtGlobalHeader);
        btnGlobalBack = findViewById(R.id.btnGlobalBack);
        btnGlobalVietEdIcon = this.findViewById(R.id.btnGlobalVietEdIcon);
        btnGlobalVietEdIcon.setVisibility(View.GONE);
        btnGlobalCancel = findViewById(R.id.btnGlobalCancel);
        btnGlobalHome = findViewById(R.id.btnGlobalHome);
        bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);

        btnGlobalBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnGlobalHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(VietEdBaseActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    protected void setShowButton(int button, boolean show) {
        View btn = null;
        switch (button) {
            case BUTTON_BACK:
                btn = btnGlobalBack;
                break;
            case BUTTON_CANCEL:
                btn = btnGlobalCancel;
                break;
            case BUTTON_HOME:
                btn = btnGlobalHome;
                break;
        }
        if(btn != null) {
            btn.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        if(button == BUTTON_BACK) {
            this.btnGlobalVietEdIcon.setVisibility(show ? View.GONE : View.VISIBLE);
            View view = show ? this.btnGlobalBack : this.btnGlobalVietEdIcon;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.txtGlobalHeader.getLayoutParams();
            params.addRule(RelativeLayout.RIGHT_OF, view.getId());
            this.txtGlobalHeader.setLayoutParams(params);
        }
    }

    protected void setTextHeader(String txtHeader) {
        if (txtHeader != null)
            txtGlobalHeader.setText(txtHeader);
    }

    protected void setLayoutBody(int idBody) {
        bodyLayout.removeAllViews();
        bodyLayout.addView(getLayoutInflater().inflate(idBody, null));
    }
}

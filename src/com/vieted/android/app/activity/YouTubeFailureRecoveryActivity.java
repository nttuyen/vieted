/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vieted.android.app.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.vieted.android.app.R;
import com.vieted.android.app.utils.Const;

/**
 * An abstract activity which deals with recovering from errors which may occur during API
 * initialization, but can be corrected through user action.
 */
public abstract class YouTubeFailureRecoveryActivity extends YouTubeBaseActivity implements
    YouTubePlayer.OnInitializedListener {
    protected static String TAG = "YOUTUBEPLAYER";

  private static final int RECOVERY_DIALOG_REQUEST = 1;

  @Override
  public void onInitializationFailure(YouTubePlayer.Provider provider,
      YouTubeInitializationResult errorReason) {
      Log.e(TAG, "Youtube player failure");
      Log.e(TAG, errorReason.name());
      Log.e(TAG, errorReason.toString());

    if (errorReason.isUserRecoverableError()) {
        Log.e(TAG, "User Recoverable Error");
      errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
    } else {
        Log.e(TAG, errorReason.toString());
      String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
      Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RECOVERY_DIALOG_REQUEST) {
      getYouTubePlayerProvider().initialize(Const.GOOGLE_API_ANDROID_DEVELOPER_KEY, this);
    }
  }

  protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();

}

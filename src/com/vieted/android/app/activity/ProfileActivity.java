package com.vieted.android.app.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidteam.base.task.RestAsyncTask;
import com.nttuyen.android.base.widget.ActionBar;
import com.vieted.android.app.R;
import com.vieted.android.app.task.ProfileTask;

public class ProfileActivity extends VietEdBaseActivity{
	private final int CAMERA_PICTURE = 1;
	private final int GALLERY_PICTURE = 2;
	private ImageView userPictureImageView;
	private Intent pictureActionIntent = null;
	private ImageView image2;
	Button btt;
	Bitmap bitmap;
	static Bitmap temp;
	String path;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 this.setTextHeader("VietEd");
	        this.actionBar.removeActionAt(0);
	        this.actionBar.addAction(new ActionBar.Action() {
	            @Override
	            public int getDrawable() {
	                return R.drawable.vieted_icon;
	            }

	            @Override
	            public void performAction(View view) {

	            }
	        });

	        this.mainTask = new ProfileTask();
	        this.mainTask.setRestAsyncTaskListener(this);
	        this.mainTask.execute();
	}
	public void initBoby(){
		
		this.setLayoutBody(R.layout.activity_profile);
		userPictureImageView = (ImageView) findViewById(R.id.imageView1);
		int length = userPictureImageView.getHeight();
		userPictureImageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startDialog();
			}

		});
		
				userPictureImageView
				.setOnLongClickListener(new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						System.out.println(path);
						return false;
					}
				});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == GALLERY_PICTURE) {
				Uri uri = data.getData();
				if (uri != null) {
					// User had pick an image.
					Cursor cursor = getContentResolver()
							.query(uri,
									new String[] { android.provider.MediaStore.Images.ImageColumns.DATA },
									null, null, null);
					cursor.moveToFirst();
					// Link to the image
					final String imageFilePath = cursor.getString(0);
					System.out.println(imageFilePath);
					path= imageFilePath;
					File photos = new File(imageFilePath);
					Bitmap b = decodeFile(photos);
					b = Bitmap.createScaledBitmap(b, 150, 150, true);
					temp= b;
					userPictureImageView.setImageBitmap(b);
					cursor.close();
				} else {
					Toast toast = Toast.makeText(this, "No Image is selected.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else if (requestCode == CAMERA_PICTURE) {
				if (data.getExtras() != null) {
					// here is the image from camera
					bitmap = (Bitmap) data.getExtras().get("data");
					temp= bitmap;
					userPictureImageView.setImageBitmap(bitmap);
				}

			}

		} catch (Exception e) {
			// TODO: execute exception
		}
	}

	private Bitmap decodeFile(File f) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 70;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale++;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	private void startDialog() {
		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		myAlertDialog.setTitle("Upload Pictures Option");
		myAlertDialog.setMessage("How do you want to set your picture?");

		myAlertDialog.setPositiveButton("Gallery",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						pictureActionIntent = new Intent(
								Intent.ACTION_GET_CONTENT, null);
						pictureActionIntent.setType("image/*");
						pictureActionIntent.putExtra("return-data", true);
						startActivityForResult(pictureActionIntent,
								GALLERY_PICTURE);
					}
				});

		myAlertDialog.setNegativeButton("Camera",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						pictureActionIntent = new Intent(
								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(pictureActionIntent,
								CAMERA_PICTURE);
					}
				});
		myAlertDialog.show();
	}
		
	

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		 if(task == this.mainTask) {
	            this.initBoby();
	        }
		
	}
	

}

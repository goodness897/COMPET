package com.mu.compet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class NewWriteActivity extends AppCompatActivity {

    File mSavedFile, mContentFile;
    private static final int RC_GET_IMAGE = 1;
    private static final int RC_CAMERA = 2;
    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    ImageView[] imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_write);
        initToolBar(getString(R.string.activity_new_write));

        firstImage = (ImageView) findViewById(R.id.image_camera_first);
        secondImage = (ImageView) findViewById(R.id.image_camera_second);
        thirdImage = (ImageView) findViewById(R.id.image_camera_third);

        imageViews = new ImageView[]{firstImage, secondImage, thirdImage};

        if (savedInstanceState != null) {
            String path = savedInstanceState.getString("savedfile");
            if (!TextUtils.isEmpty(path)) {
                mSavedFile = new File(path);
            }
            path = savedInstanceState.getString("contentfile");
            if (!TextUtils.isEmpty(path)) {
                mContentFile = new File(path);
                int dstWidth = 300;
                int dstHeight = 300;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bmImg = BitmapFactory.decodeFile(mContentFile.getAbsolutePath(), options);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap resized = Bitmap.createScaledBitmap(bmImg, dstWidth, dstHeight, true);
                Bitmap rotateBitmap = Bitmap.createBitmap(resized, 0, 0, resized.getWidth(), resized.getHeight(), matrix, true);

                for(int i = 0; i < imageViews.length; i++) {
                    if(imageViews[i].getDrawable() == null) {
                        imageViews[i].setImageBitmap(rotateBitmap);
                        break;
                    }
                }
            }
        }


    }

    private void initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addPictureClicked(View view) {
        // 갤러리를 통한 이미지 가져오기
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RC_GET_IMAGE);

    }

    public void addCameraClicked(View view) {
        // 카메라를 통한 이미지 가져오기
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = getSaveFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, RC_CAMERA);

    }


    public Uri getSaveFile() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        ), "my_image");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        mSavedFile = new File(dir, "my_picture_" + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(mSavedFile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_new_write_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_complete) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mSavedFile != null) {
            outState.putString("savedfile", mSavedFile.getAbsolutePath());
        }
        if (mContentFile != null) {
            outState.putString("contentfile", mContentFile.getAbsolutePath());
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {

            // 카메라를 통한 이미지 가져오기에 대한 결과
            case RC_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    mContentFile = mSavedFile;
                    Log.i("Image", "path : " + mContentFile.getAbsolutePath());
                }

                break;
            // 앨범에서 이미지 가져오기에 대한 결과
            case RC_GET_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri fileUri = intent.getData();
                    Cursor c = getContentResolver().query(fileUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    int dstWidth = 300;
                    int dstHeight = 300;
                    if (c.moveToNext()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                        String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                        Log.i("Image", "path : " + path);
                        Bitmap bmImg = BitmapFactory.decodeFile(path, options);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        Bitmap resized = Bitmap.createScaledBitmap(bmImg, dstWidth, dstHeight, true);
                        Bitmap rotateBitmap = Bitmap.createBitmap(resized, 0, 0, resized.getWidth(), resized.getHeight(), matrix, true);

                        for(int i = 0; i < imageViews.length; i++) {
                            if(imageViews[i].getDrawable() == null) {
                                imageViews[i].setImageBitmap(rotateBitmap);
                                break;
                            }
                        }
                    }
                    break;
                }
        }
    }
}

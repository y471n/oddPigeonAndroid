package com.example.mac.oddpigeon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class CreateCircleActivity extends ActionBarActivity {

    private Circle circle;
    private static final short SELECT_PHOTO = 101;
    public static String CIRCLE_TYPE = "com.example.mac.oddpigeon.CIRCLE_TYPE";
    private String circleType;
    private EditText circleName;
    private LinearLayout mRulesLayout;
    private Button mAddRulesButton;
    private ImageView mCircleCoverImageView;
    private Button mUploadPhotoButton;
    private String[] rulesHints = {"Cool enough....   :D ", "Loves Photography", "Is an user of PhoneCurry", "Has an Android Phone"};
    private int rulesHintsIndex = 0;
    private static final int SELECT_PICTURE = 1;
    private Button mCreateCircleButton;
    private JSONArray circleRules;
    Bitmap bmp;
    ParseFile pFile = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        circle = new Circle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);

        // For Adding New Rules
        mRulesLayout = (LinearLayout) findViewById(R.id.circle_rules_layout);
        addRules();

        // For picking a photo
        mCircleCoverImageView = (ImageView) findViewById(R.id.circle_cover_image_view);
        mUploadPhotoButton = (Button) findViewById(R.id.upload_circle_photo_button);
        mUploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });

        // Getting circle type
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            circleType = extras.getString(CIRCLE_TYPE);
        }
//        Toast.makeText(CreateCircleActivity.this, circleType, Toast.LENGTH_LONG).show();

        // Creating the Circle
        circleName = (EditText) findViewById(R.id.circle_name_edit_text);
        mCreateCircleButton = (Button) findViewById(R.id.create_circle_button);
        mCreateCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleRules = new JSONArray();
                Log.d("createCircle", circleType);
                circle.setCircleType(circleType);
                Log.d("createCircle", circleName.getText().toString());
                circle.setCircleName(circleName.getText().toString());
                int rulesEditTextIndex = 1;
                while (rulesEditTextIndex < mRulesLayout.getChildCount()) {
                    EditText rulesEditText = (EditText) mRulesLayout.getChildAt(rulesEditTextIndex);
                    try {
                        circleRules.put(rulesEditTextIndex - 1, rulesEditText.getText().toString());
                        Log.d("createCircle", circleRules.get(rulesEditTextIndex - 1).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    rulesEditTextIndex++;
                }
                circle.setCircleRules(circleRules);
                mRulesLayout.getChildCount();

                // Adding Circle Author
                circle.setAuthor(ParseUser.getCurrentUser());

                // Adding Circle Photo
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                pFile = new ParseFile("circlePhoto.jpg", stream.toByteArray());
                circle.setPhotoFile(pFile);

                circle.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(CreateCircleActivity.this, "Saved: " , Toast.LENGTH_LONG).show();
                            setResult(Activity.RESULT_OK);
//                            finish();
                        } else {
                            Toast.makeText(CreateCircleActivity.this, "Error saving: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });

    }

    private void addRules() {
        mAddRulesButton = (Button) findViewById(R.id.add_rules_button);
        mAddRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRulesLayout.addView(createNewTextView());
            }
        });
    }

    private EditText createNewTextView() {

        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final EditText mRulesEditText = new EditText(this);
        mRulesEditText.setLayoutParams(layoutParams);
        mRulesEditText.setHint(rulesHints[rulesHintsIndex]);
        rulesHintsIndex++;
        if (rulesHintsIndex > 3) {
            mAddRulesButton.setEnabled(false);
        }

        return mRulesEditText;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
//                        final Uri imageUri = imageReturnedIntent.getData();
//                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        bmp = MediaStore.Images.Media.getBitmap( this.getContentResolver(), imageReturnedIntent.getData());
//                        if (imageStream != null ) {
//                            ParseFile file = new ParseFile("circlePhoto.jpg", selectedImage);
//                        }
                        mCircleCoverImageView.setImageBitmap(bmp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    public void pickPhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


}

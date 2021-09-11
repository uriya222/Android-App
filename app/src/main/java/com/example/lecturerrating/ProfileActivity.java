package com.example.lecturerrating;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements IPickResult {
    private ImageView edit_icon, profile_picture;
    private PersonalProfile p;
    private String photo_path;
    private Utils base = Utils.getInstance(this);
    private Button myRates, edit_button;
    private TextView txt_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        Intent intent = getIntent();
        p = base.getProfile();
        photo_path = p.getPhoto_path();
        if (photo_path.equals("empty")) {
            profile_picture.setImageResource(R.drawable.ic_action_empty_photo);
        } else {
            loadImageFromStorage(photo_path);
        }

        ActivityResultLauncher<String> a = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                try {
                    profile_picture.setImageURI(result);
                    BitmapDrawable drawable = (BitmapDrawable) profile_picture.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    photo_path = saveToInternalStorage(bitmap);
                    p.setPhoto_path(photo_path);
                    base.UpdateProfile(p);
                } catch (Exception e) {
                    //Todo check if result is null and if so raise a dialog alert "are you sure?"
                    Toast.makeText(ProfileActivity.this, "did not chose photo", Toast.LENGTH_SHORT).show();
                }

            }
        });


        edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //a.launch("image/*");
                // learn to use in here - https://github.com/jrvansuita/PickImage
                PickImageDialog.build(new PickSetup()).show(ProfileActivity.this);
            }
        });


        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        //TODO: do what you have to...
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        //TODO: do what you have to if user clicked cancel
                    }
                }).show(getSupportFragmentManager());
    }

    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            getImageView().setImageBitmap(r.getBitmap());

            //Image path
            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(ProfileActivity.this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {

        try {

            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            profile_picture.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private void initViews() {
        edit_icon = findViewById(R.id.edit_icon_profile);
        profile_picture = findViewById(R.id.profile_picture);
        myRates = findViewById(R.id.my_rates_profile);
        edit_button = findViewById(R.id.btn_editProfile_profile);
        txt_edit = findViewById(R.id.txt_editPhoto_profile);
    }

}
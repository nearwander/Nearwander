package com.nearwander.application.nearwander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nearwander.application.nearwander.tabbed.HomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupProfile extends AppCompatActivity {

    private EditText input;
    private Button check;
    private ImageView mImageView;
    private CircleImageView circleImageView;
    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    /*private MenuView.ItemView itemViewCamera, itemViewGalery;
    private MenuItem menuItemCamera, menuItemGalery;*/

    private static final int GALLERY_INTENT = 2;
    private static final int CAMERA_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        input = (EditText) findViewById(R.id.user_profile_name);
        input = (EditText) findViewById(R.id.your_id);
        input = (EditText) findViewById(R.id.email);
        input = (EditText) findViewById(R.id.phone);
        input = (EditText) findViewById(R.id.status);
        input = (EditText) findViewById(R.id.test);
        check = (Button) findViewById(R.id.button2);
        /*itemViewCamera = (MenuView.ItemView) findViewById(R.id.take_from_camera);
        itemViewGalery = (MenuView.ItemView) findViewById(R.id.choose_from_galery);

        menuItemCamera = (MenuItem) findViewById(R.id.take_from_camera);
        menuItemGalery = (MenuItem) findViewById(R.id.choose_from_galery);*/



        mProgressDialog = new ProgressDialog(this);

        mImageView = (ImageView) findViewById(R.id.user_profile_photo);
        circleImageView = (CircleImageView) findViewById(R.id.profile_image);

        mStorage = FirebaseStorage.getInstance().getReference();

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(SetupProfile.this, mImageView);
                popupMenu.getMenuInflater().inflate(R.menu.popup_set_profile, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.choose_from_galery:
                                Intent i = new Intent(Intent.ACTION_PICK);
                                i.setType("image/*");

                                startActivityForResult(i, GALLERY_INTENT);
                                return true;

                            case R.id.take_from_camera:
                                Intent a = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(a, CAMERA_REQUEST_CODE);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View arg0){
                if(input.getText().toString().equals("")){
                    Toast.makeText(SetupProfile.this, "Please Insert", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SetupProfile.this, HomePageActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(SetupProfile.this,"SAVED!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setMessage("Uploading Image ...");
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(SetupProfile.this).load(downloadUri).fit().noFade().into(circleImageView);

                    mProgressDialog.dismiss();

                    Toast.makeText(SetupProfile.this, "Upload Done.", Toast.LENGTH_LONG).show();

                }
            });
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode== RESULT_OK){

            mProgressDialog.setMessage("Uploading Image ...");
            mProgressDialog.show();

            Uri uriCam = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uriCam.getLastPathSegment());

            filepath.putFile(uriCam).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();
                    Toast.makeText(SetupProfile.this, "Upload Done.", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

}
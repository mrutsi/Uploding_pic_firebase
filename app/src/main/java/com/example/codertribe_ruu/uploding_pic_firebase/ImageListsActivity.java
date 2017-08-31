package com.example.codertribe_ruu.uploding_pic_firebase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListsActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView iv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_lists);



        imgList=new ArrayList<>();
        iv=(ListView)findViewById(R.id.listViewImage);

        //show progress dialog during list image loading
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("pease wait loading list image");
        progressDialog.show();

        mDatabaseRef= FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //fetch image from firebase
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //imageupload class require default contractor
                    ImageUpload img=snapshot.getValue(ImageUpload.class);
                    imgList.add(img);
                }
                //init adapter
                adapter=new ImageListAdapter(ImageListsActivity.this,R.layout.image_item,imgList);
                iv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}

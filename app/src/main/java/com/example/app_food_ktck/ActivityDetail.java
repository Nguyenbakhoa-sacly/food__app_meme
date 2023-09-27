package com.example.App_Food_KTCK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetail extends AppCompatActivity {
    TextView txtNameMovie,txtSumary;
    ImageButton imgmp4;
    VideoView vdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtNameMovie =(TextView) findViewById(R.id.txt_detailNameMovie);
        txtSumary =(TextView) findViewById(R.id.txt_Summary);
        imgmp4 =(ImageButton) findViewById(R.id.imgmp4);
        vdView = (VideoView) findViewById(R.id.vd_ViDeoMP4);

        Intent intent =getIntent();
        txtNameMovie.setText(intent.getStringExtra("key1"));
        txtSumary.setText(intent.getStringExtra("key2"));

        imgmp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgmp4.setVisibility(View.INVISIBLE);

                switch (txtNameMovie.getText().toString()) {
                    case "Chiến Tranh":
                    case "Kinh Dị":
                    case "Hài Kịch":
                    case "Hành Động":
                    case "Cheer Up":
                    case "Tình Cảm":
                    case "Viễn Tưởng":
                    case "Khoa Học":
                    case "Hoạt Hình" :{
                        vdView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+R.raw.chientranh));
                        break;
                    }
                }
                vdView.start();
                MediaController mediaController = new MediaController(ActivityDetail.this);
                mediaController.setMediaPlayer(vdView);
                vdView.setMediaController(mediaController);
            }
        });
    }
}
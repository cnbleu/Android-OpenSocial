package com.cnbleu.social.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String SD_ROOT =
            android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

    private static final String IMAGE_JPG = SD_ROOT + File.separator + "emojithumb.jpg";
    private static final String IMAGE_GIF = SD_ROOT + File.separator + "emoji.gif";

    private static final String
            IMAGE_URL =
            "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20140728%2Fsl_88132874265.jpg&thumburl=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D4144427137%2C2176645001%26fm%3D21%26gp%3D0.jpg";

    private static final String
            IMAGE_EMOJI_URL =
            "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fww1.sinaimg.cn%2Flarge%2F85cccab3gw1etdkz2jm7vg205g05agqb.jpg&thumburl=http%3A%2F%2Fimg5.imgtn.bdimg.com%2Fit%2Fu%3D3078332440%2C3039432953%26fm%3D21%26gp%3D0.gif";


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;

        setContentView(R.layout.activity_main);

        findViewById(R.id.demo_share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}

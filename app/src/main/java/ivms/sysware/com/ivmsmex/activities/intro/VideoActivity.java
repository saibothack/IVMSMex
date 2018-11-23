package ivms.sysware.com.ivmsmex.activities.intro;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.activities.login.LoginActivity;
import ivms.sysware.com.ivmsmex.activities.rastreo.RastreoActivity;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.bgVideoView)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        setVideo();

        new Handler().postDelayed(new Runnable(){
            public void run(){
                redirect(LoginActivity.class);
            }
        }, 5000);
    }

    private void setVideo() {
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.street_view);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }
}

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
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.activities.rastreo.RastreoActivity;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class VideoActivity extends BaseActivity {
    private SharedPreferenceUtil sharedPreferences;

    @BindView(R.id.bgVideoView)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initComponents();
        setRedirect();
    }

    @Override
    public void initComponents() {
        sharedPreferences = getMyApplication().getSharedPreferenceUtil();
        setVideo();
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

    private void setRedirect() {
        if (sharedPreferences.getBoolean(SharedPreferenceUtil.Key.bLogin)) {
            redirect(NavigationActivity.class, true);
        } else {
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    redirect(LoginActivity.class, true);
                }
            }, 5000);
        }
    }
}

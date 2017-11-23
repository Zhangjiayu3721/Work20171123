package zjy.com.work20171123;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */
public class OtherActivity extends AppCompatActivity  {


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.delete)
    Button delete;
    private static final String TAG = OtherActivity.class.getSimpleName();
    private int max;
    private DownloadUtil mDownloadUtil;
    PlayerView play;
    private String urlString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        urlString = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";
        initPlayer();
        String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local2";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {

            @Override
            public void downloadStart(int fileSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                progressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "Compelete::" + downloadedSize);
                progressBar.setProgress(downloadedSize);
                textView.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
                // TODO Auto-generated method stub
                Log.w(TAG, "ENd");
            }
        });

    }

    private void initPlayer() {
        //初始化播放器
        play = new PlayerView(this)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(urlString);
        play.startPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        play.stopPlay();
    }

    @OnClick({R.id.start, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                mDownloadUtil.start();
                break;
            case R.id.delete:
                mDownloadUtil.pause();
                break;
        }
    }
}

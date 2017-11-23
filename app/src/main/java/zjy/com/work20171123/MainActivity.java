package zjy.com.work20171123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjy.com.work20171123.adapter.MyAdapter;
import zjy.com.work20171123.bean.HomeBean;
import zjy.com.work20171123.presenter.IPresenter;
import zjy.com.work20171123.view.IView;

public class MainActivity extends AppCompatActivity implements IView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.pb)
    ProgressBar pb;
    private GridLayoutManager manager;
    private MyAdapter adapter;
    int type = 1;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new IPresenter(this);
        presenter.getUrl(type);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                type++;
                presenter.getUrl(type);
                recyclerview.setAdapter(adapter);
                srl.setRefreshing(false);
            }
        });
    }

    @Override
    public void getUrl(final List<HomeBean.SongListBean> list) {
        manager = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(manager);
        adapter = new MyAdapter(list, this);
        recyclerview.setAdapter(adapter);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int last = manager.findLastCompletelyVisibleItemPosition();
                if (last == list.size() - 1) {
//                    type++;
//                    presenter.getUrl(type);
                }
            }
        });
        adapter.setAdapterItemClickListener(new MyAdapter.OnAdapterItemClickListener() {
            @Override
            public void click(View view, int position) {
                startActivity(new Intent(MainActivity.this,OtherActivity.class));
            }
        });
    }
}

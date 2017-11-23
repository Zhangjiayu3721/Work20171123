package zjy.com.work20171123.model;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zjy.com.work20171123.LoggingInterceptor;
import zjy.com.work20171123.bean.HomeBean;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */

public class Model implements IModel{

    public onClickFinish onclickfinish;
    public interface onClickFinish{
        void finish(List<HomeBean.SongListBean> list);
    }

    public void setOnclickfinish(onClickFinish onclickfinish) {
        this.onclickfinish = onclickfinish;
    }

    @Override
    public void getDate(int type) {
        HashMap<String,String> map = new HashMap<>();
        map.put("type",type+"");
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<HomeBean> getbean = apiService.getbean("ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0&qq-pf-to=pcqq.group",map);
        getbean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        List<HomeBean.SongListBean> goods_list = homeBean.getSong_list();
                        onclickfinish.finish(goods_list);
                    }
                });
    }
}

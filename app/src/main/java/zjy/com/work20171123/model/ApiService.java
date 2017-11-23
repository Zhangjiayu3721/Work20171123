package zjy.com.work20171123.model;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import zjy.com.work20171123.bean.HomeBean;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */

public interface ApiService {
    public static final String URL = "http://tingapi.ting.baidu.com/v1/restserver/";

    @POST
    Observable<HomeBean> getbean(@Url String url, @QueryMap Map<String,String> map);
}
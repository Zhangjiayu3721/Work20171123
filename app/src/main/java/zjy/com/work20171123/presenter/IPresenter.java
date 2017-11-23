package zjy.com.work20171123.presenter;

import java.util.List;

import zjy.com.work20171123.bean.HomeBean;
import zjy.com.work20171123.model.Model;
import zjy.com.work20171123.view.IView;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */

public class IPresenter implements Model.onClickFinish{
    Model model;
    IView view;

    public IPresenter(IView view) {
        this.view = view;
        model = new Model();
        model.setOnclickfinish(this);
    }

    public void getUrl(int type){
        model.getDate(type);
    }

    @Override
    public void finish(List<HomeBean.SongListBean> list) {
        view.getUrl(list);
    }
}

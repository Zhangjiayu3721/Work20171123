package zjy.com.work20171123;

import android.app.Application;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.UserDao;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */

public class MyApp extends Application{
    public static UserDao userDao;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}

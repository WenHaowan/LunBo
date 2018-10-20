package as.bwei.com.lunbo;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import as.bwei.com.lunbo.adapter.pageAdapter;
import as.bwei.com.lunbo.bean.ShowBean;
import as.bwei.com.lunbo.presenter.ShowPresenter;
import as.bwei.com.lunbo.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView{

    private List<ShowBean.DataBean> list = new ArrayList();
    private ViewPager view_page;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                int i = view_page.getCurrentItem();
                i++;
                view_page.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_page = (ViewPager) findViewById(R.id.view_page);

        ShowPresenter presenter = new ShowPresenter(MainActivity.this);
        presenter.show();
    }

    @Override
    public void failure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(msg, ShowBean.class);
                List<ShowBean.DataBean> data = showBean.getData();
                pageAdapter adapter = new pageAdapter(MainActivity.this,data);
                view_page.setAdapter(adapter);
                handler.sendEmptyMessageDelayed(0, 2000);
                view_page.setCurrentItem(list.size()*1000);
            }
        });
    }
}

package as.bwei.com.lunbo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import as.bwei.com.lunbo.R;
import as.bwei.com.lunbo.bean.ShowBean;

/**
 * Created by HP on 2018/10/19.
 */

public class pageAdapter extends PagerAdapter{
    private Context context;
    private List<ShowBean.DataBean> list;

    public pageAdapter(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        ImageView image_view = (ImageView) view.findViewById(R.id.image_view);
        Glide.with(context).load(list.get(position%list.size()).getIcon().toString()).into(image_view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

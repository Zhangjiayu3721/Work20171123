package zjy.com.work20171123.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import zjy.com.work20171123.R;
import zjy.com.work20171123.bean.HomeBean;

/**
 * Created by ZhangJiaYu on 2017/11/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<HomeBean.SongListBean> list = new ArrayList<>();
    Context context;

    public MyAdapter(List<HomeBean.SongListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    OnAdapterItemClickListener adapterItemClickListener;
    public interface OnAdapterItemClickListener{
        void click(View view,int position);
    }

    public void setAdapterItemClickListener(OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = null;
        View view = View.inflate(context,R.layout.item,null);
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
        holder.img.setImageURI(Uri.parse(list.get(position).getPic_small()));
        holder.tv.setText(list.get(position).getTitle());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterItemClickListener.click(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView img;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}

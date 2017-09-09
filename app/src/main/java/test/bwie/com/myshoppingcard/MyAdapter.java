package test.bwie.com.myshoppingcard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 黑白 on 2017/9/9.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MyJavaBean.DataBean> data;
    private final MainActivity mainActivity;

    public MyAdapter(Context context, List<MyJavaBean.DataBean> data) {
        this.context = context;
        this.data = data;
        mainActivity = (MainActivity) context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder) {
            final MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.title.setText(data.get(position).getContent());
            Glide.with(context).load(data.get(position).getImage_url()).into(viewHolder.imageView);
            viewHolder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    data.get(position).box = b;

//                    mainActivity.setCheckBos();
                    if (b) {
//                        mainActivity.setCheckBos(isCheck());
                        setonCheck.setCheckstate(isCheck());
                    } else {
//                        mainActivity.setCheckBos(false);
                        setonCheck.setCheckstate(false);
                    }

                }
            });

            viewHolder.box.setChecked(data.get(position).box);
        }
    }

    private boolean isCheck() {
        for (int i = 0; i < data.size(); i++) {

            if (!data.get(i).box) {

                return false;
            }
        }
        return true;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox box;
        ImageView imageView;
        TextView title;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            box = itemView.findViewById(R.id.itemCheckbox);
            imageView = itemView.findViewById(R.id.itemImage);
            title = itemView.findViewById(R.id.itemTextView);
            price = itemView.findViewById(R.id.itemPrice);
        }
    }

    /*public void setCheckBos() {
        for (int i = 0; i < data.size(); i++) {
            Log.d("zzz", "开始查询");
            if (!data.get(i).box) {
                Log.d("zzz", "状态改变");
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.setCheckBos();

            }
        }


    }*/

    private SetonCheck setonCheck;

    public interface SetonCheck {
        void setCheckstate(boolean isNet);
    }

    public void jiekou(SetonCheck setonCheck) {

        this.setonCheck = setonCheck;
    }
}

package test.bwie.com.myshoppingcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.LoaderFactory;
import org.xutils.x;

import java.util.List;

import test.bwie.com.myshoppingcard.MyAdapter.SetonCheck;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.SetonCheck {

    private RecyclerView mRecycler;
    private CheckBox mCheckBox;
    /**
     * 价格
     */
    private TextView mPrice;
    private List<MyJavaBean.DataBean> data;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);
        mPrice = (TextView) findViewById(R.id.price);
        initJavaBean();

    }

    private void initJavaBean() {
        String url = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";
        x.http().get(new RequestParams(url), new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.d("zzz", result);
                if (result != null) {
                    Gson gson = new Gson();
                    MyJavaBean myJavaBean = gson.fromJson(result, MyJavaBean.class);
                    Log.d("zzz", myJavaBean.toString());
                    data = myJavaBean.getData();
                    Log.d("zzz", data.toString() + data.size());
                    myAdapter = new MyAdapter(MainActivity.this, data);
                    mRecycler.setAdapter(myAdapter);
                    myAdapter.jiekou(MainActivity.this);
                    /*myAdapter.jiekou(new SetonCheck() {
                        @Override
                        public void setCheckstate(boolean isNet) {
                            mCheckBox.setChecked(isNet);
                        }
                    });*/

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBox:
                initCheck();
                break;
        }
    }

    private void initCheck() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).box = mCheckBox.isChecked();
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCheckstate(boolean isNet) {
        mCheckBox.setChecked(isNet);
    }





    /*public void setCheckBos(boolean isNet) {
        mCheckBox.setChecked(isNet);
    }*/


}

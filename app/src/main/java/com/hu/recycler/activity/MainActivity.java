package com.hu.recycler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hu.recycler.R;
import com.hu.recycler.ViewItemState;
import com.hu.recyclerlib.adapter.AbsBaseAdapter;
import com.hu.recyclerlib.adapter.factory.AbsViewHolderFactory;
import com.hu.recycler.bean.DemoBean;
import com.hu.recycler.bean.DemoBean2;
import com.hu.recycler.viewholder.DemoViewHolder;
import com.hu.recycler.viewholder.DemoViewHolder2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = "MyActivity";
    RecyclerView rvListView;
    int layoutId;
    String viewHolderName;
    List<Object> myBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListView = (RecyclerView) findViewById(R.id.rvListView);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        for (int i = 0; i < 100; i++) {
            myBeans.add(new DemoBean("title:" + i, "second:" + i, "btn:" + i));
            myBeans.add(new DemoBean2("title:" + i, "second:" + i, "btn:" + i));
        }

        rvListView.setAdapter(new AbsBaseAdapter<Object>(myBeans, new SimpleFactory()) {
            @Override
            public void bindViewHolders(RecyclerView.ViewHolder viewHolder, Object bean, int position) {
                System.out.println(viewHolder.getClass().getName());
                if (viewHolder instanceof DemoViewHolder) {
                    DemoViewHolder demoViewHolder = (DemoViewHolder) viewHolder;
                    demoViewHolder.textView1.setText(((DemoBean) bean).text1);
                    demoViewHolder.textView2.setText(((DemoBean) bean).text2);
                    demoViewHolder.button1.setText(((DemoBean) bean).btnText);
                } else if (viewHolder instanceof DemoViewHolder2) {
                    DemoViewHolder2 demoViewHolder2 = (DemoViewHolder2) viewHolder;
                    demoViewHolder2.textView1.setText(((DemoBean2) bean).text1);
                    demoViewHolder2.textView2.setText(((DemoBean2) bean).text2);
                    demoViewHolder2.button1.setText(((DemoBean2) bean).btnText);
                }
            }
        });
    }

    private static class SimpleFactory extends AbsViewHolderFactory {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case ViewItemState.NORMAL_ITEM_TYPE:
                    return R.layout.item_aaa;
                case ViewItemState.FOOTER_TYPE:
                    return R.layout.item_bbb;
            }
            return R.layout.item_aaa;
        }

        @Override
        public Class<? extends RecyclerView.ViewHolder> getViewHolder(int itemType) {
            switch (itemType) {
                case ViewItemState.NORMAL_ITEM_TYPE:
                    return DemoViewHolder.class;
                case ViewItemState.FOOTER_TYPE:
                    return DemoViewHolder2.class;
            }
            return DemoViewHolder.class;
        }

    }

}

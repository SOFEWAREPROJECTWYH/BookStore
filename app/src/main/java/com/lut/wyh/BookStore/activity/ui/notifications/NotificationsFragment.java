package com.lut.wyh.BookStore.activity.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.search.BCallBack;
import com.lut.wyh.BookStore.search.ICallBack;
import com.lut.wyh.BookStore.search.SearchListView;
import com.lut.wyh.BookStore.sqlite.RecordSQLiteOpenHelper;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    // 搜索框组件
    private EditText et_search; // 搜索按键
    private LinearLayout search_block; // 搜索框布局
    private ImageView searchBack; // 返回按键

    // 回调接口
    private ICallBack mCallBack;// 搜索按键回调接口
    private BCallBack bCallBack; // 返回按键回调接口

    // ListView列表 & 适配器
    private SearchListView searchListView;
    private BaseAdapter adapter;
    private TextView tv_clear;
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    //用户信息
    private User userInfo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.layout_search, container, false);
        initView(root);
        return root;
    }
    private void initView(View view){
        // 2. 绑定搜索框EditText
        et_search = (EditText)view.findViewById(R.id.et_search);
        // 3. 搜索框背景颜色
        search_block = (LinearLayout)view.findViewById(R.id.search_block);
        // 4. 历史搜索记录 = ListView显示
        searchListView = (SearchListView)view.findViewById(R.id.searchListView);
        // 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(getContext());
        // 5. 删除历史搜索记录 按钮
        tv_clear = (TextView)view.findViewById(R.id.tv_clear);
        tv_clear.setVisibility(INVISIBLE); // 初始状态 = 不可见
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            // 输入文本后调用该方法
            @Override
            public void afterTextChanged(Editable s) {
                // 每次输入后，模糊查询数据库 & 实时显示历史搜索记录
                // 注：若搜索框为空,则模糊搜索空字符 = 显示所有的搜索历史
                String tempName = et_search.getText().toString();
                queryData(tempName); // ->>关注1

            }
        });
        searchListView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, view1, position, id) -> {
            // 获取用户点击列表里的文字,并自动填充到搜索框内
            TextView textView = (TextView) view1.findViewById(android.R.id.text1);
            String name = textView.getText().toString();
            et_search.setText(name);
            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        });

    }
    /**
     * 关注1
     * 模糊查询数据 & 显示到ListView列表上
     */
    private void queryData(String tempName) {

        // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        searchListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        System.out.println(cursor.getCount());
        // 当输入框为空 & 数据库中有搜索记录时，显示 "删除搜索记录"按钮
        if (tempName.equals("") && cursor.getCount() != 0){
            tv_clear.setVisibility(VISIBLE);
        }
        else {
            tv_clear.setVisibility(INVISIBLE);
        };

    }
    // 搜索按键回调接口
    public interface ICallBack {
        void SearchAciton(String string);
    }

    // 返回按键接口回调
    public void setOnClickBack(BCallBack bCallBack){
        this.bCallBack = bCallBack;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
package com.lut.wyh.BookStore.activity.ui.notifications;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.adapter.CommentAdapter;
import com.lut.wyh.BookStore.entity.Comment;
import com.lut.wyh.BookStore.entity.Comments;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.CommentEvent;
import com.lut.wyh.BookStore.presenter.CommentPresenter;
import com.lut.wyh.BookStore.search.BCallBack;
import com.lut.wyh.BookStore.search.ICallBack;
import com.lut.wyh.BookStore.search.SearchListView;
import com.lut.wyh.BookStore.sqlite.RecordSQLiteOpenHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Comments comments;
    // 搜索框组件
    private EditText et_search; // 搜索按键
    private LinearLayout search_block; // 搜索框布局
    private ImageView searchBack; // 返回按键
    private Button search;
    // 回调接口
    private ICallBack mCallBack;// 搜索按键回调接口
    private BCallBack bCallBack; // 返回按键回调接口
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    // ListView列表 & 适配器
    private SearchListView searchListView;
    private BaseAdapter adapter;
    private TextView tv_clear;
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    //用户信息
    private User userInfo;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.layout_search, container, false);
        view=root;
        EventBus.getDefault().register(this);
        initData();
        return root;
    }
    public void initData(){
        new CommentPresenter().loadData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Comments comments){
        this.comments=comments;
        initView();
    }
    private void initView(){
        search=view.findViewById(R.id.search);
        // 2. 绑定搜索框EditText
        et_search = view.findViewById(R.id.et_search);
        // 3. 搜索框背景颜色
        search_block = view.findViewById(R.id.search_block);
        // 4. 历史搜索记录 = ListView显示
        searchListView = view.findViewById(R.id.searchListView);
        // 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(getContext());
        // 5. 删除历史搜索记录 按钮
        tv_clear = view.findViewById(R.id.tv_clear);
        tv_clear.setVisibility(INVISIBLE); // 初始状态 = 不可见
        recyclerView=view.findViewById(R.id.feel_exper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        commentAdapter=new CommentAdapter(comments.getComments(),getActivity());
        recyclerView.setAdapter(commentAdapter);
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
        searchListView.setOnItemClickListener((parent, view1, position, id) -> {
            // 获取用户点击列表里的文字,并自动填充到搜索框内
            TextView textView = view1.findViewById(android.R.id.text1);
            String name = textView.getText().toString();
            et_search.setText(name);
            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        });
        search.setOnClickListener(v->{
            if (et_search.getText().toString().equals("")){
                Toast.makeText(getContext(),"输入为空！",Toast.LENGTH_SHORT).show();
            }else{
                new CommentPresenter().refreshData(et_search.getText().toString());
            }
        });
        et_search.setOnEditorActionListener((v,actionId,event)->{
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //点击搜索的时候隐藏软键盘
                hideKeyboard();
                // 在这里写搜索的操作,一般都是网络请求数据
                new CommentPresenter().refreshData(et_search.getText().toString());
                return true;
            }
            return false;
        });
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm!=null){
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(CommentEvent commentEvent){
        commentAdapter.updateData(commentEvent.getRefreshData());
        commentAdapter.notifyDataSetChanged();
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
        EventBus.getDefault().unregister(this);
    }
}
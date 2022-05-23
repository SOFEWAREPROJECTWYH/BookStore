package com.lut.wyh.BookStore.activity.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.shape.OffsetEdgeTreatment;
import com.lut.wyh.BookStore.MainActivity;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.activity.DetailActivity;
import com.lut.wyh.BookStore.activity.StoreActivity;
import com.lut.wyh.BookStore.adapter.ListPagerAdapter;
import com.lut.wyh.BookStore.adapter.RecyclerViewAdapter;
import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.entity.Inventory;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.InventoryEvent;
import com.lut.wyh.BookStore.presenter.BookPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BookStoreFragment extends Fragment {

    private BookStoreViewModel bookStoreViewModel;
    //用户信息
    private User userInfo;
    private ViewPager viewPagerTextBook;
    private ViewPager viewPagerOtherBook;
    private TextView textbook;
    private TextView other_book;
    private ViewGroup viewGroup_textbook;
    private ViewGroup viewGroup_otherbook;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private BookPresenter bookPresenter;
    private List<Inventory> inventoryList;
    private View soft;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookStoreViewModel =
                new ViewModelProvider(this).get(BookStoreViewModel.class);
        View root=inflater.inflate(R.layout.fragment_store, container, false);
        EventBus.getDefault().register(this);
        textbook=root.findViewById(R.id.text_textbook);
        other_book=root.findViewById(R.id.text_otherbook);
        viewGroup_textbook=root.findViewById(R.id.textbook_channel);
        viewGroup_otherbook=root.findViewById(R.id.otherbook_channel);
        bookPresenter=new BookPresenter();
        setTextbook(root);
        setOtherBook(root);
        initPager(root);
        viewPagerOtherBook.setVisibility(View.GONE);
        setTextBookClick(textbook);
        setOtherBookClick(other_book);
        return root;
    }
    public void setTextbook(View view){
        List<View> viewList = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater();
        View software=layoutInflater.inflate(R.layout.pager_software_engineering, null);
        soft=software;
        View mechanical=layoutInflater.inflate(R.layout.pager_mechanical_engineering,  null);
        View electrical=layoutInflater.inflate(R.layout.pager_electrical_engineering_and_automation,null);
        View civil=layoutInflater.inflate(R.layout.pager_civil_engineering,null);
        viewList.add(software);
        viewList.add(mechanical);
        viewList.add(electrical);
        viewList.add(civil);
        ListPagerAdapter listPagerAdapter;
        viewPagerTextBook = view.findViewById(R.id.viewpager_textbook);
        listPagerAdapter = new ListPagerAdapter(viewList);
        viewPagerTextBook.setAdapter(listPagerAdapter);
        viewPagerTextBook.setOffscreenPageLimit(2);//设置缓存页面数。当前页，左右两边（单边）最大缓存页面数。
        listPagerAdapter.notifyDataSetChanged();
        textbook.setTextSize(25);
        textbook.setTextColor(Color.BLACK);
        textbook.getPaint().setFakeBoldText(true);
        textbook.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
        bookPresenter.loadData();
    }
    public void setOtherBook(View view){
        List<View> viewList = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater();
        View natural=layoutInflater.inflate(R.layout.pager_natural_science, null);
        View humanities=layoutInflater.inflate(R.layout.pager_the_humanities,  null);
        View fiction=layoutInflater.inflate(R.layout.pager_fiction,null);
        View painting=layoutInflater.inflate(R.layout.pager_painting_set,null);
        View refer=layoutInflater.inflate(R.layout.pager_reference_books,null);
        viewList.add(natural);
        viewList.add(humanities);
        viewList.add(fiction);
        viewList.add(painting);
        viewList.add(refer);
        ListPagerAdapter listPagerAdapter2;
        viewPagerOtherBook = view.findViewById(R.id.viewpager_otherbook);
        listPagerAdapter2 = new ListPagerAdapter(viewList);
        viewPagerOtherBook.setAdapter(listPagerAdapter2);
        viewPagerOtherBook.setOffscreenPageLimit(2);//设置缓存页面数。当前页，左右两边（单边）最大缓存页面数。
        listPagerAdapter2.notifyDataSetChanged();
    }
    private void initPager(View view){
        TextView soft=view.findViewById(R.id.soft);
        TextView mechanical=view.findViewById(R.id.mechanical);
        TextView electrical=view.findViewById(R.id.electrical);
        TextView civil=view.findViewById(R.id.civil);
        TextView natural=view.findViewById(R.id.natural);
        TextView humanities=view.findViewById(R.id.humanities);
        TextView fiction=view.findViewById(R.id.fiction);
        TextView painting=view.findViewById(R.id.painting);
        TextView refer=view.findViewById(R.id.refer);
        List<TextView> textViewList=new ArrayList<>();
        List<TextView> textViewList2=new ArrayList<>();
        textViewList.add(soft);
        textViewList.add(mechanical);
        textViewList.add(electrical);
        textViewList.add(civil);
        textViewList2.add(natural);
        textViewList2.add(humanities);
        textViewList2.add(fiction);
        textViewList2.add(painting);
        textViewList2.add(refer);
        setViewPagerTextBookChanged(viewPagerTextBook,textViewList);
        setViewPagerOtherBookChanged(viewPagerOtherBook,textViewList2);
    }
    private void setTextBookClick(TextView textbookview){
        textbookview.setOnClickListener(v -> {
            viewPagerTextBook.setVisibility(View.VISIBLE);
            viewPagerOtherBook.setVisibility(View.GONE);
            viewGroup_otherbook.setVisibility(View.GONE);
            viewGroup_textbook.setVisibility(View.VISIBLE);
            textbook.setTextSize(25);
            textbook.setTextColor(Color.BLACK);
            textbook.getPaint().setFakeBoldText(true);
            textbook.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
            other_book.setTextSize(20);
            other_book.getPaint().setFakeBoldText(false);
            other_book.getPaint().setTypeface(Typeface.DEFAULT);
        });
    }
    private void setOtherBookClick(TextView otherbook){
        otherbook.setOnClickListener(v -> {
            viewPagerTextBook.setVisibility(View.GONE);
            viewPagerOtherBook.setVisibility(View.VISIBLE);
            viewGroup_textbook.setVisibility(View.GONE);
            viewGroup_otherbook.setVisibility(View.VISIBLE);
            other_book.setTextSize(25);
            other_book.setTextColor(Color.BLACK);
            other_book.getPaint().setFakeBoldText(true);
            other_book.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
            textbook.setTextSize(20);
            textbook.getPaint().setFakeBoldText(false);
            textbook.getPaint().setTypeface(Typeface.DEFAULT);
        });
    }
    private void setViewPagerTextBookChanged(ViewPager viewPager_TextBook,List<TextView> textViewList){
        viewPager_TextBook.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(0).setTextColor(Color.RED);
                        break;
                    case 1:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.RED);
                        break;
                    case 2:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.RED);
                        break;
                    case 3:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.RED);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//页面变化监听
    }
    private void setViewPagerOtherBookChanged(ViewPager viewGroup_otherbook,List<TextView> textViewList){
        viewGroup_otherbook.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(4).setTextColor(Color.GRAY);
                        textViewList.get(0).setTextColor(Color.RED);
                        break;
                    case 1:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(4).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.RED);
                        break;
                    case 2:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(4).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.RED);
                        break;
                    case 3:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(4).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.RED);
                        break;
                    case 4:
                        textViewList.get(0).setTextColor(Color.GRAY);
                        textViewList.get(1).setTextColor(Color.GRAY);
                        textViewList.get(2).setTextColor(Color.GRAY);
                        textViewList.get(3).setTextColor(Color.GRAY);
                        textViewList.get(4).setTextColor(Color.RED);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//页面变化监听
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Inventories inventories){
        inventoryList=inventories.getInventoryList();
        initRecycler();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(User user){
        userInfo=user;
    }
    private void initRecycler() {
        recyclerView=soft.findViewById(R.id.recycler);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewAdapter=new RecyclerViewAdapter(inventoryList,this.getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener((v, position) -> {
            Intent intent=new Intent(this.getActivity(), DetailActivity.class);
            intent.putExtra("bookid",inventoryList.get(position).getBookid().toString());
            intent.putExtra("image",inventoryList.get(position).getUrl());
            intent.putExtra("textname",inventoryList.get(position).getBookname());
            intent.putExtra("textprice",inventoryList.get(position).getPrice());
            startActivity(intent);
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
package com.example.myrehabilitaion.ui.Record;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myrehabilitaion.Main;
import com.example.myrehabilitaion.R;
import com.example.myrehabilitaion.RecyclerExampleViewAdapter;
import com.example.myrehabilitaion.RecyclerInfoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment {



    private RecyclerExampleViewAdapter adapter_exampler;
    RecyclerView recyclerexample;
    Button imgbtnaddcase;
    Dialog mDlog;

    private RecyclerInfoAdapter infoadapter;
    RecyclerView recyclerInfo;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_frag__record, container, false);

        List<String> listStr = new ArrayList<String>();
        List<Integer> listImg = new ArrayList<Integer>();

//        for( int i=0 ; i <3 ; i++){
//            listStr.add(new String("目標" + String.valueOf(i+1)));
//        }
        String[] target_name = {"電療", "下肢動作"};
        for(int i=0;i<target_name.length;i++){
            listStr.add(target_name[i]);
        }
        Integer[] target_img ={R.drawable.electherapy, R.drawable.legtrain};
        for(int i=0;i<target_img.length;i++){
            listImg.add(target_img[i]);
        }




        recyclerexample = root.findViewById(R.id.recyclerview_home);
        recyclerexample.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        adapter_exampler = new RecyclerExampleViewAdapter(listStr, listImg);
        //adapter_home.addItem(sercmng.Syc());


        recyclerexample.setAdapter(adapter_exampler);

//        imgbtnaddcase = root.findViewById(R.id.btn_addcase);
//        imgbtnaddcase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDlog = new Dialog(v.getContext());
//                mDlog.setContentView(R.layout.dlg_addtarget);
//                mDlog.setCancelable(true);
//                mDlog.show();
//
//                Button btnaddtargt = mDlog.findViewById(R.id.btn_addtargt);
//                Button btncancelbox = mDlog.findViewById(R.id.btn_cancelbox);
//                final EditText edttargetname = mDlog.findViewById(R.id.edt_targetname);
//
//                btnaddtargt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        adapter_exampler.addItem(edttargetname.getText().toString().trim());
//                        //mSyncTarget = edttargetname.getText().toString().trim();
//
//                        Toast.makeText(v.getContext(),
//                                "您新增了新的目標", Toast.LENGTH_SHORT).show();
//
//                        mDlog.dismiss();
//                    }
//                });
//
//            }
//        });

        //TypedArray infoImageList =
        // getResources().obtainTypedArray(R.array.info_icon_list);

/*
        //主頁資訊欄
        List<Map<String,Object>> listImg_info = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 5 ; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(String.valueOf(i), infoImageList.getResourceId(i, 0));
            listImg_info.add(map);
        }

        recyclerInfo = root.findViewById(R.id.recyc_rehbinfo);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerInfo.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));
        infoadapter = new RecyclerInfoAdapter(listImg_info);

        recyclerInfo.setAdapter(infoadapter);
 */

//----------NEWS欄位----------
//        ViewPager viewPager;
//
//        //三個view
//        View view1;
//        View view2;
//        View view3;
//
//        //用來存放view並傳遞給viewPager的介面卡。
//        ArrayList<View> pageview;
//
//        //用來存放圓點，沒有寫第四步的話，就不要定義一下三個變量了。
//        ImageView[] tips = new ImageView[3];
//        ImageView imageView;
//
//        //圓點組的物件
//        ViewGroup group;
//
//        //將view加進pageview中
//        viewPager = (ViewPager)root.findViewById(R.id.viewpager);
//        view1 = getLayoutInflater().inflate(R.layout.view1,null);
//        view2 = getLayoutInflater().inflate(R.layout.view2,null);
//        view3 = getLayoutInflater().inflate(R.layout.view3,null);
//        pageview = new ArrayList<View>();
//        pageview.add(view1);
//        pageview.add(view2);
//        pageview.add(view3);
//
//        //viewPager下面的圓點，ViewGroup
//        group = (ViewGroup)root.findViewById(R.id.pointgroup);
//        tips = new ImageView[pageview.size()];
//        for(int i =0;i<pageview.size();i++){
//            imageView = new ImageView(getContext());
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(40,40));
//            imageView.setPadding(20, 0, 20, 0);
//            tips[i] = imageView;
//
//            //預設第一張圖顯示為選中狀態
//            if (i == 0) {
//                tips[i].setBackgroundResource(R.drawable.shape_point_selected);
//            } else {
//                tips[i].setBackgroundResource(R.drawable.shape_point_normal);
//            }
//
//            group.addView(tips[i]);
//        }
//        //這裡的mypagerAdapter是第三步定義好的。
//        viewPager.setAdapter(new NewsImageAdapter(pageview));
//        //這裡的GuiPageChangeListener是第四步定義好的。
//        viewPager.addOnPageChangeListener(new GuidePageChangeListener(tips, pageview));



        final FloatingActionButton fab01 = root.findViewById(R.id.floatingActionButton);
//        final FloatingActionButton fab02 = root.findViewById(R.id.fab02);
//        fab02.setVisibility(View.INVISIBLE);
        fab01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                fab01.setVisibility(View.INVISIBLE);
//                fab02.setVisibility(View.VISIBLE);
//
//                fab02.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Snackbar.make(view, "開啟統計頁面", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//
//                Intent intent = new Intent(getContext(),HomePage.class);
//                startActivity(intent);
//
//                        Main.this.showStatisticsFragment();
//
//                        fab02.setVisibility(View.INVISIBLE);
//                        fab01.setVisibility(View.VISIBLE);
                mDlog = new Dialog(v.getContext());
                mDlog.setContentView(R.layout.dlg_addtarget);
                mDlog.setCancelable(true);
                mDlog.show();

                Button btnaddtargt = mDlog.findViewById(R.id.btn_addtargt);
                Button btncancelbox = mDlog.findViewById(R.id.btn_cancelbox);
                final EditText edttargetname = mDlog.findViewById(R.id.edt_targetname);

                btnaddtargt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter_exampler.addItem(edttargetname.getText().toString().trim());
                        //mSyncTarget = edttargetname.getText().toString().trim();

                        Toast.makeText(v.getContext(),
                                "您新增了新的目標", Toast.LENGTH_SHORT).show();

                        mDlog.dismiss();
                    }
                });
            }
        });
        return root;
    }
}
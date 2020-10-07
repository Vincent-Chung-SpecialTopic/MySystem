package com.example.myrehabilitaion;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerExampleViewAdapter extends RecyclerView.Adapter<RecyclerExampleViewAdapter.ViewHolder>{

    private List<String> mListString = new ArrayList<String>();
    private List<Integer> mListImage = new ArrayList<Integer>();

    Dialog mDlog_case;

    public void addItem(String text) {
        // 為了示範效果，固定新增在位置3。若要新增在最前面就把3改成0
        mListString.add(text);
        mListImage.add(R.drawable.bg_02);
        notifyItemInserted(mListString.size());
    }
    public void updateItem(int position){

        EditText updatetargetname = mDlog_case.findViewById(R.id.edt_updatetargetname);

        mListString.set(position, updatetargetname.getText().toString().trim());//修改值
        notifyDataSetChanged();//刷新版列表权
    }



    // 刪除項目
    public void removeItem(int position){
        mListString.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImgView;
        public TextView mTxt;
        public ImageButton mEditImgPen;
        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.img_target);
            mTxt = (TextView) itemView.findViewById(R.id.txt_target);
            // 處理按下的事件。
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mDlog_case = new Dialog(v.getContext());
                    mDlog_case.setContentView(R.layout.dlg_case);
                    mDlog_case.setCancelable(true);
                    mDlog_case.show();

                    Button btnupdatetargt =  mDlog_case.findViewById(R.id.btn_updatetargt);

                    btnupdatetargt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateItem(getAdapterPosition());

                            Toast.makeText(v.getContext(),
                                    "您更新了目標", Toast.LENGTH_SHORT).show();

                            mDlog_case.dismiss();
                        }
                    });
                    Button btncanceledit = mDlog_case.findViewById(R.id.btn_cancelbox);
                    btncanceledit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDlog_case.dismiss();
                        }
                    });

                    Button btndeltarget =mDlog_case.findViewById(R.id.btn_deltbox);
                    btndeltarget.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeItem(getAdapterPosition());
                            mDlog_case.dismiss();
                        }
                    });

                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(),RecordMain.class);
            v.getContext().startActivity(intent);
        }
    }

    // 建構式，用來接收外部程式傳入的項目資料。
    public RecyclerExampleViewAdapter(List<String> listString, List<Integer> listImg) {

        mListString = listString;
        mListImage = listImg;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public RecyclerExampleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 建立一個 view。
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recyclerexampleview, viewGroup, false);

        // 建立這個 view 的 ViewHolder。
        RecyclerExampleViewAdapter.ViewHolder viewHolder = new RecyclerExampleViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。


    @Override
    public void onBindViewHolder(@NonNull RecyclerExampleViewAdapter.ViewHolder viewHolder, int i) {
        // 把資料設定給 ViewHolder。
        viewHolder.mImgView.setImageResource(mListImage.get(i));
        viewHolder.mTxt.setText(mListString.get(i).toString().trim());
    }

    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return mListString.size();
    }


}

package com.example.myrehabilitaion.FragmentHomePage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myrehabilitaion.LoginPage;
import com.example.myrehabilitaion.Main;
import com.example.myrehabilitaion.R;
import com.example.myrehabilitaion.RecordMain;
import com.example.myrehabilitaion.Variables;

public class StartRecord extends Fragment {
    Button mbtnconfirmstart, mbtncancelstart;
    private static Variables Variables = new Variables();
    Dialog mDlog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_startrecord, container, false);
        Spinner spnUnit = root.findViewById(R.id.spin_unit);

        mbtnconfirmstart = root.findViewById(R.id.btn_confirmstart);
        mbtncancelstart = root.findViewById(R.id.btn_cancelstartpage);
        mbtnconfirmstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDlog = new Dialog(getContext());
                mDlog.setContentView(R.layout.dlg_startcheck);
                mDlog.setCancelable(true);
                mDlog.show();

                Button btnstarttargt = mDlog.findViewById(R.id.btn_checkconfirm);
                Button btncancel = mDlog.findViewById(R.id.btn_checkcancel);
                final EditText edtargetinput = mDlog.findViewById(R.id.edt_inputarget);

                btnstarttargt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecordMain recordmain = (RecordMain) getActivity();
                        recordmain.showRecordigFragment();

                        //int count = Integer.parseInt(edtargetinput.getText().toString());
                        //Variables.timsSetter(count);

                        Intent itt = new Intent();
                        itt.putExtra("key1",Integer.parseInt(edtargetinput.getText().toString().trim()));

                        mDlog.dismiss();
                    }
                });


                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDlog.dismiss();
                    }
                });

            }
        });

        mbtncancelstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Main.class);
                startActivity(intent);
            }
        });
        return root;

    }


}

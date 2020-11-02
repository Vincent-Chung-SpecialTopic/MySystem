package com.example.myrehabilitaion;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;


public class BT_Main extends Fragment {
    protected BluetoothAdapter mBTAdapter;
    protected BluetoothSocket mBTSocket = null;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    protected final String TAG = BT_Main.class.getSimpleName();
    protected Handler mHandler;
    protected ConnectedThread mConnectedThread;
    private Handler handler = new Handler();

    int Count = 0;
    String CountS = null;
    String mReadBuffer = null;

    private final static int MESSAGE_READ = 2;
    private final static int CONNECTING_STATUS = 3;

    protected Button mStartBtn;

    private Long startTime;
    protected Button mStopBtn;
    protected TextView TimerTV;
    protected TextView BluetoothCount;

    TextView time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.bt_main, container, false);

        TimerTV = (TextView) root.findViewById(R.id.timer);
        mStopBtn = (Button)  root.findViewById(R.id.stop);
        BluetoothCount = (TextView) root.findViewById(R.id.textCount);
        mStartBtn = (Button) root.findViewById(R.id.StartBn);
        time = (TextView)  root.findViewById(R.id.timer);

        GlobalVariable globalVariable = new GlobalVariable();

        if(globalVariable.getDeviceAddress() ==null){
            Toast.makeText(getContext(),"尚未連接藍芽，無法進行記錄", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"已偵測到藍芽裝置", Toast.LENGTH_SHORT).show();
            new Thread()
            {
                public void run() {

                    mBTAdapter = BluetoothAdapter.getDefaultAdapter();
                    mBTAdapter.getBondedDevices();

                    GlobalVariable globalVariable = (GlobalVariable) getContext().getApplicationContext();
                    boolean fail = false;
                    BluetoothDevice device = mBTAdapter.getRemoteDevice(globalVariable.getDeviceAddress());

                    try {
                        mBTSocket = createBluetoothSocket(device);

                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                    // Establish the Bluetooth socket connection.
                    try {
                        mBTSocket.connect();
                        Log.d("tt", String.valueOf(  mBTSocket));
                    } catch (IOException e) {
                        try {
                            fail = true;
                            mBTSocket.close();
                            mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                    .sendToTarget();
                            mBTSocket.close();
                        } catch (IOException e2) {
                            //insert code to deal with this
                            Toast.makeText(getContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(fail == false) {


                        mConnectedThread = new ConnectedThread(mBTSocket);
                        mConnectedThread.start();


                        mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, globalVariable.getCaseName())
                                .sendToTarget();

                    }
                }
            }.start();

        }



        executeBT();

        return root;
    }

    protected void executeBT() {

        mHandler = new Handler(){
            public void handleMessage(android.os.Message msg){

                if(msg.what == MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    mReadBuffer = readMessage;
                    mConnectedThread.Counter();

                }
                if(msg.what == CONNECTING_STATUS){
                    if(msg.arg1 == 1){
//                        ((GlobalVariable) getContext().getApplicationContext()).main.setToolbarTitle("Connect to :" + String.valueOf(msg.obj) + "(Signals)");
                        Toast.makeText(getContext().getApplicationContext(), "Connect",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
//                        ((GlobalVariable) getContext().getApplicationContext()).main.setToolbarTitle("Connect Failed");
                        Toast.makeText(getContext().getApplicationContext(), "Disconnect",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };


        //*******************************更改****************************************

        mStartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mConnectedThread != null) {//First check to make sure thread created
                    Count = 0;
                    BluetoothCount.setText(String.valueOf(Count));
                    mConnectedThread.write("a");
                    Toast.makeText(getContext().getApplicationContext(),"開始!",
                            Toast.LENGTH_SHORT).show();
                    //設定定時要執行的方法
                    handler.removeCallbacks(updateTimer);
                    //取得目前時間
                    startTime = System.currentTimeMillis();
                    //設定Delay的時間
                    handler.postDelayed(updateTimer, 1000);
                    mStartBtn.setText("重新開始");
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(),"設備未連接!",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });


        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mConnectedThread.Cancel();
                mConnectedThread.CounterSTOP();
                onStop();
            }
        });

    }

//
//    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if(BluetoothDevice.ACTION_FOUND.equals(action)){
//                BluetoothDevice device =
//                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                // add the name to the list
//                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
//                mBTArrayAdapter.notifyDataSetChanged();
//            }
//        }
//    };

    private BluetoothSocket createBluetoothSocket( BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass()
                    .getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BTMODULEUUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    private class ConnectedThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.available();
                    if(bytes != 0) {
                        buffer = new byte[1024];
                        SystemClock.sleep(100); //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available(); // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes); // record how many bytes we actually read
                        mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                                .sendToTarget();
                        Log.d("test", String.valueOf("這裡"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String input) {
            byte[] bytes = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        public void Counter() {
            if (mReadBuffer != null)
            {
                Count++;
                BluetoothCount.setText(String.valueOf(Count));
                mReadBuffer = null;

            }
        }


        public void CounterSTOP() {
            CountS = Integer.toString(Count);
            BluetoothCount.setText(CountS);
        }

    }



    public void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimer);
    }

    //固定要執行的方法
    private Runnable updateTimer = new Runnable() {
        public void run() {
            final TextView time = (TextView) getView().findViewById(R.id.timer);
            Long spentTime = System.currentTimeMillis() - startTime;
            //計算目前已過分鐘數
            Long minius = (spentTime / 1000) / 60;
            //計算目前已過秒數
            Long seconds = (spentTime / 1000) % 60;
            time.setText(minius + ":" + seconds);
            handler.postDelayed(this, 1000);
        }
    };
}




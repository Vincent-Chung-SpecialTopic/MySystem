package com.example.myrehabilitaion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.datatype.Duration;

import static android.widget.Toast.LENGTH_LONG;

public class Class_Registeration extends AppCompatActivity {

    EditText mname,memail,mpassword;
    Button registerbtn;

    private static String ip = "140.131.114.241";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "109-rehabilitation";
    private static String username = "case210906";
    private static String password = "1@case206";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    Statement statement = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_registeration);

        mname = (EditText)findViewById(R.id.edtName);
        memail = (EditText)findViewById(R.id.edtEmail);
        mpassword = (EditText)findViewById(R.id.edtPassword);
        registerbtn = (Button)findViewById(R.id.mbtnRegistr);

        ActivityCompat.requestPermissions(Class_Registeration.this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Toast toast = Toast.makeText(Class_Registeration.this,"Success", Toast.LENGTH_SHORT);
            toast.show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(Class_Registeration.this,"ERROR", Toast.LENGTH_SHORT);
            toast.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(Class_Registeration.this,"FAILURE", Toast.LENGTH_SHORT);
            toast.show();

        }
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connection!=null){
                    try {
                        Toast toast = Toast.makeText(Class_Registeration.this,"註冊成功", Toast.LENGTH_SHORT);
                        toast.show();
                        statement = connection.createStatement();
                        //此執行可能有錯誤，但可運行
                        statement.executeQuery("INSERT INTO dbo.registered (username,Email,passwd)" +
                                " VALUES ('"+mname.getText().toString().trim()+"','"+memail.getText().toString().trim()+"','"+mpassword.getText().toString().trim()+"')");

                    } catch (SQLException e) {
//                        Toast toast = Toast.makeText(Class_Registeration.this,"跑到catch啦", Toast.LENGTH_SHORT);
//                        toast.show();
                        //會執行到此catch尚未解決但是資料傳輸成功
                        e.printStackTrace();
                    }
                }
                else {
                    Toast toast = Toast.makeText(Class_Registeration.this,"註冊失敗", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}

package com.example.fruitgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class OrangeActivity extends AppCompatActivity {

    private Button btnEN, btnCN, btnBack;
    private Boolean flag = false;
    private TextView mtvInfo, mtvTitle;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orange);
        btnCN = findViewById(R.id.btn_cn);
        btnEN = findViewById(R.id.btn_en);
        btnBack = findViewById(R.id.btn_back);
        mtvInfo = findViewById(R.id.mtv_info);
        mtvTitle = findViewById(R.id.mtv_title);
        mSharedPreferences = getSharedPreferences("flag", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        flag = mSharedPreferences.getBoolean("flag", flag);
        setListener();
        flag();
    }
    private void setListener(){
        Onclick onclick = new Onclick();
        btnEN.setOnClickListener(onclick);
        btnCN.setOnClickListener(onclick);
        btnBack.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_en:
                    flag = true;
                    break;
                case R.id.btn_cn:
                    flag = false;
                    break;
                case R.id.btn_back:
                    Intent intent = new Intent(OrangeActivity.this, MainActivity.class);
                    mEditor.putBoolean("flag", flag);
                    mEditor.apply();
                    startActivity(intent);
                    break;
            }
            flag();
        }
    }
    public void flag(){
        if(flag){
            btnBack.setText("BACK");
            btnCN.setText("Chinese");
            btnEN.setText("English");
            mtvInfo.setText(getResources().getString(R.string.orange_en));
            mtvTitle.setText("Orange");
        }
        else{
            mtvInfo.setText(getResources().getString(R.string.orange_cn));
            mtvTitle.setText("橙子");
            btnBack.setText("返回");
            btnCN.setText("中文");
            btnEN.setText("英文");
        }
    }
}
package com.example.newfacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tab2Activity extends AppCompatActivity {

    // Button object 생성
    Button button_buy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);



        //############################ 버튼 이벤트 처리 부분 ##############################
        // 1.......home_Button 누르면 첫페이지로 (intent)
        Button button_home =(Button)findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(tab2Activity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        // 2.......buy_Button 누르면 intent로 구매 페이지로 + 정보를 담아서
        Button button_buy =(Button)findViewById(R.id.button_buy);
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(tab2Activity.this, tab3Activity.class);
                intent2.putExtra("INTENT_KEY", 5);
                startActivity(intent2);
            }
        });










    }
}

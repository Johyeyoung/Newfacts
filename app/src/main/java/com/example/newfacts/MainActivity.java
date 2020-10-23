package com.example.newfacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    ListView mListView; //......list 참조 변수
    int[] images = {R.drawable.selecto, R.drawable.coffeebean};
    String[] storeName = {"selecto", "Coffeebean"};
    String[] menuName = {"추억의 달고나", "달고나 라떼"};
    String[] price = {"5000원", "6500원"};


    // 전달할 물품의 정보를 담는 String
    String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // button 객체 생성
        Button button_buy =(Button)findViewById(R.id.buy_btn);
        Button button_cart =(Button)findViewById(R.id.cart_btn);


        // 1. Button Action 처리
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tab2Activity.class);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tab3Activity.class);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });






        // ..........0. ListView 가져오기
        mListView = (ListView) findViewById(R.id.listView);
        // ..........1. listView header 추가 (나중에 카테고리로 만들 예정)
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View header = layoutInflater.inflate(R.layout.listview_header_view, null, false);
        mListView.addHeaderView(header);
        // ..........2. adaptor 추가
        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);

        // ..........3. 아이템을 [클릭]시의 이벤트 리스너를 등록
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                content += (storeName[position] + menuName[position] + price[position]);
            }
        });
    }


    // Adapter 생성하는데 BaseAdapter 타입으로
    class CustomAdaptor extends BaseAdapter{
        @Override
        public int getCount() {
            return images.length;
        } // listView 의 항목 개수
        @Override
        public Object getItem(int position) {  // listView 에서 해당 position 번째의 아이템을 반환
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.customlayout, null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView2);
            TextView mTextView1 = (TextView) view.findViewById(R.id.store_name);
            TextView mTextView2 = (TextView) view.findViewById(R.id.menu_name);
            TextView mTextView3 = (TextView) view.findViewById(R.id.price);
            mImageView.setImageResource(images[position]);
            mTextView1.setText(storeName[position]);
            mTextView2.setText(menuName[position]);
            mTextView3.setText(price[position]);
            return view;
        }
    }
}

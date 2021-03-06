package com.example.newfacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class tab3Activity extends AppCompatActivity {



    ListView mListView; //......list 참조 변수
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        // 1.......result_buy_Button 누르면 intent로 첫페이지로
        Button complete_btn =(Button)findViewById(R.id.complete_btn);
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tab3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });





        //############################ 리스트 뷰 생성##################################
        // ..........0. ListView 객체화
        mListView = (ListView) findViewById(R.id.listView);
        // ..........1. listView header 추가 (나중에 카테고리로 만들 예정)
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View header = layoutInflater.inflate(R.layout.listview_header_view, null, false);
        mListView.addHeaderView(header);
        // ..........2. adaptor 추가
        CustomChoiceListViewAdapter adapter =  new CustomChoiceListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String choice = intent.getExtras().getString("content");
        String price = choice.replaceAll("[^0-9]", "");
        textView =(TextView) findViewById(R.id.total_price);
        textView.setText("결제 금액 : " + price);
        if(!TextUtils.isEmpty(intent.getStringExtra("content"))) {
            int count = adapter.getCount();
            // 아이템 추가.
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.selecto), choice);
            // listview 갱신
            adapter.notifyDataSetChanged();
        }
        else{

        }
    }




    // Adapter 생성하는데 BaseAdapter 타입으로
    public class CustomChoiceListViewAdapter extends BaseAdapter {
        // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
        private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
        // ListViewAdapter의 생성자
        public CustomChoiceListViewAdapter() { }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return listViewItemList.size() ; }
        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();
            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customlayout_cart, parent, false);
            }
            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
           // ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView2) ;
            TextView textTextView = (TextView) convertView.findViewById(R.id.text1) ;
            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem listViewItem = listViewItemList.get(position);
            // 아이템 내 각 위젯에 데이터 반영
           // iconImageView.setImageDrawable(listViewItem.getIcon());
            textTextView.setText(listViewItem.getText());
            return convertView; }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public long getItemId(int position) {
            return position ; }
        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {
            return listViewItemList.get(position) ; }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(Drawable icon, String text) {
            ListViewItem item = new ListViewItem();
            item.setIcon(icon);
            item.setText(text);
            listViewItemList.add(item); }
    }

}



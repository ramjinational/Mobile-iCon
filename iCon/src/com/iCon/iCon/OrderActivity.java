package com.iCon.iCon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.iCon.iCon.R;

public class OrderActivity extends Activity implements OnItemClickListener {

    private ListView listView;
    private LinearLayout headerLinear;
    private OrderAdapter adapter;
    private ArrayList<Map<String, String>> mockData;
    private ArrayList<Map<String, String>> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mockData = getMockData();
        adapter = new OrderAdapter(this, mockData, R.layout.activity_order, new String[] { "order_reference",
                "confirmation_status", "settlement_status" }, new int[] { R.id.order_reference,
                R.id.confirmation_status, R.id.settlement_status });

        displayWithHeader();
    }

    private void displayWithHeader() {
        headerLinear = new LinearLayout(this);
        TextView order_reference = new TextView(this);
        order_reference.setText("Order Reference");

        TextView confirmation_status = new TextView(this);
        confirmation_status.setText("Confirm Status");

        TextView settlement_status = new TextView(this);
        settlement_status.setText("Settlement Status");

        int winWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() / 3.0);

        headerLinear.addView(order_reference, winWidth, 80);
        headerLinear.addView(confirmation_status, winWidth, 80);
        headerLinear.addView(settlement_status, winWidth, 80);

        HorizontalScrollView hsv = new HorizontalScrollView(this);
        listView = new ListView(this);
        listView.addHeaderView(headerLinear);
        listView.setAdapter(adapter);
        hsv.addView(listView);
        listView.setOnItemClickListener(this);
        listView.setItemsCanFocus(true);
        setContentView(hsv);
    }

    private ArrayList<Map<String, String>> getMockData() {

        String affirmed_order_reference = getIntent().getStringExtra("order_reference");
        String returned_confirmation_status = getIntent().getStringExtra("confirmation_status");

        mylist = new ArrayList<Map<String, String>>();
            for (int i = 0; i < 100; i++) {
                Map<String, String> item = new HashMap<String, String>();
                item.put("order_reference", "order_ref_" + i);
                if (i % 5 == 0) {
                    item.put("confirmation_status", "Affirmed");
                } else if(i % 3 == 0){
                    item.put("confirmation_status", "Rejected");
                } else{
                    item.put("confirmation_status", "In Progress");
                }
                item.put("settlement_status", "In Progress");
                if (("order_ref_" + i).equals(affirmed_order_reference)){
                    item.put("confirmation_status", returned_confirmation_status);
                }
                mylist.add(item);
            }
        return mylist;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String order_reference = ((TextView) view.findViewById(R.id.order_reference)).getText().toString();
        String confirmation_status = ((TextView) view.findViewById(R.id.confirmation_status)).getText().toString();
        String settlement_status = ((TextView) view.findViewById(R.id.settlement_status)).getText().toString();

        Intent intent = new Intent();
        intent.setClass(this, DisplayAndAffirmConfirmation.class);
        intent.putExtra("order_reference", order_reference);
        intent.putExtra("confirmation_status", confirmation_status);
        intent.putExtra("settlement_status", settlement_status);
        startActivity(intent);
    }
}

class OrderAdapter extends SimpleAdapter {

    private int[] colors = new int[] { 0x30FF0000, 0x300000FF };

    public OrderAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int winWidth = (int) (parent.getWidth() / 3.0);

        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        if (colorPos == 1) {
            view.setBackgroundColor(Color.argb(250, 255, 255, 255));
        } else {
            view.setBackgroundColor(Color.argb(250, 224, 243, 250));
        }

        TextView confirmation_status = (TextView) view.findViewById(R.id.confirmation_status);
        confirmation_status.setWidth(winWidth);
        confirmation_status.setTextColor(Color.BLACK);
        if ("Affirmed".equals(confirmation_status.getText())) {
            confirmation_status.setTextColor(Color.GREEN);
        } else if ("Rejected".equals(confirmation_status.getText())) {
            confirmation_status.setTextColor(Color.RED);
        }

        TextView settlement_status = (TextView) view.findViewById(R.id.settlement_status);
        settlement_status.setWidth(winWidth);
        settlement_status.setTextColor(Color.BLACK);
        if ("Affirmed".equals(settlement_status.getText())) {
            settlement_status.setTextColor(Color.GREEN);
        } else if ("Rejected".equals(settlement_status.getText())) {
            settlement_status.setTextColor(Color.RED);
        }

        TextView order_reference = (TextView) view.findViewById(R.id.order_reference);
        order_reference.setWidth(winWidth);
        order_reference.setTextColor(Color.BLACK);

        return view;
    }
}

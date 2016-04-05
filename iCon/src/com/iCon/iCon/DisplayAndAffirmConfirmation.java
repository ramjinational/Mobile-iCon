package com.iCon.iCon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class DisplayAndAffirmConfirmation extends Activity implements OnClickListener{

    Button viewButton, affirmButton, rejectButton;
    String order_reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_action);

        viewButton = (Button) findViewById(R.id.viewConfirm);
        viewButton.setEnabled(false);

        affirmButton = (Button) findViewById(R.id.affirmConfirm);
        affirmButton.setEnabled(false);

        rejectButton = (Button) findViewById(R.id.rejectConfirm);
        affirmButton.setEnabled(false);

        order_reference = getIntent().getStringExtra("order_reference");

        displayTradeInformationAndHandleUserAction();
        addListenerOnButton(viewButton);
        addListenerOnButton(affirmButton);
        addListenerOnButton(rejectButton);
    }

    public void displayTradeInformationAndHandleUserAction(){
        String statusText = getIntent().getStringExtra("confirmation_status");
        switch (statusText){
            case "In Progress":
                viewButton.setEnabled(true);
                affirmButton.setEnabled(true);
                rejectButton.setEnabled(true);
                break;
            case "Affirmed":
                viewButton.setEnabled(true);
                affirmButton.setEnabled(false);
                rejectButton.setEnabled(false);
                break;
            case "Rejected":
                viewButton.setEnabled(true);
                affirmButton.setEnabled(true);
                rejectButton.setEnabled(false);
                break;
            default:
                break;
        }
    }

    public void addListenerOnButton (Button button){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewConfirm:
                Intent viewIntent=new Intent();
                viewIntent.setClass(this,DisplayConfirmationPDF.class);
                startActivity(viewIntent);
                break;
            case R.id.affirmConfirm:
                Intent affirmIntent=new Intent();
                affirmIntent.setClass(this,OrderActivity.class);
                affirmIntent.setAction(affirmIntent.ACTION_SEND);
                affirmIntent.putExtra("confirmation_status", new String("Affirmed"));
                affirmIntent.putExtra("order_reference",order_reference);
                affirmIntent.setType("text/plain");
                startActivity(affirmIntent);
                break;
            case R.id.rejectConfirm:
                Intent rejectIntent=new Intent();
                rejectIntent.setClass(this,OrderActivity.class);
                rejectIntent.setAction(rejectIntent.ACTION_SEND);
                rejectIntent.putExtra("confirmation_status", new String("Rejected"));
                rejectIntent.putExtra("order_reference",order_reference);
                rejectIntent.setType("text/plain");
                startActivity(rejectIntent);
                break;
            default:
                break;
        }

    }
}
package com.iCon.iCon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayConfirmationPDF extends Activity {

    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_confirm);

        showConfirmation();

    }

    public void showConfirmation() {

                image = (ImageView) findViewById(R.id.confirmation1);
                image.setImageResource(R.drawable.confirmationsample);
            }

    }
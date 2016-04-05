package com.iCon.iCon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity implements OnClickListener {

    TextView warningText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);

        Button reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }
    protected boolean validateLogin() {
        return false;
    }
        @Override
        public void onClick(View view) {
            EditText user_id = (EditText) findViewById(R.id.user_id);
            EditText password = (EditText) findViewById(R.id.password);
            EditText authenticator_number = (EditText) findViewById(R.id.authenticator_number);

            warningText = (TextView) findViewById(R.id.warning);
            warningText.setText("");

            switch (view.getId()) {
                case R.id.login:
                    if ("".equals(user_id.getText().toString()) || "".equals(password.getText().toString())
                            || "".equals(authenticator_number.getText().toString())) {
                        warningText.setText("Login information can't be blank");
                        warningText.setTextColor(Color.RED);
                    } else try {
                        if (validLogin(user_id.getText().toString(), password.getText().toString(), authenticator_number
                                .getText().toString())) {
                            Intent intent = new Intent(this, OrderActivity.class);
                            startActivity(intent);
                        } else {
                            warningText.setText("Login information is incorrect, try again!");
                            warningText.setTextColor(Color.RED);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.reset:
                    user_id.setText("");
                    password.setText("");
                    authenticator_number.setText("");
                    user_id.requestFocus();
                    break;

                default:
                    break;
            }
        }

        private boolean validLogin(String user_id, String password, String authenticator_number) throws Exception {
            boolean isValid = false;
            User loginUser = new User(user_id, password, authenticator_number);

            List<User> users = new ArrayList<User>();
            users.add(new User("u1", "p1", "000000"));
            users.add(new User("u2", "p2", "000000"));
            users.add(new User("u3", "p3", "000000"));
            users.add(new User("u4", "p4", "000000"));

            for (User user : users) {
                if (user.getUser_id().equals(loginUser.getUser_id())
                        && user.getPassword().equals(loginUser.getPassword())
                        && TOTPGenerator.validateCode(Long.valueOf(authenticator_number).longValue() ,"ABCDEFGHIJ".getBytes(),System.currentTimeMillis() / 1000 / 30) ) {
                    isValid = true;
                }
            }
            return isValid;
        }
}

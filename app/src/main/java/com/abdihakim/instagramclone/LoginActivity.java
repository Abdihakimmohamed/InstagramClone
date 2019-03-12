package com.abdihakim.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView edtLoginEmail,edtLoginPassword;
    private Button btnLoginActivity,btnSignUpLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log in");
        // Iniialization
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);


        // set onclick listeners for the buttons
        btnLoginActivity.setOnClickListener(LoginActivity.this);
        btnSignUpLoginActivity.setOnClickListener(LoginActivity.this);


    }

    @Override
    public void onClick(View view) {
  switch (view.getId()){

      case R.id.btnLoginActivity:

          if(edtLoginEmail.getText().toString().equals("")|| edtLoginPassword.getText().toString().equals("")){
           FancyToast.makeText(LoginActivity.this,"Email or password missing",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
          }else {
              final ProgressDialog progressDialog = new ProgressDialog(this);
              progressDialog.setMessage("Loging in ");
              progressDialog.show();
              ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                      edtLoginPassword.getText().toString(),
                      new LogInCallback() {
                          @Override
                          public void done(ParseUser user, ParseException e) {
                              if (user != null && e == null) {

                                  FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged in", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                              transitionToSocialMediaActivity();
                              }
                              progressDialog.dismiss();
                          }
                      }
              );

          }
          break;

      case R.id.btnSignUpLoginActivity:

          Intent intent = new Intent(LoginActivity.this,SignUp.class);
          startActivity(intent);
          break;
  }
    }


    public  void rootTapped(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }

    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMediaActitvity.class);
        startActivity(intent);
    }
}

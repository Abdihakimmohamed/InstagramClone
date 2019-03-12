package com.abdihakim.instagramclone;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    // UI components

    private EditText edtEnterEmail , edtUserName ,edtEnterPassword;
    private Button btnSIgnUp , btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        // Initializing components

        edtEnterEmail = findViewById(R.id.edtEnterEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtEnterPassword = findViewById(R.id.edtEnterPassword);
        edtEnterPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){

                    onClick(btnSIgnUp);
                }
                return false;
            }
        });
        btnSIgnUp = findViewById(R.id.btnSIgnUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        // This is to set the onclick listener for the buttons

        btnSIgnUp.setOnClickListener(SignUp.this);
        btnLogIn.setOnClickListener(SignUp.this);


if (ParseUser.getCurrentUser()!= null){

   transitionToSocialMedia();
}



    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.btnSIgnUp:

                if(edtEnterEmail.getText().toString().equals("") ||
                        edtUserName.getText().toString().equals("")||
                        edtEnterPassword.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this,"Email,Username or password missing",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEnterEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtEnterPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing " + edtUserName.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                           // if there is no error transition to the social media activity

                                transitionToSocialMedia();

                            } else {

                                FancyToast.makeText(SignUp.this, "There is an Error: " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                            progressDialog.dismiss();
                        }
                    });

                }

                break;

            case R.id.btnLogIn:

                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;
        }


    }


    public void rootLayoutTapped(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }
    private void transitionToSocialMedia(){

        Intent intent = new Intent(SignUp.this, SocialMediaActitvity.class);
        startActivity(intent);
    }
}






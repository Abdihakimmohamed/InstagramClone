package com.abdihakim.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetData , txtSean;
    private Button btnGetAllData;
    private String allKickBoxer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Initializing my objects

        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        txtSean = findViewById(R.id.txtSean);
        btnGetAllData = findViewById(R.id.btnGetAllData);

        // Set onClick listener for the objects
        btnSave.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(SignUp.this);
        txtSean.setOnClickListener(SignUp.this);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("CrYTKoOVJP", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){

                      txtGetData.setText(object.get("Name")+" - "+" Punch power :"+object.get("Punch_power"));
                        }
                    }
                });
            }
        });
    txtSean.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ParseQuery<ParseObject> parseQueryTwo = ParseQuery.getQuery("KickBoxer");
        parseQueryTwo.getInBackground("ZII7jAwH78", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (object != null && e==null ){

                    txtSean.setText(object.get("Name")+" - "+"Kick speed "+object.get("Kick_speed")+" and "+" Kick power"+object.get("Kick_power "+"Power speed"+object.get("Punch_speed")+" Punch power"+object.get("Punch_power")));
                }
            }
        });
    }
});

    btnGetAllData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            allKickBoxer = "";
            ParseQuery<ParseObject> queryAll = ParseQuery.getQuery( "KickBoxer");
            queryAll.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e == null){

                        if(objects.size() > 0){

                           for(ParseObject kickBoxer : objects){

                               allKickBoxer = allKickBoxer + kickBoxer.get("Name")+" "+kickBoxer.get("Punch_speed")+" "+kickBoxer.get("Punch_power")+"\n";
                           }
                           FancyToast.makeText(SignUp.this, allKickBoxer,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }else {

                            Toast.makeText(SignUp.this,"Error",Toast.LENGTH_LONG).show();

                        }


                    }
                }
            });
        }
    });
    }


    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", edtName.getText().toString());
            kickBoxer.put("Punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("Punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("Kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("Kick_power", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("Name") + " is saved to sever", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {

                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });

        }catch (Exception e){

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }



}

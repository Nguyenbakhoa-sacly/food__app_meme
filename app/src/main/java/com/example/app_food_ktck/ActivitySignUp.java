package com.example.App_Food_KTCK;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySignUp extends AppCompatActivity {
    private EditText edtUserName,edtEmail,edtPassWord,edtConfirmPassword;
    private Button btnSignIn,btnSignUp;
    private ImageButton imageButtonMatPassword,imageButtonNhamMatPassword;
    private ImageButton imageButtonMatConfirmPassword,imageButtonNhamMatConfirmPassword;
    boolean status=false;
    public static boolean check =false;
    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPassWord= (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword= (EditText) findViewById(R.id.edtConfirmPassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        imageButtonMatPassword=(ImageButton) findViewById(R.id.imgMatPassword);
        imageButtonNhamMatPassword=(ImageButton) findViewById(R.id.imgNhamMatPassword);
        imageButtonMatConfirmPassword=(ImageButton) findViewById(R.id.imgMatConfirmPassword);
        imageButtonNhamMatConfirmPassword=(ImageButton) findViewById(R.id.imgNhamMatConfirmPassword);
        imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
        imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                if(edtUserName.getText().length()!=0&& edtEmail.getText().length()!=0
                        && edtPassWord.getText().length()!=0&&edtConfirmPassword.getText().length()!=0){
                    String regex ="^\\w+[a-z0-9]*@{1}\\w+mail.com$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(edtEmail.getText().toString());

                    if(!matcher.find()){
                        Toast.makeText(ActivitySignUp.this, "Sai định dạng Mail", Toast.LENGTH_SHORT).show();
                    }else{
                        if(edtPassWord.getText().toString().equals(edtConfirmPassword.getText().toString())){
                            Cursor dataAccount = ActivitySignIn.database.GetData("SELECT * FROM DuLieuTaiKhoan");
                            while (dataAccount.moveToNext()){
                                String userName =dataAccount.getString(0);
                                String password =dataAccount.getString(1);
                                if(userName.equals(edtUserName.getText().toString())){
                                    Toast.makeText(ActivitySignUp.this, "Tên tài khoản đã có người sử dụng", Toast.LENGTH_SHORT).show();
                                    status=true;
                                }
                            }
                            if(!dataAccount.moveToNext() && !status ){
                                TaiKhoan tk = new TaiKhoan();
                                tk.setUserName(edtUserName.getText().toString());
                                tk.setPassword(edtPassWord.getText().toString());
                                ActivitySignIn.database.insertTaiKhoan(tk);
                                Toast.makeText(ActivitySignUp.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(ActivitySignUp.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else
                {
                    Toast.makeText(ActivitySignUp.this, "Bạn cần nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check=true;
                Intent intent = new Intent(ActivitySignUp.this, ActivitySignIn.class);
                startActivity(intent);
            }
        });
        imageButtonMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
            }
        });
        imageButtonMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
            }
        });

    }
}
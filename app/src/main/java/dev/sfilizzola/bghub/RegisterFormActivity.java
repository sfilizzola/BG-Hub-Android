package dev.sfilizzola.bghub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterFormActivity extends BaseActivity {

    private String TAG = RegisterFormActivity.class.getSimpleName();

    private EditText userFullName;
    private EditText userLogin;
    private EditText userEmail;
    private EditText userPasswd;
    private EditText userConfPasswd;

    private Button userRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
        setContentView(R.layout.activity_register_form);
        activateToolbarWithHomeEnabled();

        userFullName = (EditText) findViewById(R.id.user_fullName);
        userLogin = (EditText) findViewById(R.id.user_login);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPasswd = (EditText) findViewById(R.id.user_passwd);
        userConfPasswd = (EditText) findViewById(R.id.user_confirm_passwd);
        
        userRegisterButton = (Button) findViewById(R.id.user_register_btn);
        userRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VerificaSenha(userPasswd.getText().toString(), userConfPasswd.getText().toString()))
                {
                    InsereUsuarioParse();
                }
                else
                {
                    userConfPasswd.setError(getString(R.string.error_incorrect_password));
                    userConfPasswd.requestFocus();
                }
            }
        });

    }

    private boolean VerificaSenha(String passField, String confirmField) {
        return passField.equals(confirmField);
    }


    private void InsereUsuarioParse() {

        ParseUser user = new ParseUser();
        user.setUsername(userLogin.getText().toString());
        user.setEmail(userEmail.getText().toString());
        user.setPassword(userPasswd.getText().toString());
        user.put("displayName", userFullName.getText().toString());
        //TODO - Colocar imagem do usuário

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(RegisterFormActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Log.e(TAG, "Erro de Login do Parse: " + e.getMessage());
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
    }
}

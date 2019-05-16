package com.qtcteam.loginmvc.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.qtcteam.loginmvc.R;
import com.qtcteam.loginmvc.model.api.ApiAdapter;
import com.qtcteam.loginmvc.model.api.scheme.in.LoginInRO;
import com.qtcteam.loginmvc.model.api.scheme.out.UserOutRO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN_MVC_LOGIN_ACT";
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.login_input_username);
        mPassword = findViewById(R.id.login_input_password);
    }

    public void login(View v) {
        // Esconder el teclado
        Utilities.hideKeyboard(this);
        // Verificar datos de usuario
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        if (!verifyLoginData(username, password)) return;
        // Consultar al API REST
        LoginInRO loginInRO = new LoginInRO(username, password);
        Call<UserOutRO> call = ApiAdapter.getInstance().login(loginInRO);
        call.enqueue(loginCallback);
        // Notificar al usuario que se está iniciando sesión
        Utilities.showToast(this, R.string.login_msg_loading);
    }

    private boolean verifyLoginData(String username, String password) {
        // Verificar nombre de usuario
        if (username.isEmpty()) {
            Utilities.showToast(this, R.string.login_msg_username_empty);
            return false;
        }
        // Verificar contraseña
        if (password.isEmpty()) {
            Utilities.showToast(this, R.string.login_msg_password_empty);
            return false;
        }
        return true;
    }

    private Callback<UserOutRO> loginCallback = new Callback<UserOutRO>() {
        @Override
        public void onResponse(@NonNull Call<UserOutRO> call, @NonNull Response<UserOutRO> response) {
            // Verificar respuesta de la API REST
            UserOutRO userOutRO = verifyResponse(response);
            if (userOutRO == null) return;
            // Mostrar el nombre y el correo electrónico
            AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(R.string.login_dialog_ok_title)
                    .setMessage(String.format(getString(R.string.login_dialog_ok_message),
                            userOutRO.getNames(), userOutRO.getEmail()))
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }

        @Override
        public void onFailure(@NonNull Call<UserOutRO> call, @NonNull Throwable t) {
            Log.d(TAG, "onFailure");
            showErrorMessage(t.getMessage());
        }
    };

    private UserOutRO verifyResponse(Response<UserOutRO> response) {
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            Log.d(TAG, "onResponse unsuccessful");
            String message = String.format(getString(R.string.api_msg_http_code),
                    response.code());
            showErrorMessage(message);
            return null;
        }

        // Obtener respuesta en JSON
        UserOutRO userOutRO = response.body();
        if (userOutRO == null) {
            Log.d(TAG, "response body is null");
            showErrorMessage(getString(R.string.api_msg_response));
            return null;
        }

        // Verificar que la respuesta no indique un error
        int errorCode = userOutRO.getErrorCode();
        if (errorCode != 0) {
            Log.d(TAG, "errorCode != 0");
            String message = userOutRO.getMessage();
            if (message == null) {
                message = String.format(getString(R.string.api_msg_service_code), errorCode);
            }
            showErrorMessage(message);
            return null;
        }
        return userOutRO;
    }

    private void showErrorMessage(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.api_title_login)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

}

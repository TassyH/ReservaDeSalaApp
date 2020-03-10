package com.example.ui.controledesalas.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorLogin;
import com.example.ui.controledesalas.ui.fragment_main.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class LoginUsuarioActivity extends AppCompatActivity {
    EditText edEmailLog, edSenhaLog;
    Button btnLogin, btnCadastrar2;
    SharedPreferences preferences;

    public static final String TITULO_APPBAR = "login Usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);

        ////////////////////////////
        preferences = getSharedPreferences("USER_LOGIN", 0);
        if (preferences.contains("userEmail")) {
            startActivity(new Intent(LoginUsuarioActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login_usuario);
        ///////////////////////////

        final TextInputLayout edEmailLog = findViewById(R.id.ed_login_email);
        final TextInputLayout edSenhaLog = findViewById(R.id.ed_login_senha);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCadastrar2 = (Button) findViewById(R.id.btn_cadastro2);

        btnCadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String authReturn = null;//q
                    String emailString = edEmailLog.getEditText().getText().toString().trim();
                    String senhaString = edSenhaLog.getEditText().getText().toString().trim();

                    authReturn = new VerificadorLogin().execute(emailString, senhaString).get();


                    if (emailString.isEmpty() || senhaString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "os campos de login na podem ficar vazios, bro", Toast.LENGTH_SHORT).show();
                    } else {

                    }

                    //////////////////////////////////////////////////////////////////////////////////////////

                    if (authReturn.length() > 0) {
                        try {
                            JSONObject usuarioJSON = new JSONObject(authReturn);

                            if (usuarioJSON.has("email") && usuarioJSON.has("id") && usuarioJSON.has("nome") && usuarioJSON.has("idOrganizacao")) {
                                int id = usuarioJSON.getInt("id");
                                String nome = usuarioJSON.getString("nome");
                                String email = usuarioJSON.getString("email");

                                JSONObject organizacao = usuarioJSON.getJSONObject("idOrganizacao");
                                String nomeOrganizacao = organizacao.getString("nome");
                                String tipoOrganizacao = organizacao.getString("tipoOrganizacao");
                                int idOrganizacao = organizacao.getInt("id");

                                preferences = getSharedPreferences("USER_LOGIN", 0);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("userEmail", email);
                                editor.putString("userName", nome);
                                editor.putString("userId", Integer.toString(id));
                                editor.putString("userIdOrganizacao", Integer.toString(idOrganizacao));
                                editor.putString("userNomeEmpresa", nomeOrganizacao);
                                editor.putString("userTipoEmpresa", tipoOrganizacao);
                                editor.commit();

                            }
                            System.out.println(preferences.getString("userEmail", null));
                            System.out.println(preferences.getString("userName", null));
                            System.out.println(preferences.getString("userId", null));
                            System.out.println(preferences.getString("userIdOrganizacao", null));
                            System.out.println(preferences.getString("userNomeEmpresa", null));
                            System.out.println(preferences.getString("userTipoEmpresa", null));
                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), " inválido", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(LoginUsuarioActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {

                        Toast.makeText(getApplicationContext(), "os dados inseridos nao sao validos, baby", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {

                }
            }
        });
    }

    public void exibirMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();

    }


}






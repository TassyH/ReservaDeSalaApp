package com.example.ui.controledesalas.activityfragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.activitysnormal.LoginUsuarioActivity;

public class ContaUsuarioFragment extends Fragment {
    TextView user_nome, user_email, user_org;
    EditText edNome, edEmail, edSenha;
    Spinner spOrganizacao;
    LoginUsuarioActivity login;
    Button btnSair;
    //SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String nomeString;
    public static final String mypreference = "USER_LOGIN";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_conta_usuario, container, false);

        btnSair = (Button) view.findViewById(R.id.btn_sair_conta);
        user_nome = (TextView) view.findViewById(R.id.tv_user_nome);
        user_email = (TextView) view.findViewById(R.id.tv_user_email);
        user_org = (TextView) view.findViewById(R.id.tv_user_organizacao);


        SharedPreferences preferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String idOrg = preferences.getString("userIdOrganizacao", null);

            String nomeUser = preferences.getString("userName", null);
            String emailUser = preferences.getString("userEmail", null);
            String nomeOrganizacao = preferences.getString("userNomeEmpresa", null);

            user_nome.setText("Nome: "+nomeUser);
            user_email.setText("Email: "+emailUser);
            user_org.setText("Sua Empresa: "+nomeOrganizacao);


                btnSair.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences pref = getContext().getSharedPreferences("USER_LOGIN", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove("userEmail");
                        editor.remove("userName");
                        editor.remove("userId");
                        editor.remove("userIdOrganizacao");
                        editor.remove("userNomeEmpresa");
                        editor.remove("userTipoEmpresa");
                        editor.commit();

                        Intent intent = new Intent(view.getContext(), LoginUsuarioActivity.class);
                        startActivity(intent);
                    }

                });
                return view;
            }

}


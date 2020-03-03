package com.example.ui.controledesalas.activityfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.ui.controledesalas.Adapter.ListaSalasAdapter;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorSala;
import com.example.ui.controledesalas.activitysnormal.ReservaSalaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaSalasFragment extends Fragment {

    TextView nomeSala;
    List<Sala> salas = new ArrayList<>();
    List<Sala> listaSalasStrings = new ArrayList<>();
    ListaSalasAdapter adapt;
    ArrayList<String> arraySalas;
    ListaSalasAdapter adpter;
    ConstraintLayout expandableView;
    CardView cardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_salas, container, false);
        nomeSala = view.findViewById(R.id.item_nome_sala);
        cardView = view.findViewById(R.id.card_item_lista_salas);


        try {
            SharedPreferences preferences = getContext().getSharedPreferences("USER_LOGIN", 0);
            String idOrg = preferences.getString("userIdOrganizacao", null);

            String verifSalas = "";
            verifSalas = new VerificadorSala().execute(idOrg).get();

            if (verifSalas.length() > 0) {
                JSONArray salasJson = new JSONArray(verifSalas);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("listaSalas", verifSalas);
                editor.commit();
                System.out.println("nome da sala" + verifSalas);

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJsonObjeto = salasJson.getJSONObject(i);
                    if (salaJsonObjeto.has("nome") && salaJsonObjeto.has("localizacao") && salaJsonObjeto.has("id")) {
                        String nome = salaJsonObjeto.getString("nome");
                        String local  =  salaJsonObjeto.getString("localizacao");
                        int idSala = salaJsonObjeto.getInt("id");



                        Sala novaSala = new Sala();
                        novaSala.setNome(nome);
                        novaSala.setLocalizacao(local);
                        salas.add(novaSala);

                    }
                }


                ListView listaDeSalas = view.findViewById(R.id.listview_de_salas);
                listaDeSalas.setAdapter(new ListaSalasAdapter(salas, getContext()));


                listaDeSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(view.getContext(), ReservaSalaActivity.class).putExtra("position", position);
                        startActivity(intent);
                    }
                });


            } else {
                System.out.println("Deu ruim bro");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/////////////////////////////////////////////////////////////////////////////////////////////////

        return view;
    }


}


package com.example.ui.controledesalas.activityfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.controledesalas.Adapter.ListaReservasAdapter;
import com.example.ui.controledesalas.Adapter.ListaSalasAdapter;
import com.example.ui.controledesalas.Dao.ReservaDAO;
import com.example.ui.controledesalas.Modal.Reserva;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorReserva;
import com.example.ui.controledesalas.ServidorHttp.VerificadorReservaByIdUsuario;
import com.example.ui.controledesalas.ServidorHttp.VerificadorSala;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaReservasFragment extends Fragment {

    List<Reserva> reservas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_reserva, container, false);
        TextView textoResev = view.findViewById(R.id.textReservas);
        textoResev.setVisibility(View.VISIBLE);
        TextView tx_reservaHoraInicial = view.findViewById(R.id.item_datahora_inicial);
        TextView tx_reservaHoraFinal = view.findViewById(R.id.item_datahora_final);
        TextView tx_reservaDescricao = view.findViewById(R.id.item_descricao_reserva);
        TextView tx_reservaNomeSala = view.findViewById(R.id.item_nome_sala);

        try {
            SharedPreferences preferences = getContext().getSharedPreferences("USER_LOGIN", 0);
            String userid = preferences.getString("userId", null);

            String verifReservas = "";
            verifReservas = new VerificadorReservaByIdUsuario().execute(userid).get();

            if (verifReservas.length() > 0) {
                JSONArray reservaJson = new JSONArray(verifReservas);
                System.out.println("reserva: " + verifReservas);

                for (int i = 0; i < reservaJson.length(); i++) {
                    JSONObject reservaJsonObjeto = reservaJson.getJSONObject(i);

                    int id = reservaJsonObjeto.getInt("id");
                    int idSala = reservaJsonObjeto.getInt("idSala");
                    int idUsuario = reservaJsonObjeto.getInt("idUsuario");
                    String dataHoraInicio = reservaJsonObjeto.getString("dataHoraInicio");
                    String dataHoraFim = reservaJsonObjeto.getString("dataHoraFim");
                    //boolean ativo = reservaObjeto.getBoolean("ativo");
                    String descricao = reservaJsonObjeto.getString("descricao");
                    String nomeOrganizador = reservaJsonObjeto.getString("nomeOrganizador");



                    Reserva novaReserva = new Reserva();
                    novaReserva.setNomeOrganizador(nomeOrganizador);
                    novaReserva.setDescricao(descricao);
                    novaReserva.setHoraIncial(dataHoraInicio);
                    novaReserva.setHoraFinal(dataHoraFim);
                    reservas.add(novaReserva);


                }

                ListView listaDeReservas = view.findViewById(R.id.listViewReservas);
                ListaReservasAdapter adapter = new ListaReservasAdapter(reservas, getContext());
                listaDeReservas.setAdapter(adapter);

            }

        } catch(InterruptedException e){
            e.printStackTrace();
        } catch(ExecutionException e){
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }



        return view;
        }


    }


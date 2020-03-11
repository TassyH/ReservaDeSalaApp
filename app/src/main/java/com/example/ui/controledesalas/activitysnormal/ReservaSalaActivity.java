package com.example.ui.controledesalas.activitysnormal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ui.controledesalas.Adapter.ListaReservasAdapter;
import com.example.ui.controledesalas.Dao.ReservaDAO;
import com.example.ui.controledesalas.Modal.Reserva;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorApagarReserva;
import com.example.ui.controledesalas.ServidorHttp.VerificadorReserva;
import com.example.ui.controledesalas.ServidorHttp.VerificadorReservaByIdUsuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservaSalaActivity extends AppCompatActivity {
    List<Sala> salas = new ArrayList<>();
    private ListaReservasAdapter adapter;
    List<Reserva> reservas = new ArrayList<>();

    public static final String mypreference = "USER_LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_sala);
        inicializacaoDosComponentes();
        exibirDadosReserva();
        cadastrarReserva();
        reservas.clear();

        final FloatingActionButton btn_infor = findViewById(R.id.fab_infor);
        TextView tx_nome = findViewById(R.id.tx_nome_sala);
        TextView nomeSala = findViewById(R.id.item_nome_sala);
        TextView tx_local = findViewById(R.id.tx_local_sala);
        TextView tx_latitude = findViewById(R.id.tx_latitude_sala);
        TextView tx_longitude = findViewById(R.id.tx_longitude_sala);
        TextView tx_quantPessoas = findViewById(R.id.tx_quantPessoas_sala);
        TextView tx_refrigeracao = findViewById(R.id.tx_refrigeracao_sala);
        TextView tx_area_sala = findViewById(R.id.tx_area_sala);
        TextView tx_midia = findViewById(R.id.tx_possuiMidia_sala);
        TextView tx_carqcteristicas = findViewById(R.id.tx_caracteristicas_sala);
        final ConstraintLayout expandir = findViewById(R.id.layoutExpand);
        final CardView cardView = findViewById(R.id.card_reserva);

        SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        btn_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandir.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandir.setVisibility(View.VISIBLE);
                    //btn_infor.setBackgroundResource(R.drawable.icon_btn_baixo);
                    // btn_infor.setVisibility(R.drawable.icon_btn_cima);

                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandir.setVisibility(View.GONE);
                    // btn_infor.setBackgroundResource(R.drawable.icon_btn_baixo);

                }


            }
        });

///////////////////////////////////////////////////////////////////////////////////////////////////

        try {

            String idOrg = preferences.getString("userIdOrganizacao", null);
            String listaSalasFromPref = "";
            listaSalasFromPref = preferences.getString("listaSalas", null);
            Intent in = getIntent();
            Bundle bundle = in.getExtras();
            int position = bundle.getInt("position");
            if (listaSalasFromPref != null) {

                JSONArray salasJson = new JSONArray(listaSalasFromPref);

                JSONObject salaJsonObjeto = salasJson.getJSONObject(position);
                if (salaJsonObjeto.has("nome") && salaJsonObjeto.has("quantidadePessoasSentadas") && salaJsonObjeto.has("id") && salaJsonObjeto.has("possuiMultimidia") && salaJsonObjeto.has("possuiArcon") && salaJsonObjeto.has("areaDaSala") && salaJsonObjeto.has("longitude") && salaJsonObjeto.has("latitude") && salaJsonObjeto.has("dataCriacao") && salaJsonObjeto.has("dataAlteracao") && salaJsonObjeto.has("localizacao")) {
                    String nome = salaJsonObjeto.getString("nome");
                    String local = salaJsonObjeto.getString("localizacao");
                    int quantPessoas = salaJsonObjeto.getInt("quantidadePessoasSentadas");
                    int idSala = salaJsonObjeto.getInt("id");
                    double area = salaJsonObjeto.getDouble("areaDaSala");
                    double longitude = salaJsonObjeto.getDouble("longitude");
                    double latitude = salaJsonObjeto.getDouble("latitude");
                    boolean midia = salaJsonObjeto.getBoolean("possuiMultimidia");
                    boolean refrigeracao = salaJsonObjeto.getBoolean(String.valueOf("possuiArcon"));

                    preferences = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("idSala", Integer.toString(idSala));
                    editor.commit();

                    atualizarListaReservas(idOrg);


                    tx_nome.setText(nome);
                    tx_local.setText("Localizacao: " + local);
                    tx_quantPessoas.setText("Capacidade: " + quantPessoas + " pessoas");
                    tx_area_sala.setText("Area da sala: " + area);
                    tx_latitude.setText("Latitude: " + latitude);
                    tx_longitude.setText("Longitude: " + longitude);
                    tx_midia.setText("possui midia : " + midia);
                    tx_refrigeracao.setText("refrigeracao: " + refrigeracao);


                }

                System.out.println(preferences.getString("idSala", null));//id da sala vindo do pref

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String userid = preferences.getString("idSala", null);
            String verifReservas = "";
            verifReservas = new VerificadorReserva().execute(userid).get();

            if (verifReservas.length() > 0) {
                JSONArray reservaJson = new JSONArray(verifReservas);
                System.out.println("reserva: " + verifReservas);
                for (int i = 0; i < reservaJson.length(); i++) {
                    JSONObject reservaJsonObjeto = reservaJson.getJSONObject(i);
                    reservas.clear();
                    int id = reservaJsonObjeto.getInt("id");
                    int idSala = reservaJsonObjeto.getInt("idSala");
                    int idUsuario = reservaJsonObjeto.getInt("idUsuario");
                    String dataHoraInicio = reservaJsonObjeto.getString("dataHoraInicio");
                    String dataHoraFim = reservaJsonObjeto.getString("dataHoraFim");
                    String descricao = reservaJsonObjeto.getString("descricao");
                    String nomeOrganizador = reservaJsonObjeto.getString("nomeOrganizador");

                    excluirReserva(id);

                    Reserva novaReserva = new Reserva();
                    novaReserva.setId(id);
                    novaReserva.setId_usuario(idUsuario);
                    novaReserva.setNomeOrganizador(nomeOrganizador);
                    novaReserva.setDescricao(descricao);
                    novaReserva.setHoraIncial(dataHoraInicio);
                    novaReserva.setHoraFinal(dataHoraFim);
                    reservas.add(novaReserva);

                }

                ListView listaDeReservas = findViewById(R.id.listViewReservas);
                ListaReservasAdapter adapter = new ListaReservasAdapter(reservas, this);
                listaDeReservas.setAdapter(adapter);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


/////////////////////////////////////////////////////////////////////////////////////////////////////


        FloatingActionButton botaoNovaReserva = findViewById(R.id.fab_reserva);
        botaoNovaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservaSalaActivity.this, CadastroReservaActivity.class);
                startActivity(intent);
            }
        });


    }

    private void atualizarListaReservas(String idOrg) {
    }

    private void cadastrarReserva() {
    }

    private void excluirReserva(int id) {

        ListView listaDeReservas = findViewById(R.id.listViewReservas);
        listaDeReservas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(ReservaSalaActivity.this).setTitle("Deletar").setMessage("quer mesmo deletar essa reserva bro?")
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences preferences = getSharedPreferences("USER_LOGIN", 0);
                                String verifDeleteReserva = "";

                                int user = reservas.get(position).getId_usuario();
                                int idReserva = reservas.get(position).getId();

                                System.out.println("id da reserva pra excluir reserva: " + idReserva);
                                System.out.println("id user brabo : " + user);

                                if (user == Integer.parseInt(preferences.getString("userId", null))) {
                                    try {
                                        verifDeleteReserva = new VerificadorApagarReserva().execute(String.valueOf(idReserva)).get();
                                        System.out.println("servidor delete: " + verifDeleteReserva);
                                        if (verifDeleteReserva.equals("A reserva foi cancelada com sucesso")) {
                                            reservas.remove(position);
                                            reservas.clear();
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(ReservaSalaActivity.this, "Reserva excluida com sucesso", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ReservaSalaActivity.this, "Erro ao exluir reserva", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(ReservaSalaActivity.this, "nao sei o que deu errado me abraça mano eu quero chorar", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("Nao", null)
                        .show();
                return true;

            }
        });


    }

    private void exibirDadosReserva() {
    }

    private void inicializacaoDosComponentes() {

    }
}







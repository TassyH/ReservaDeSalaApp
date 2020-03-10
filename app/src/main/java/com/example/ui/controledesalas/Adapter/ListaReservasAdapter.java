package com.example.ui.controledesalas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ui.controledesalas.Modal.Reserva;
import com.example.ui.controledesalas.R;

import java.util.List;

public class ListaReservasAdapter extends BaseAdapter {

    private final List<Reserva> reservas;
    private Context context;

    public ListaReservasAdapter(List<Reserva> reservas, Context context) {
        this.reservas = reservas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.reservas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.reservas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lista_reservas, parent, false);

        Reserva reserva = reservas.get(posicao);
        mostraNomeSala(viewCriada, reserva);
        mostraDescricao(viewCriada, reserva);
        mostraHoraInicial(viewCriada, reserva);
        mostraHorafinal(viewCriada, reserva);
        mostrarData(viewCriada, reserva);
        return viewCriada;
    }

    private void mostraNomeSala(View viewCriada, Reserva reserva) {
        TextView nomeLocador = viewCriada.findViewById(R.id.item_nome_sala);
        nomeLocador.setText(reserva.getNomeOrganizador());
    }

    private void mostraDescricao(View viewCriada, Reserva reserva) {
        TextView descricao = viewCriada.findViewById(R.id.item_descricao_reserva);
        descricao.setText(reserva.getDescricao());
    }

    private void mostrarData(View viewCriada, Reserva reserva) {
        TextView tvData = viewCriada.findViewById(R.id.item_data_reserva);
        String data = reserva.getHoraIncial().split("T")[0];
        String[] dataComposta = data.split("-");
        tvData.setText(dataComposta[2] + "/" + dataComposta[1] + "/" + dataComposta[0]);
    }

    private void mostraHoraInicial(View viewCriada, Reserva reserva) {
        TextView horaInicial = viewCriada.findViewById(R.id.item_datahora_inicial);
        String horaCompleta = reserva.getHoraIncial().split("T")[1].split("Z")[0];
        horaInicial.setText(horaCompleta.substring(0, 5));
    }

    private void mostraHorafinal(View viewCriada, Reserva reserva) {
        TextView horaFinal = viewCriada.findViewById(R.id.item_datahora_final);
        String horaCompleta = reserva.getHoraFinal().split("T")[1].split("Z")[0];
        horaFinal.setText(horaCompleta.substring(0, 5));
    }

}





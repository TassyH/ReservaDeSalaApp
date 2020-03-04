package com.example.ui.controledesalas.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.controledesalas.Modal.Reserva;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaReservasAdapter extends BaseAdapter {

    private final List<Reserva> reservas;
    private Context context;

    public ListaReservasAdapter(List<Reserva> reservas, Context context){
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

        TextView data = viewCriada.findViewById(R.id.item_data_reserva);
        TextView horaInicial = viewCriada.findViewById(R.id.item_datahora_inicial);
        TextView horaFinal = viewCriada.findViewById(R.id.item_datahora_final);

        Reserva reserva = reservas.get(posicao);
       // formataDatHoraDireito(data, horaFinal, horaInicial, reserva, viewCriada);
        mostraNomeSala(viewCriada, reserva);
        mostraDescricao(viewCriada, reserva);
        mostraHoraInicial(viewCriada, reserva);
        mostraHorafinal(viewCriada, reserva);

        SimpleDateFormat dateFormatOriginal = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dateFormatCerto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormatOriginal = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat hourFormatCerto = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < 2; i++) {
            String dataCompleta;

            if (i == 0) {
                dataCompleta = reserva.getHoraIncial();
            } else {
                dataCompleta = reserva.getHoraFinal();
            }
            String[] dataSplit = dataCompleta.split("T");
            String[] horaInicioSplit = dataSplit[1].split("Z");

            System.out.println("texugos: "+dataCompleta);

            System.out.println("pandas: "+dataSplit[1]);

            System.out.println("coalas: "+horaInicioSplit);


/*

            try {

                Date dateParse = dateFormatOriginal.parse(dataSplit[0]);
                String dateStr = dateFormatCerto.format(dateParse);
                Date horaInicioParseada = hourFormatOriginal.parse(horaInicioSplit[0]);
                String horaStr = hourFormatCerto.format(horaInicioParseada);

                System.out.println("pandas: "+dataSplit[1]);


                data.setText(dateStr);
                System.out.println(data.getText().toString());

                if (i == 0) {
                    horaInicial.setText(horaStr + " - ");
                } else {
                    horaFinal.setText(horaStr);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

*/
        }

        return viewCriada;
    }

 //   private void formataDatHoraDireito(TextView data, TextView horaFinal, TextView horaInicial, Reserva reserva, View viewCriada) {

    private void mostraNomeSala(View viewCriada, Reserva reserva) {
        TextView nomeLocador = viewCriada.findViewById(R.id.item_nome_sala);
        nomeLocador.setText(reserva.getNomeOrganizador());

    }

    private void mostraDescricao(View viewCriada, Reserva reserva) {
        TextView descricao = viewCriada.findViewById(R.id.item_descricao_reserva);
        descricao.setText(reserva.getDescricao());
    }


    private void mostraHoraInicial(View viewCriada, Reserva reserva) {
        TextView horaInicial = viewCriada.findViewById(R.id.item_datahora_inicial);
        horaInicial.setText(reserva.getHoraIncial());
    }

    private void mostraHorafinal(View viewCriada, Reserva reserva) {
        TextView horaFinal = viewCriada.findViewById(R.id.item_datahora_final);
        horaFinal.setText(reserva.getHoraFinal());

    }





    }

/*
    formataHoraDireito(reserva, horaInicio, horaFim) {

    }
*/




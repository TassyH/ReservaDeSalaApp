package com.example.ui.controledesalas.ServidorHttp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificadorReserva extends AsyncTask<String, Void, String>
{
    String urlWS = "http://172.30.248.144:8080/ReservaDeSala/rest/reserva/byIdSala/";

    @Override
    protected String doInBackground(String... strings)
    {
        StringBuilder result = new StringBuilder();
        try
        {
            URL url = new URL(urlWS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("authorization", "secret");
            conn.setRequestProperty("id_sala", strings[0]);
            conn.setConnectTimeout(2000);

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();





            System.out.println("ce puxou a resrva peli id sala  sim irmao");
            return result.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("cadastro de reservas nao deu boa nao irmao");
        }
        return result.toString();
    }


}

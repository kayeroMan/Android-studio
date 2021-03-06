package com.example.sarr.canadaapp;
/**
 * Ce Programme est une intervalle est une application android qui fourni
 * des informations générales sur les provinces du Canada.
 * Il a été élaboré par des étudiants au Baccalauréat en Informatique
 * de l'Université de Montréal . Hiver 2018.
 * Willy FOADJO:  Matricule :20059876
 * Abdramane Diasso: Matricule 20057513
 * Mohamed Sarr : Matricule 20050326
 */

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *Cette classse initialise le service de connexion pour l,activit, actualité
 */

public class Function {

    /**
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    /**
     *
     * @param targetURL
     * @param urlParameters
     * @return
     */

    public static String excuteGet(String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Creer connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-CA");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            InputStream is;
            int status = connection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK)
                is = connection.getErrorStream();
            else
                is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}

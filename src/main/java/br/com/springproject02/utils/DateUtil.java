package br.com.springproject02.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    //Método para converter um valor string no formato YYYY-MM-DD
    //para o tipo java.util.Date
    public static Date toDate(String date) {

        //Formato : YYYY-MM-DD => substring
        //índice, quantidade (sempre contando do primeiro elemento)
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        // month -1 pq Janeiro é 0, Fev 1 etc
        Calendar calendar = new GregorianCalendar(year, month -1, day);

        return calendar.getTime(); //Transforma em Date

    }
}

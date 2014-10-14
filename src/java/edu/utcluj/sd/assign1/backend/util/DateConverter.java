/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utcluj.sd.assign1.backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eniko
 */
public class DateConverter {
     public static String convertToString(Date birthDate) {
  SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
  String date_to_string = formatDate.format(birthDate);
return date_to_string;
}
   public static Date convertToDate(String birthDate) {
  Date date = null;
         try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
         } catch (ParseException ex) {
             Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
         }
  
return date;
}   
}

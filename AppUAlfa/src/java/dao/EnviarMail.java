package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import util.Conexion;

public class EnviarMail {

    private String cod;
    private Connection conexion;

    public EnviarMail() {
        this.cod = Integer.toString((int) Math.floor(Math.random() * (1000000 - 100000 + 1) + (100000)));
        Conexion db = Conexion.getConexion();
        this.conexion = db.getConnection();

    }

    public void sendMail(String toAdd) {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            System.out.println("Entro Al Metodo De Registro");
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("appumartsw@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(toAdd));
            message.setSubject("Verificación de cuenta");
            message.setText("Tu código: "+this.cod);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("appumartsw@gmail.com", "dondetusicompras");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCod() {

        return cod;

    }
    
    
    public void sendCheckOut(String toAdd,String map){
    
        try {
            System.out.println("***********EMPEZANDO A ENVIAR MAIL/INICIO CONFIGURACIONES**********");
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("appumartsw@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(toAdd));
            message.setSubject("Tienes un nuevo pedido!!");
            
            System.out.println("ESTE ES EL HTML DEL PEDIDO!!"+map);

            final MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(map, "text/html");
            // Create the Multipart.  Add BodyParts to it.
            final Multipart mp = new MimeMultipart("alternative");
            mp.addBodyPart(htmlPart);
            
            message.setContent(mp);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("appumartsw@gmail.com", "dondetusicompras");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            System.out.println("++++++++++++++++++CORREO ENVIADO DEL TODO/FINITO++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    public String CorreoTienda(int id){
        
        String query="select vendedor from Tiendas where id="+id;
        String Correo="";
        String vendedor="";
        
        try {
            
            Statement st = this.conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next()){
            vendedor=rs.getString(1);
                System.out.println("vendedor"+vendedor);
           if (vendedor!=null) {
               query="select correo from vendedores where celular='"+vendedor+"'";
               ResultSet rs2 = st.executeQuery(query);
                 while(rs2.next()){
                Correo=rs2.getString(1);
                     System.out.println(Correo);
            }
                 return Correo;
            } 
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return null;
    
    }
    
    
}
//=======
//package dao;
//
//import java.util.Properties;
//import java.util.Random;
//
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class EnviarMail {
//
//    private String cod;
//
//    public EnviarMail() {
//        this.cod = Integer.toString((int) Math.floor(Math.random() * (1000000 - 100000 + 1) + (100000)));
//
//    }
//
//    public void sendMail(String toAdd) {
//        try {
//            // Propiedades de la conexión
//            Properties props = new Properties();
//            props.setProperty("mail.smtp.host", "smtp.gmail.com");
//            props.setProperty("mail.smtp.starttls.enable", "true");
//            props.setProperty("mail.smtp.port", "587");
//            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
//            props.setProperty("mail.smtp.auth", "true");
//
//            // Preparamos la sesion
//            Session session = Session.getDefaultInstance(props);
//
//            // Construimos el mensaje
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("appumartsw@gmail.com"));
//            message.addRecipient(
//                    Message.RecipientType.TO,
//                    new InternetAddress(toAdd));
//            message.setSubject("Verificación de cuenta");
//            message.setText("Tu código: "+this.cod);
//
//            // Lo enviamos.
//            Transport t = session.getTransport("smtp");
//            t.connect("appumartsw@gmail.com", "dondetusicompras");
//            t.sendMessage(message, message.getAllRecipients());
//
//            // Cierre.
//            t.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getCod() {
//
//        return cod;
//
//    }
//}
//>>>>>>> origin/nico

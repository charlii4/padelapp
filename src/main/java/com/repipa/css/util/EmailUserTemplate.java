package com.repipa.css.util;

import java.util.List;

public class EmailUserTemplate {
    public String generateTemplate(String content) {
        String html = "<!DOCTYPE html>\r\n"
                + "<html>\r\n"
                + "\r\n"
                + "<head>\r\n"
                + "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n"
                + "	<link href=\"https://fonts.googleapis.com/css2?family=Raleway&display=swap\" rel=\"stylesheet\">\r\n"
                + "</head>\r\n"
                + "\r\n"
                + "<body style=\"background-color: #F2F2F2;\">\r\n"
                + "\r\n"
                + "\r\n"
                + "    <table width=\"70%\" border=\"0\" cellspacing=\"20\" cellpadding=\"0\" align=\"center\" style=\"background-color: white; Margin-top: 30px;\">\r\n"
                + "        <tr style=\"width: 300;\">\r\n"
                + "            <div style=\"background-color: #311b92; text-align: center;\">\r\n"
                + "                <h1 style=\"font-family: 'Raleway', sans-serif, italic !important; color:white; font-style:italic;\">Ecoplan</h1>\r\n"
                + "            </div>\r\n"
                + "            \r\n"
                + "        </tr>\r\n"
                + "        <tr>\r\n"
                + "            <td style=\"Margin: 20px;font-family: 'Raleway', sans-serif, italic !important; background-color: white;\">\r\n"
                + "                <p>"+content+"</p>\r\n"
                + "            <p style=\"color:gray\">Por favor, no responda a esta e-mail. <br>\r\n"
                + "                Para informar de cualquier duda o problema envíe un correo a <a\r\n"
                + "                    href=\"mailto:ITCORPORATIVA@GHENOVA.NET\">ITCORPORATIVA@GHENOVA.NET</a> <br>\r\n"
                + "                - <i>Equipo Ecoplan</i></p>\r\n"
                + "            </td>\r\n"
                + "        </tr>\r\n"
                + "    </table>\r\n"
                + "\r\n"
                + "\r\n"
                + "</body>\r\n"
                + "\r\n"
                + "</html>";
        return html;
    }
    public String newUser(String user, String pwd) {
        String message = "<i>Bienvenido a REPIPA!</i><br>";
        message += "<b>Tu usuario ha sido creado con éxito</b><br><br>";
        message += "<font color=red>CREDENCIALES:</font><br>";
        message += "<b>USUARIO: </b>"+user+"<br>";
        message += "<b>CONTRASEÑA: </b>"+pwd+"<br><br>";
        message += "Recuerda cambiar tu contraseña en el primer inicio<br><br>";

        return generateTemplate(message);
    }

    public String resetPassword(String pwd) {
        String message = "<i>REPIPA INFO - CAMBIO DE CONTRASEÑA</i><br>";
        message += "Se ha reestablecido su contraseña. Su nueva contraseña es: <br>";
        message += "<i style=\"text-align:center\">" + pwd + "</i><br><br>";
        message += "<b>Recuerde cambiar esta contraseña cuando inicie sesión.</b><br>";
        message += "Si usted no ha pedido este cambio de contraseña. Póngase en contacto con el administrador.<br>";

        return generateTemplate(message);
    }

    public String inactiveUsers(List<String> agresso_codes) {

        String message = "<i>Administrador, </i><br>";
        message += "Se han encontrado usuarios activos en Ecoplan dados de baja en Agresso hace más de 3 días.<br><br>";
        message += "Código(s) de Agresso:<br>";
        for(String x: agresso_codes)
            message += "<li>"+x+"</li><br>";

        return generateTemplate(message);
    }

    public String code(String code) {
        String message = "Tu código de verificación de Ecoplan es: <br>";
        message += "<font color=red ><b>"+code+"</b></font><br>";
        message += "Si usted no ha pedido este cambio de contraseña. Haga caso omiso a este correo.<br>";
        return generateTemplate(message);
    }

    public String imputacionesIncorrectas(List<String> sentido1, List<String> sentido2, List<String> sinImputar, boolean actual, String fecha) {
        String message = "<i>Administrador, </i><br>";

        if(sentido1.size() != 0 || sentido2.size() != 0 || sinImputar.size() != 0) {
            message += "Hay inconsistencias entre las vacaciones solicitadas en Ecoplan y los días imputados en Agresso";

            if(actual == true)
                message += " <b>hasta la fecha " + fecha + "</b>.<br><br>";
            else
                message += " <b>después de la fecha " + fecha + "</b>.<br><br>";
        }
        else
            message +="No hay inconsistencias entre las vacaciones solicitadas en Ecoplan y los días imputados en Agresso";

        if(sentido1.size() != 0) {
            message += "<b>Vacaciones solicitadas en Ecoplan NO imputadas correctamente en Agresso:</b><br>";
            for(String x: sentido1)
                message += "<li>" + x + "</li><br>";
        }

        if(sentido2.size() != 0) {
            message += "<br><b>Vacaciones imputadas en Agresso sin aprobación en Ecoplan:</b><br>";
            for(String x: sentido2)
                message += "<li>" + x + "</li><br>";
        }

        if(sinImputar.size() != 0) {
            message +="<br><b>Vacaciones aprobadas en Ecoplan no imputadas en Agresso aun</b>";
            for(String x: sinImputar)
                message += "<li>" + x + "</li><br>";
        }

        return generateTemplate(message);
    }

    public String imputacionesUsuariosDeBaja(List<String> inactivos) {
        String message = "<i>Administrador, </i><br>";
        message += "Hay inconsistencias entre las vacaciones solicitadas en Ecoplan y los días imputados en Agresso.<br><br>";
        message += "<br><b>Vacaciones no imputadas en Agresso, aprobadas en Ecoplan de usuarios inactivos actualmente</b>";
        for(String x: inactivos)
            message += "<li>" + x + "</li><br>";
        return generateTemplate(message);
    }

    public String cambioAImputado(int count) {
        String message = "<i>Administrador, </i><br>";
        message += "Se han realizado los siguientes cambios <br><br>";
        message += "+ "+count+ " ausencias actualizadas a estado Imputado";
        return generateTemplate(message);
    }

    public String cambioAprobado(int count) {
        String message = "<i>Administrador, </i><br>";
        message += "Se han realizado los siguientes cambios <br><br>";
        message += "+ "+count+ " ausencias actualizadas de estado Imputado a Aprobado";
        return generateTemplate(message);
    }
}


<%@page import="vo.ProductoVO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <!--<script type="text/javascript" src="InfoCarritoAjax.js" charset="UTF-8"></script>-->
        <script type="text/javascript" src="InfoCheckOut.js" charset="UTF-8"></script>
        <script type="text/javascript" src="seleccionProductoAjax.js"></script>
        <link href="StyleLogin.css" rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Leckerli+One" rel="stylesheet">
        <title>Tú Carrito</title>
        <style>
            @font-face
            {
                font-family: 'carrito.ttf';
                src: url('Pictures/carrito.ttf');
            }

            *{
                font-family: 'carrito.ttf';
            }
            .carro{
                top:600px;
                left:auto;
                right: auto;
                position:fixed;
                z-index: -1;  
                filter:brightness(1);
            }
            #InfoCarro {
                -webkit-border-radius: 15px;
                -moz-border-radius: 15px;
                border-radius: 15px;
                color: #2485e5;
                font-weight: light;
                border: 1px solid #CCC;
                padding: 5px 15px;
                -webkit-transition-duration: 0.4s; /* Safari */
                transition-duration: 0.4s;
                cursor: pointer
            }

            #InfoCarro:hover {
                /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#cb60b3+0,ad1283+50,de47ac+100;Pink+3D */
                background: rgb(203,96,179); /* Old browsers */
                background: -moz-linear-gradient(top, rgba(203,96,179,1) 0%, rgba(173,18,131,1) 50%, rgba(222,71,172,1) 100%); /* FF3.6-15 */
                background: -webkit-linear-gradient(top, rgba(203,96,179,1) 0%,rgba(173,18,131,1) 50%,rgba(222,71,172,1) 100%); /* Chrome10-25,Safari5.1-6 */
                background: linear-gradient(to bottom, rgba(203,96,179,1) 0%,rgba(173,18,131,1) 50%,rgba(222,71,172,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cb60b3', endColorstr='#de47ac',GradientType=0 ); /* IE6-9 */
                color: white
            }
            #Confirmar {
                -webkit-border-radius: 15px;
                -moz-border-radius: 15px;
                border-radius: 15px;
                color: #2485e5;
                font-weight: light;
                border: 1px solid #CCC;
                padding: 5px 15px;
                -webkit-transition-duration: 0.4s; /* Safari */
                transition-duration: 0.4s;
                cursor: pointer
            }

            #Confirmar:hover {
                /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#cb60b3+0,ad1283+50,de47ac+100;Pink+3D */
                background: rgb(203,96,179); /* Old browsers */
                background: -moz-linear-gradient(top, rgba(203,96,179,1) 0%, rgba(173,18,131,1) 50%, rgba(222,71,172,1) 100%); /* FF3.6-15 */
                background: -webkit-linear-gradient(top, rgba(203,96,179,1) 0%,rgba(173,18,131,1) 50%,rgba(222,71,172,1) 100%); /* Chrome10-25,Safari5.1-6 */
                background: linear-gradient(to bottom, rgba(203,96,179,1) 0%,rgba(173,18,131,1) 50%,rgba(222,71,172,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cb60b3', endColorstr='#de47ac',GradientType=0 ); /* IE6-9 */
                color: white
            }
        </style>
        
        
        
        <style>
            #map {
             height: 200px;
             width: 400px;
            }
        </style>
        <script>
            jQuery(document).ready(function () {
                jQuery("#loader").fadeOut("slow");
                var Ancho = screen.width;
                var Alto = screen.height;
                //alert(Ancho + " Y " + Alto);


                if ((Ancho <= 600)) {

                    //alert("Menor a 600");

                }

                if ((Ancho => 600)) {

                    //alert("Mayor a 600");
                    $('#llegada2').append('<a href="listarTiendas.jsp"><img src="Pictures/AppuMartTextoBorde.png"></a>');
                    $('#llegada').append('<img src="Pictures/checkout.png">');
                }
            });

        </script>
    </head>
    <body>
        <% HttpSession mySession = request.getSession();
            String correo = (String) mySession.getAttribute("correo");
//            String correo = "diego@correo.usa.edu.co";
        %>

        <%if (correo == null) {%>
        <META HTTP-EQUIV="REFRESH" CONTENT="0;URL=index.jsp">
        <%}%>
        <!--<img src="carrito_bg.jpg" class="background">-->
        <form name="form1">
            <center>
                <br><br><br>
                <input class="submit" type="text" id="opcion" value="1" onclick="form1.submit()"  style="visibility:hidden"/><br>
                
                <p id="ack"></p>
                <p id="ackk"></p>
                <br><br>
                <input class="btn" type="submit" id="InfoCarro" value="Actualizar"/>
                <br><br><br>
                <!--<textarea rows="4" cols="50" name="comment" id="comment">Ingresa Los Comentarios Del Pedido Aqui...</textarea><br>
                <input class="btn" type="submit" id="Confirmar" value="Confirmar"/>-->
            </center>
            
             <center><div id="carlos"></div></center>

        </form>
        
        <form name="form2">
            <center>
                
                <textarea rows="4" cols="50" name="comment" id="comment">Ingresa Los Comentarios Del Pedido Aqui...</textarea><br>
                <!--<input class="submit" type="text" id="opcion1" value="3" onclick="form2.submit()" style="visibility:hidden"/><br>
                <input class="btn" type="submit" id="Confirmar" value="Confirmar"/>-->
                <button onclick="mifuncion()">Confirmar</button>
                <div id="map"></div>
            </center>

        </form>

        <!--<div class="carro"><img src="Pictures/CarroMedio.png"></div>-->
    <center><div id="llegada"></div></center>
    <center><div id="llegada2"></div></center>
        
        
        
        
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB78-OGRg5rcLtUs9caqE4lQVIlw-D7zA4&callback=initMap"></script>
    
   
    <!--<img src="Pictures/AppuMartTextoBorde.png">-->
</body>
</html>


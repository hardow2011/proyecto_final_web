<!DOCTYPE html>
<html lang="en" manifest="/templates/manifest.appcache">
<#include "principal.ftl">
<#macro page_body>
    <script>

        var listaRegistrosLocales = JSON.parse(localStorage.getItem("listaRegistrosLocales") || "[]");
        console.log(listaRegistrosLocales);

        $(document).ready(function(){

            var webSocket;
            var tiempoReconectar = 3000;

            console.info("Iniciando Jquery -  Ejemplo WebServices");

            var tbl=$("<table/>").attr("id","mytable");
            $("#div1").append(tbl);
            for(var i=0;i<listaRegistrosLocales.length;i++)
            {
                var tri="<tr>";
                var td1="<td>"+listaRegistrosLocales[i]["nombre"]+"</td>";
                var td5="<td>"+listaRegistrosLocales[i]["nivelEscolar"]+"</td>";
                var td2="<td>"+listaRegistrosLocales[i]["latitud"]+"</td>";
                var td3="<td>"+listaRegistrosLocales[i]["longitud"]+"</td>";
                var td4="<td><button class=\"btn btn-danger\" onclick=\"eliminarindice("+i+")\";>Eliminar</button></td>";
                var trf="</tr>";
                var tdfoto="<td colspan=\"2\"><img width=275px; width=270px; src=\""+listaRegistrosLocales[i]["fotoBase64"]+"\" alt=\"No hay foto\"></td>";

            $("#mytable").append(tri+td1+td5+td2+td3+td4+trf+tri+tdfoto+trf); 

            }

            conectar();

        });

        function eliminarindice(i) {
            listaRegistrosLocales.splice(i, 1);
            localStorage.setItem("listaRegistrosLocales", JSON.stringify(listaRegistrosLocales));
            window.location.reload();
        }

        function beforeSubmit() {

            for(i = 0; i < listaRegistrosLocales.length; i++){
                console.log("json message length: " + JSON.stringify(listaRegistrosLocales[i]).length);
                <#--  webSocket.send(JSON.stringify(listaRegistrosLocales[i]));  -->
            }

            localStorage.removeItem("listaRegistrosLocales");
            <#--  window.location.reload();  -->
        }

        function recibirInformacionServidor(mensaje){
            console.log("Recibiendo del servidor: "+mensaje.data)
            $("#mensajeServidor").append(mensaje.data);
        }

        function conectar() {
            webSocket = new WebSocket("wss://" + location.hostname + ":" + location.port + "/enviarqueue");

            //indicando los eventos:
            webSocket.onmessage = function(data){recibirInformacionServidor(data);};
            webSocket.onopen  = function(e){
                console.log("Conectado - status "+this.readyState);
                document.getElementById("inputbuttonsubmit").disabled = false;
            };
            webSocket.onclose = function(e){
                console.log("Desconectado - status "+this.readyState);
                document.getElementById("inputbuttonsubmit").disabled = true;
            };
        }

        function verificarConexion(){
            if(!webSocket || webSocket.readyState == 3){
              conectar();
            }
        }

        setInterval(verificarConexion, 3000); //para reconectar.

    </script>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
            <br>

            <a href="/formulario/crear" class="btn btn-primary">Nuevo Formulario</a>
            <br>
            <div class="table-responsive"> 
                <table id="mytable" class="table table-striped">
                    <tr>
                        <th>Nombre</th>
                        <th>Nivel Escolar</th>
                        <th>Latitud</th> 
                        <th>Longitud</th>
                        <th></th>
                    </tr>
                </table>
            </div>
            <form id="formQueue" enctype="application/x-www-form-urlencoded" method="post" action="/formulario/crear" >
                <input type="hidden" id="inputListaFormularios" name="listaFormularios"/>
            <button id="inputbuttonsubmit" type="button" onclick="beforeSubmit();" class="btn btn-primary">Enviar formularios</button>
        </div>
    </div>
</#macro>
</html>
<@display_page/>
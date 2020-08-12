<!DOCTYPE html>
<#include "principal.ftl">
<#macro page_body>
    <script>

        var listaRegistrosLocales = JSON.parse(localStorage.getItem("listaRegistrosLocales") || "[]");
        console.log(listaRegistrosLocales);

        $(document).ready(function(){

            var tbl=$("<table/>").attr("id","mytable");
            $("#div1").append(tbl);
            for(var i=0;i<listaRegistrosLocales.length;i++)
            {
                var tr="<tr>";
                var td1="<td>"+listaRegistrosLocales[i]["nombre"]+"</td>";
                var td5="<td>"+listaRegistrosLocales[i]["nivelEscolar"]+"</td>";
                var td2="<td>"+listaRegistrosLocales[i]["latitud"]+"</td>";
                var td3="<td>"+listaRegistrosLocales[i]["longitud"]+"</td>";
                var td4="<td><button class=\"btn btn-danger\" onclick=\"eliminarindice("+i+")\";>Eliminar</button></td></tr>";

            $("#mytable").append(tr+td1+td5+td2+td3+td4); 

            }

        });

        function eliminarindice(i) {
            listaRegistrosLocales.splice(i, 1);
            localStorage.setItem("listaRegistrosLocales", JSON.stringify(listaRegistrosLocales));
            window.location.reload();
        }

        function beforeSubmit() {
            document.getElementById("inputListaFormularios").value = JSON.stringify(listaRegistrosLocales);
            document.getElementById('formQueue').submit();
            localStorage.removeItem("listaRegistrosLocales");

        }

    </script>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
            <br>

            <a href="/formulario/crear" class="btn btn-primary">Nuevo Formulario</a>
            <br>
            <table id="mytable" class="table table-striped">
                <tr>
                    <th>Nombre</th>
                    <th>Nivel Escolar</th>
                    <th>Latitud</th> 
                    <th>Longitud</th>
                    <th></th>
                </tr>
            </table>
            <form id="formQueue" enctype="application/x-www-form-urlencoded" method="post" action="/formulario/crear" >
                <input type="hidden" id="inputListaFormularios" name="listaFormularios"/>
                <input type="hidden" value="12345" id="idInputPrueba" name="inputPrueba"/>
            <button type="button" onclick="beforeSubmit();" class="btn btn-primary">Enviar formularios</button>
        </div>
    </div>
</#macro>
<@display_page/>
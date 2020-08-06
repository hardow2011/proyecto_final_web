<#include "principal.ftl">
<#macro page_body>
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

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
                var td2="<td>"+listaRegistrosLocales[i]["latitud"]+"</td>";
                var td3="<td>"+listaRegistrosLocales[i]["longitud"]+"</td>";
                var td4="<td><button onclick=\"eliminarindice("+i+")\";>Eliminar</button></td></tr>";

            $("#mytable").append(tr+td1+td2+td3+td4); 

            }

        });

        function eliminarindice(i) {
            listaRegistrosLocales.splice(i, 1);
            localStorage.setItem("listaRegistrosLocales", JSON.stringify(listaRegistrosLocales));
            window.location.reload();
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
                    <th>Latitud</th> 
                    <th>Longitud</th>
                    <th></th>
                </tr>
            </table>
        </div>
    </div>
</#macro>
<@display_page/>
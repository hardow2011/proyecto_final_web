<#include "principal.ftl">
<!DOCTYPE html>
<head>
    <title>${titulo}</title>
</head>

<#macro page_body>
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <script>
    function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
    }

    function showPosition(position) {
    document.getElementById("latitude").value = position.coords.latitude;
    document.getElementById("longitude").value = position.coords.longitude;
    $('#formFormulario').submit();
    }

    function beforeSubmit() {
    getLocation();
    $('#formFormulario').submit();
    }
    </script>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
        </div>
        <!-- El endpoint que estará procesando el formulario será enviado por el controlador      -->
        <form id="formFormulario" enctype="application/x-www-form-urlencoded" method="post" action=${accion}>
            <input hidden="true" type="text" id="latitude" name="latitude">
            <input hidden="true" type="text" id="longitude" name="longitude">
            <div class="form-group">
                <label for="nombreForm">Nombre Completo</label>
                <input value="<#if producto??> ${producto.nombre} <#else></#if>" type="text"  name="nombre" class="form-control" id="nombreForm">
            </div>
            <div class="form-group">
                <label for="inputNivelEscolar">Nivel escolar</label>
                <select class="form-control" id="inputNivelEscolar" name="nivelEscolar">
                    <option>Básico</option>
                    <option>Medio</option>
                    <option>Grado Universitario</option>
                    <option>Postgrado</option>
                    <option>Doctorado</option>
                </select>
            </div>
            <div class="form-group">
                <label for="nombreForm">Registrado Por</label>
                <input readonly="true" type="text"  name="nombreUsuario" value="${user.nombreUsuario}" class="form-control" id="nombreForm">
            </div>
            <!-- Los botones para la creación del producto -->
            <button type="button" onclick="beforeSubmit();" class="btn btn-primary">Anadir al Queue</button>
            <a href="/tarea2/" class="btn btn-primary">Cancelar</a>
        </form>
    </div>
</#macro>

<@display_page/>
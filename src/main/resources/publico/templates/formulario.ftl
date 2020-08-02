<#include "principal.ftl">
<!DOCTYPE html>
<head>
    <title>${titulo}</title>
</head>

<#macro page_body>
        <div class="container">
            <div class="jumbotron">
                <h1 class="display-4">${titulo}</h1>
            </div>
            <!-- El endpoint que estará procesando el formulario será enviado por el controlador      -->
            <form enctype="application/x-www-form-urlencoded" method="post" action=${accion} >
                <div class="form-group">
                    <label for="matriculaForm">ID</label>
                    <input value="<#if producto??> ${producto.id} <#else></#if>" type="number" name="matricula" class="form-control" id="matriculaForm" aria-describedby="matriculaHelp">
                </div>
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
                    <input value="<#if producto??> ${producto.nombre} <#else></#if>" type="text"  name="nombre" class="form-control" id="nombreForm">
                </div>
                <!-- Los botones para la creación del producto -->
                <button type="submit" class="btn btn-primary">Anadir al Queue</button>
                <a href="/tarea2/" class="btn btn-primary">Cancelar</a>
            </form>
        </div>
</#macro>

<@display_page/>
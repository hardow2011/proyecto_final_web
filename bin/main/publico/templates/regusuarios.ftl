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
                <label for="nombreForm">Nombre de usuario</label>
                <input type="text"  name="nombre" class="form-control" id="nombreForm" required>
            </div>
            <div class="form-group">
                <label for="passwordInput">Contraseña</label>
                <input type="password" id="passwordInput" class="form-control" name="password" required/><br/>
            </div>
            <div class="form-group">
                <#if user??>
                    <#if user.admin>
                        <input type="checkbox" id="esAdmin" name="esAdmin" checked>
                        <label for="esAdmin">Es admin?</label><br>
                    <#else>
                        <input type="checkbox" id="esAdmin" name="esAdmin">
                        <label for="esAdmin">Es admin?</label><br>
                    </#if>
                <#else>
                    <input type="checkbox" id="esAdmin" name="esAdmin">
                    <label for="esAdmin">Es admin?</label><br>
                </#if>
            </div>
            <!-- Los botones para la creación del producto -->
            <button type="submit" class="btn btn-primary">Crear</button>
            <a href="/usuarios" class="btn btn-primary">Cancelar</a>
        </form>
    </div>
</#macro>

<@display_page/>
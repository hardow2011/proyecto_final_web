<#include "principal.ftl">
<#macro page_body>
    <br>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
            <br>

            <a href="/parcial2/crearusuario" class="btn btn-primary">Nuevo Usuario</a>
            <br>
            <table class="table table-striped">
                <br>
                <thead>
                <tr>
                    <th scope="col" style="width:25%">ID</th>
                    <th scope="col" style="width:25%">Username</th>
                    <th scope="col" style="width:25%">Rol</th>
                    <th scope="col" style="width:25%">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <#list lista as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.nombre}</td>
                        <td>${user.rol}</td>
                        <td>
                            <a class="btn btn-secondary btn-sm" href="/parcial2/editar/${user.id}">Editar</a>
                            <a class="btn btn-danger btn-sm" href="/parcial2/eliminar/${user.id}">Eliminar</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</#macro>
<@display_page/>
<#include "principal.ftl">
<#macro page_body>
    <br>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
            <br>

            <a href="/usuarios/crear" class="btn btn-primary">Nuevo Usuario</a>
            <br>
            <table class="table table-striped">
                <br>
                <thead>
                <tr>
                    <th scope="col" style="width:25%">ID</th>
                    <th scope="col" style="width:25%">Username</th>
                    <th scope="col" style="width:25%">Es admin?</th>
                    <th scope="col" style="width:25%">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <#foreach user in lista>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.nombreUsuario}</td>
                        <td>
                            <#if user.admin>
                                Sí
                            <#else>
                                No
                            </#if>
                        </td>
                        <td>
                            <a class="btn btn-secondary btn-sm" href="/usuarios/editar/${user.id}">Editar</a>
                            <a class="btn btn-danger btn-sm" href="/usuarios/eliminar/${user.id}">Eliminar</a>
                        </td>
                    </tr>
                </#foreach>
                </tbody>
            </table>
        </div>
    </div>
</#macro>
<@display_page/>
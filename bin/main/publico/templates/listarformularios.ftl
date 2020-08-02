<#include "principal.ftl">
<#macro page_body>
    <br>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
            <br>

            <a href="/parcial2/crearusuario" class="btn btn-primary">Nuevo Formulario</a>
            <br>
            <table class="table table-striped">
                <br>
                <thead>
                <tr>
                    <th scope="col" style="width:25%">#</th>
                    <th scope="col" style="width:25%">Nombre</th>
                    <th scope="col" style="width:25%">Nivel</th>
                    <th scope="col" style="width:25%">Usuario</th>
                    <th scope="col" style="width:25%">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <#list lista as reg>
                    <tr>
                        <td>${reg.id}</td>
                        <td>${reg.nombre}</td>
                        <td>${reg.nivel}</td>
                        <td>${reg.usuario}</td>
                        <td>
                            <a class="btn btn-secondary btn-sm" href="/parcial2/editar/${reg.id}">Editar</a>
                            <a class="btn btn-danger btn-sm" href="/parcial2/eliminar/${reg.id}">Eliminar</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</#macro>
<@display_page/>
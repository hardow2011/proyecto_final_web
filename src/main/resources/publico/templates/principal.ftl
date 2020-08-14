<#macro page_head>
    <title>No title in page_head</title>
</#macro>

<#macro page_body>
    <h1>No page_body</h1>
</#macro>

<#macro display_page>
    <!doctype html>
    <!-- Creando el fragmento en la plantilla-->
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../css/bootstrap.css">
        <script src="../js/jquery-3.5.1.js"></script>
        <script src="../js/popper.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <title>Plantilla Crud Tradicional</title>
        <@page_head/>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08" >
            <ul class="navbar-nav">

                <li class="nav-item active">
                    <a class="navbar-brand" href="#">PARCIAL 2 </a>
                </li>
                <#if user??>
                    <#if user.admin>
                        <li class="nav-item">
                            <a class="nav-link" href="/formulario/crear">FORMULARIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/formulario/queue">QUEUE</a>
                            <#--  <a class="nav-link" href="/templates/listarformularios.ftl">QUEUEPRUEBA</a>  -->
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/formulario/mapa">MAPA/Lista de formularios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/usuarios/">LISTAR USUARIOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/usuarios/crear">CREAR USUARIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">LOGOUT: ${user.nombreUsuario}</a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/formulario/crear">FORMULARIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/formulario/queue">QUEUE</a>
                            <#--  <a class="nav-link" href="/templates/listarformularios.ftl">QUEUEPRUEBA2</a>  -->
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">LOGOUT: ${user.nombreUsuario}</a>
                        </li>
                    </#if>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">LOGIN</a>
                    </li>
                </#if>

            </ul>
        </div>
    </nav>


    <@page_body/>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    </body>
    </html>
</#macro>
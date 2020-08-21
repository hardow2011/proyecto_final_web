<!DOCTYPE html>

<html lang="en" manifest="/templates/manifest.appcache">
<#include "principal.ftl">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titulo}</title>
</head>
<#macro page_body>
    <link rel="stylesheet" href="../css/material-icons.css">
    <link rel="stylesheet" href="../css/webcam-demo.css">
    <link rel="stylesheet" href="../css/my-style.css">
    <script src="../js/jquery-3.5.1.js"></script>
    <script src="../js/webcam-easy.js"></script>
    <script>
    function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
    }

    function showPosition(position) {
    document.getElementById("inputLatitud").value = position.coords.latitude;
    document.getElementById("inputLongitud").value = position.coords.longitude;
    
    var listaRegistrosLocales = JSON.parse(localStorage.getItem("listaRegistrosLocales") || "[]");
    var registro = {nombre:document.getElementById("inputNombrePersona").value,nivelEscolar:document.getElementById("inputNivelEscolar").value,latitud:document.getElementById("inputLatitud").value,longitud:document.getElementById("inputLongitud").value,usuario:null,fotoAlmacenada:localStorage.getItem("fotoAlmacenada")};
    listaRegistrosLocales.push(registro);

    localStorage.setItem("listaRegistrosLocales", JSON.stringify(listaRegistrosLocales));
    console.log(listaRegistrosLocales);

    let divFotoAlmacenada = document.getElementById("div-foto-almacenada");
    divFotoAlmacenada.innerHTML = ""; 

    localStorage.removeItem("fotoAlmacenada");

    }

    function beforeSubmit() {
    getLocation();
    }

    </script>

    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
        </div>
        <form id="formFormulario" enctype="application/x-www-form-urlencoded" method="post" action=${accion}>
            <input hidden="true" type="text" id="inputLatitud" name="latitud">
            <input hidden="true" type="text" id="inputLongitud" name="longitud">
            <div class="form-group">
                <label for="inputNombrePersona">Nombre Completo</label>
                <input type="text"  name="nombrePersona" class="form-control" id="inputNombrePersona">
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
                <label for="inputNombreUsuario">Registrado Por</label>
                <input readonly="true" type="text"  name="nombreUsuario" value="${user.nombreUsuario}" class="form-control" id="inputNombreUsuario">
            </div>
            <div class="form-group">
                <div class="form-control webcam-start center-text" id="webcam-control">
                    <label class="form-switch">
                    <input type="checkbox" id="webcam-switch">
                    <i></i>
                    <span id="webcam-caption">Encender cámara</span>
                    </label>
                    <a id="cameraFlip" class="btn d-none"></a>               
                </div>                  
                <video id="webcam" autoplay playsinline width="100" height="100"></video>
                <canvas id="canvas" class="d-none"></canvas>
                <audio id="snapSound" src="../audio/snap.wav" preload = "auto"></audio>
                <div class="center-text">
                    <a id="take-photo" title="Take Photo"><i class="material-icons btn">camera_alt</i></a>
                    <#--  <a href="#" id="download-photo" download="selfie.png" target="_blank" title="Save Photo" class="d-none"><i class="material-icons">file_download</i></a>    -->
                </div>
            </div>
            <div id="div-foto-almacenada" class="form-group">
            </div>
            <div class="last-buttons-div-container">
                <div class="col-sm-12">
                    <button type="button" onclick="beforeSubmit();" class="btn btn-primary">Anadir al Queue</button>
                </div>
                <div class="col-sm-12">
                    <a href="/tarea2/" class="btn btn-primary">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="../js/app.js"></script>
</#macro>
</html>

<@display_page/>
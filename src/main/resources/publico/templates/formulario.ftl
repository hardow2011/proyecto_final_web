<!DOCTYPE html>

<html lang="en" manifest="/templates/manifest.appcache">
<#include "principal.ftl">
<head>
    <title>${titulo}</title>
</head>
<#macro page_body>
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
    var registro = {nombre:document.getElementById("inputNombrePersona").value,nivelEscolar:document.getElementById("inputNivelEscolar").value,latitud:document.getElementById("inputLatitud").value,longitud:document.getElementById("inputLongitud").value,usuario:null};
    listaRegistrosLocales.push(registro);

    localStorage.setItem("listaRegistrosLocales", JSON.stringify(listaRegistrosLocales));
    console.log(listaRegistrosLocales);

    let picture = webcam.snap();
    document.querySelector('#download-photo').href = picture;

    window.location.reload();

    }

    function beforeSubmit() {
    getLocation();
    }
    </script>
    <script>

        $(document).ready(function(){

            const webcamElement = document.getElementById('webcam');
            const canvasElement = document.getElementById('canvas');
            const snapSoundElement = document.getElementById('snapSound');
            const webcam = new Webcam(webcamElement, 'user', canvasElement, snapSoundElement);

            webcam.start()
            .then(result =>{
                console.log("webcam started");
            })
            .catch(err => {
                console.log(err);
            });

        });
    </script>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo}</h1>
        </div>
        <!-- El endpoint que estará procesando el formulario será enviado por el controlador      -->
        <form id="formFormulario" enctype="application/x-www-form-urlencoded" method="post" action=${accion}>
            <input hidden="true" type="text" id="inputLatitud" name="latitud">
            <input hidden="true" type="text" id="inputLongitud" name="longitud">
            <div class="form-group">
                <video id="webcam" autoplay playsinline width="640" height="480"></video>
                <canvas id="canvas" class="d-none"></canvas>
                <audio id="snapSound" src="audio/snap.wav" preload = "auto"></audio>
            </div>
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
            <!-- Los botones para la creación del producto -->
            <button type="button" onclick="beforeSubmit();" class="btn btn-primary">Anadir al Queue</button>
            <a href="/tarea2/" class="btn btn-primary">Cancelar</a>
        </form>
    </div>
</#macro>
</html>

<@display_page/>
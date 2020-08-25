<#include "principal.ftl">

<#macro page_head>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDmO0JHOHAXY2C3Ud49KbMSwFf3APep1Ow&callback=initMap&libraries=&v=weekly"
            defer
    ></script>
    <style type="text/css">
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }

        /* Optional: Makes the sample page fill the window. */
        html,
        body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        #coords {
            background-color: black;
            color: white;
            padding: 5px;
        }
    </style>

    <script>
        const ubicaciones = [];
        function add_location() {
            <#list formularios as fomu>

            ubicaciones.push(
                {
                    type: "Feature",
                    geometry: {
                        type: "Point",
                        coordinates: [${fomu.longitud}, ${fomu.latitud}]
                    },
                    properties: {
                        name: "${fomu.nombre}"
                    }
                }
            );
            </#list>
        }
        function initMap() {
            const rd = new google.maps.LatLng(18.88, -70.27);
            const map = new google.maps.Map(document.getElementById("map"), {
                center: rd,
                zoom: 8
            });
            const coordInfoWindow = new google.maps.InfoWindow();
            coordInfoWindow.open(map);

            map.data.setStyle(feature => {
                return {
                    title: feature.getProperty("name"),
                    optimized: false
                };
            });
            const cities = {
                type: "FeatureCollection",
                features: ubicaciones
            };

            add_location();

            map.data.addGeoJson(cities);

        }




        "use strict";

    </script>
</#macro>

<#macro page_body>
    <br>
    <br>
    <main role="main" class="container">
        <br><h1 class="text-center">Localizaciones de los Registros</h1><br>
        <div class="jumbotron" style="height: 600px;">

            <div id="map"></div>
            <div id="coords"></div>
        </div>
        <div class="container jumbotron-fluid">
            <br><h1 class="text-center">${title}</h1><br>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light text-center">
                    <th>Nombre</th>
                    <th>Nivel Escolar</th>
                    <th>Longitud</th>
                    <th>Latitud</th>
                    <th>Registrado por</th>
                    <th></th>
                    </thead>
                    <tbody class="text-center">
                    <#list formularios as formu>
                        <tr>
                            <td>${formu.nombre}</td>
                            <td>${formu.nivelEscolar}</td>
                            <td>${formu.longitud}</td>
                            <td>${formu.latitud}</td>
                            <td>${formu.usuario.nombreUsuario}</td>
                            <td>
                                <a class="btn btn-danger btn-sm" href="/formulario/eliminar/${formu.id}">Eliminar</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><img width=275px; width=270px; src="${(formu.foto.fotoBase64)!}" alt="No hay foto"></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </main>

</#macro>

<@display_page/>


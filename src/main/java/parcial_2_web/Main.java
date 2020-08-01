/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package parcial_2_web;

import io.javalin.Javalin;
import parcial_2_web.entidades.Usuario;
import parcial_2_web.services.BootStrapServices;
import parcial_2_web.services.UsuarioServices;
import parcial_2_web.controladores.TemplateController;

public class Main {

    public static void main(String[] args) {
        
        // Iniciando la base de datos.
        BootStrapServices.getInstancia().init();

        Javalin app = Javalin.create(config -> {
            if (UsuarioServices.getInstancia().listar().size() == 0) {
                Usuario primerAdmin = new Usuario("admin", "admin");
                primerAdmin.setAdmin(true);
                UsuarioServices.getInstancia()
                        .crear(primerAdmin);
            }
            // Si la carpeta /publico no tiene ningún archivo, el build de Gradle fallará.
             config.addStaticFiles("/publico");
            // config.registerPlugin(new RouteOverviewPlugin("/rutas"));
        }).start();

        //Manejadores de rutas
        new TemplateController(app).aplicarRutas();
    }
}

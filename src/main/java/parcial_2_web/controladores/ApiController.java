package parcial_2_web.controladores;

import io.javalin.Javalin;
import parcial_2_web.services.UsuarioServices;
import parcial_2_web.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiController extends BaseControlador {

    public ApiController(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/api", () -> {

                path("/usuarios", () -> {

                    get("/", ctx -> {
                        ctx.json(UsuarioServices.getInstancia().listar());
                    });
                    
                });

            });
        });

    }
    
    
}
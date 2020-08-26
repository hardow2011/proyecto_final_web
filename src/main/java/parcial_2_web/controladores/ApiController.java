package parcial_2_web.controladores;

import io.javalin.Javalin;
import parcial_2_web.entidades.Registro;
import parcial_2_web.services.UsuarioServices;
import parcial_2_web.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

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
                        ctx.json(UsuarioServices.getInstancia().listarSinRelaciones());
                    });

                    get("/:idUsuario/formularios", ctx -> {
                        int idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));

                        ctx.json(UsuarioServices.getInstancia().find(idUsuario).getListaRegistros());
                    });

                    post("/", ctx -> {
                        System.out.println("\n\n\n");
                        System.out.println(ctx.body());
                        System.out.println("\n");

                        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                        JSONObject jsonObject = new JSONObject(ctx.body());

                        Registro registro = mapper.readValue(ctx.body(), Registro.class);
                        System.out.println(registro.getNombre()+" "+registro.getNivelEscolar()+" "+registro.getLatitud()+" "+registro.getLongitud()+""+jsonObject.getInt("user_id"));
                        System.out.println("\n\n\n");

                    });
                    
                });

            });
        });

    }
    
    
}
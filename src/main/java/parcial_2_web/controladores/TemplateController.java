package parcial_2_web.controladores;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.jasypt.util.password.StrongPasswordEncryptor;
import parcial_2_web.entidades.Usuario;
import parcial_2_web.entidades.Registro;
import parcial_2_web.services.RegistroServices;

import java.math.BigDecimal;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class TemplateController {

    private Javalin app;
    public TemplateController(Javalin app){
        this.app = app;
    }

    RegistroServices registro = RegistroServices.getInstancia();

    private void registroPlantillas() {
        JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");
    }

    public void aplicarRutas() {
        app.routes(() -> {

            path("/parcial2", () -> {
                get("/formulario", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Formulario");
                    contexto.put("admin", true);
                    contexto.put("accion", "parcial2/addcola");
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/publico/templates/formulario.ftl", contexto);
                });

                get("/crearusuario", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Crear Usuario");
                    contexto.put("admin", true);
                    contexto.put("accion", "parcial2/addcola");
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/publico/templates/regusuarios.ftl", contexto);
                });

                get("/listaru", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    List<Usuario> lista = new ArrayList<>();
                    contexto.put("titulo", "Listado de Usuarios");
                    contexto.put("admin", true);
                    contexto.put("lista",lista);
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));

                    ctx.render("/publico/templates/listarusuarios.ftl", contexto);
                });

                get("/listarf", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    List<Registro> lista = new ArrayList<>();
                    contexto.put("titulo", "Listado de Formularios Llenados");
                    contexto.put("admin", true);
                    contexto.put("lista",lista);
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/publico/templates/listarformularios.ftl", contexto);
                });

                get("/queue", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    List<Registro> lista = new ArrayList<>();
                    contexto.put("titulo", "Cola de formularios");
                    contexto.put("admin", true);
                    contexto.put("lista",lista);
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/publico/templates/colaregistros.ftl", contexto);
                });
            });
        });
    }
}

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

public class FormularioController {

    private Javalin app;
    public FormularioController(Javalin app){
        this.app = app;
    }

    RegistroServices registro = RegistroServices.getInstancia();

    private void registroPlantillas() {
        JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");
    }

    public void aplicarRutas() {
        app.routes(() -> {

            path("/formulario", () -> {

                get("/", ctx -> {
                    ctx.redirect("/formulario/crear");
                });

                get("/crear", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Formulario");
                    contexto.put("admin", true);
                    contexto.put("accion", "formulario/crear");
                    contexto.put("user", ctx.sessionAttribute("user"));
                    ctx.render("/publico/templates/formulario.ftl", contexto);
                });


            });
        });
    }
}

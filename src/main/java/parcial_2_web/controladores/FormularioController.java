package parcial_2_web.controladores;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.jasypt.util.password.StrongPasswordEncryptor;
import parcial_2_web.entidades.Usuario;
import parcial_2_web.entidades.Registro;
import parcial_2_web.services.RegistroServices;
import parcial_2_web.services.UsuarioServices;

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

                                // Antes de acceder a la ruta...
                before(ctx -> {
                    // Si hay una cookie recuerdame...
                    if(Objects.nonNull(ctx.cookie("recuerdame"))){
                        // Si no hay atributo de sesión usuario...
                        if(ctx.sessionAttribute("user") == null){
                            // Crear el atributo de sesión que va a contener el usuario que tenga el ID igual al valor de la cookie recuerdame.
                            ctx.sessionAttribute("user", UsuarioServices.getInstancia().find(Integer.parseInt(ctx.cookie("recuerdame"))));
                        }
                    }
                    // Si el usuario no es admin, se redirige al form.
                    if((Usuario) ctx.sessionAttribute("user") == null) {
                        ctx.redirect("/login");
                    }
                });

                get("/", ctx -> {
                    ctx.redirect("/formulario/crear");
                });

                get("/crear", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Formulario");
                    contexto.put("admin", true);
                    contexto.put("accion", "/formulario/crear");
                    contexto.put("user", ctx.sessionAttribute("user"));
                    ctx.render("/publico/templates/formulario.ftl", contexto);
                });

                post("/crear", ctx -> {
                    // String nonbreFormulario = ctx.formParam("nombrePersona");
                    // float latitud = Float.parseFloat(ctx.formParam("latitud"));
                    // float longitud = Float.parseFloat(ctx.formParam("longitud"));
                    // String nivelEscolar = ctx.formParam("nivelEscolar");

                    // Registro registro = new Registro(nonbreFormulario, nivelEscolar, latitud, longitud);
                    // registro.setUsuario(ctx.sessionAttribute("user"));

                    // RegistroServices.getInstancia().crear(registro);

                    // ctx.redirect("/formulario");
                });

                get("/testlocalstorage", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    ctx.render("/publico/templates/testlocalstorage.ftl", contexto);
                });

                get("/queue", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("admin", true);
                    contexto.put("user", ctx.sessionAttribute("user"));
                    contexto.put("titulo", "Formularios en queue");
                    ctx.render("/publico/templates/listarformularios.ftl", contexto);
                });


            });
        });
    }
}

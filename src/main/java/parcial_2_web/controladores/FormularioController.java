package parcial_2_web.controladores;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.jasypt.util.password.StrongPasswordEncryptor;
import parcial_2_web.entidades.*;
import parcial_2_web.services.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;

import java.lang.reflect.Array;
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
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("accion", "/formulario/crear");
                    contexto.put("user", ctx.sessionAttribute("user"));
                    ctx.render("/publico/templates/formulario.ftl", contexto);
                });

                post("/crear", ctx -> {
                    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    String json = ctx.formParam("pruebaListaFormularios");
                    List<Registro> registros = Arrays.asList(mapper.readValue(json, Registro[].class));
                    List<Foto> fotos = Arrays.asList(mapper.readValue(json, Foto[].class));

                    for(int i = 0; i < registros.size(); i++){
                        Registro registro = new Registro(registros.get(i).getNombre(), registros.get(i).getNivelEscolar(), registros.get(i).getLatitud(), registros.get(i).getLongitud());
                        registro.setUsuario(UsuarioServices.getInstancia().find(((Usuario) ctx.sessionAttribute("user")).getId()));
                        RegistroServices.getInstancia().crear(registro);
                        Foto foto = new Foto(fotos.get(i).getFotoBase64(), Iterables.getLast(RegistroServices.getInstancia().listar()));
                        FotoServices.getInstancia().crear(foto);
                        System.out.println(foto.getFotoBase64());
                    }

                    ctx.redirect("/formulario");

                });

                get("/testlocalstorage", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    ctx.render("/publico/templates/testlocalstorage.ftl", contexto);
                });

                get("/queue", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("user", ctx.sessionAttribute("user"));
                    contexto.put("titulo", "Formularios en queue");
                    ctx.render("/publico/templates/listarformularios.ftl", contexto);
                });

                get("/mapa", ctx -> {

                    List<Registro> forms = RegistroServices.getInstancia().listar();
                    Map<String, Object> contexto = new HashMap<>();
                    // System.out.println("\n\n\n\n");
                    // for (int j = 0; j < forms.size(); j++) {
                    //     System.out.println(forms.get(j).getFoto().getFotoBase64().substring(0, 6));
                    // }
                    // System.out.println("\n\n\n\n");
                    contexto.put("title", "Listado Formularios Registrados Por el Usuario");
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("user", ctx.sessionAttribute("user"));
                    contexto.put("formularios", forms);
                    ctx.render("/publico/templates/mapa.ftl", contexto);

                });

                get("/eliminar/:idFormulario", ctx -> {

                    int id = Integer.parseInt(ctx.pathParam("idFormulario"));
                    RegistroServices.getInstancia().eliminar(id);
                    ctx.redirect("/formulario/mapa");

                });

            });
        });

        app.ws("/enviarqueue", ws -> {

            ws.onConnect(ctx -> {
                System.out.println("Conexión Iniciada - "+ctx.getSessionId());
            });

            ws.onClose(ctx -> {
                System.out.println("Conexión Cerrada - "+ctx.getSessionId());
            });

            ws.onMessage(ctx -> {

                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                String json = ctx.message();
                List<Registro> registros = Arrays.asList(mapper.readValue(json, Registro[].class));
                List<Foto> fotos = Arrays.asList(mapper.readValue(json, Foto[].class));

                for(int i = 0; i < registros.size(); i++){
                    Registro registro = new Registro(registros.get(i).getNombre(), registros.get(i).getNivelEscolar(), registros.get(i).getLatitud(), registros.get(i).getLongitud());
                    registro.setUsuario(UsuarioServices.getInstancia().find(((Usuario) ctx.sessionAttribute("user")).getId()));
                    RegistroServices.getInstancia().crear(registro);
                    Foto foto = new Foto(fotos.get(i).getFotoBase64(), Iterables.getLast(RegistroServices.getInstancia().listar()));
                    FotoServices.getInstancia().crear(foto);
                }

                // ctx.redirect("/formulario");
                
            });

        });
    }
}

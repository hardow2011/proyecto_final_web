package parcial_2_web.controladores;

import io.javalin.Javalin;
import parcial_2_web.entidades.Usuario;
import parcial_2_web.services.UsuarioServices;
import parcial_2_web.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SesionController extends BaseControlador {

    public SesionController(Javalin app) {
        super(app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void aplicarRutas() {
        
        app.routes(() -> {
            
            path("/", () -> {

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
                });

                get("/", ctx -> {
                    // Si no hay atributo de sesión de usuario y se accede a la ruta "/"...
                    if(ctx.sessionAttribute("user") == null){
                        // Se redirige al login...
                        ctx.redirect("/login");
                    }else{
                        // De otro modo, se redirige al formulario.
                        ctx.redirect("/formulario");
                    }
                });

                get("/login", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Login");
                    contexto.put("admin", false);
                    contexto.put("accion", "/login");
                    ctx.render("/publico/templates/login.ftl", contexto);
                });

                post("/login", ctx -> {
                    String nombreUsuario = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    Boolean existe = false;

                    List<Usuario> listaUsuarios = UsuarioServices.getInstancia().listar();

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        if(listaUsuarios.get(i).getNombreUsuario().equals(nombreUsuario) && listaUsuarios.get(i).getPassword().equals(password)){
                            ctx.sessionAttribute("user", listaUsuarios.get(i));
                            // ctx.cookie("recuerdame", String.valueOf(usuarioServices.getListaUsuarios().get(i).getId()), 604800);
                            existe = true;
                            // Si el campo recuerdame no es nulo, crear una cookie llamada recuerdame con el nombre de usuario
                            if(Objects.nonNull(ctx.formParam("recuerdame"))){
                                ctx.cookie("recuerdame", String.valueOf(listaUsuarios.get(i).getId()), 604800);
                            }
        
                            ctx.redirect("/formulario");
                            break;
                        }
                    }

                    if(existe == false){
                        ctx.redirect("/login");
                    }

                });

                get("/logout", ctx -> {
                    // Hacer nulo el atributo de sesión del usuario en caso de logout
                    ctx.req.getSession().setAttribute("user", null);
                    ctx.cookie("recuerdame", "null", 0);
                    ctx.redirect("/login");
                });
            });
        });

    }
    
}
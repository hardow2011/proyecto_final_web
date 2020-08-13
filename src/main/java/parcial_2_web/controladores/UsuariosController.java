package parcial_2_web.controladores;

import io.javalin.Javalin;
import parcial_2_web.util.BaseControlador;
import parcial_2_web.entidades.Usuario;
import parcial_2_web.services.UsuarioServices;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UsuariosController extends BaseControlador {

    public UsuariosController(Javalin app) {
        super(app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void aplicarRutas() {
        // TODO Auto-generated method stub

        app.routes(() -> {
            path("/usuarios", () ->{

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
                    if(!((Usuario) ctx.sessionAttribute("user")).getAdmin()) {
                        ctx.redirect("/formulario");
                    }
                });

                get("/", ctx -> {
                    if(ctx.sessionAttribute("user") == null){
                        ctx.redirect("/login");
                    }else{
                        ctx.redirect("/usuarios/listar");
                    }
                });

                get("/listar", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    List<Usuario> lista = UsuarioServices.getInstancia().listar();
                    contexto.put("titulo", "Listado de Usuarios");
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("lista",lista);
                    contexto.put("user", ctx.sessionAttribute("user"));
                    ctx.render("/publico/templates/listarusuarios.ftl", contexto);
                });

                get("/crear", ctx -> {
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("titulo", "Crear Usuario");
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("accion", "/usuarios/crear");
                    contexto.put("user", ctx.sessionAttribute("user"));
                    ctx.render("/publico/templates/regusuarios.ftl", contexto);
                });

                post("/crear", ctx -> {
                    String nombreUsuario = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    Usuario usuario = new Usuario(nombreUsuario, password);

                    if(Objects.nonNull(ctx.formParam("esAdmin"))){
                        usuario.setAdmin(true);
                    }

                    UsuarioServices.getInstancia().crear(usuario);

                    ctx.redirect("/usuarios");
                });

                get("/editar/:idUsuario", ctx -> {

                    Usuario usuario = UsuarioServices.getInstancia().find(Integer.parseInt(ctx.pathParam("idUsuario")));

                    Map<String, Object> contexto = new HashMap<>();

                    contexto.put("user", usuario);

                    contexto.put("titulo", "Editar usuario");
                    contexto.put("admin", ((Usuario) ctx.sessionAttribute("user")).getAdmin());
                    contexto.put("accion", "/usuarios/editar");
                    contexto.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/publico/templates/regusuarios.ftl", contexto);
                });

                post("/editar/", ctx -> {
                    
                    Usuario usuario = UsuarioServices.getInstancia().find(Integer.parseInt(ctx.formParam("idUsuario")));

                    String nombreUsuario = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setPassword(password);

                    if(Objects.nonNull(ctx.formParam("esAdmin"))){
                        usuario.setAdmin(true);
                    }else{
                        usuario.setAdmin(false);
                    }

                    UsuarioServices.getInstancia().editar(usuario);

                    // Redireccionar a la lista

                    ctx.redirect("/usuarios/");
                });

                get("/eliminar/:idUsuario", ctx -> {
                    int id = Integer.parseInt(ctx.pathParam("idUsuario"));

                    UsuarioServices.getInstancia().eliminar(id);
                    
                    ctx.redirect("/usuarios");
                });

            });
        });

    }
    
}
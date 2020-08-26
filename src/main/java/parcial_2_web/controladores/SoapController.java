package parcial_2_web.controladores;

import io.javalin.Javalin;
import parcial_2_web.soap.UsuarioWebServices;
import parcial_2_web.util.BaseControlador;

import com.sun.net.httpserver.HttpContext;
import io.javalin.Javalin;
import org.eclipse.jetty.http.spi.HttpSpiContextHandler;
import org.eclipse.jetty.http.spi.JettyHttpContext;
import org.eclipse.jetty.http.spi.JettyHttpServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import javax.xml.ws.Endpoint;
import java.lang.reflect.Method;

public class SoapController extends BaseControlador {

    public SoapController(Javalin app) {
        super(app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void aplicarRutas() {
        Server server = app.server().server();
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        server.setHandler(contextHandlerCollection);

        try {
            HttpContext context = build(server, "/ws");

            //El o los servicios que estoy agrupando en ese contexto
            UsuarioWebServices wsa = new UsuarioWebServices();
            Endpoint endpoint = Endpoint.create(wsa);
            endpoint.publish(context);
            // Para acceder al wsdl en http://localhost:9002/ws/UsuarioWebServices?wsdl
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param server
     * @param contextString
     * @return
     * @throws Exception
     */
    private HttpContext build(Server server, String contextString) throws Exception {
        JettyHttpServer jettyHttpServer = new JettyHttpServer(server, true);
        JettyHttpContext ctx = (JettyHttpContext) jettyHttpServer.createContext(contextString);
        
        try {
            Method method = JettyHttpContext.class.getDeclaredMethod("getJettyContextHandler");
            method.setAccessible(true);
            HttpSpiContextHandler contextHandler = (HttpSpiContextHandler) method.invoke(ctx);
            contextHandler.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ctx;
    }
    
}
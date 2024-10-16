package itstep.learning.ioc;
import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.*;
import itstep.learning.servlets.*;

public class WebModule extends ServletModule {
    @Override protected void configureServlets() {
        filter( "/*" ).through( CharsetFilter.class  );
        filter( "/*" ).through( SecurityFilter.class );
        serve( "/"          ).with( HomeServlet.class    );
        serve( "/auth"      ).with( AuthServlet.class    );
        serve( "/storage/*" ).with( StorageServlet.class );
        serve( "/web-xml"   ).with( WebXmlServlet.class  );
        serve( "/shop/category" ).with( CategoryServlet.class );
    }
}
package itstep.learning.ioc;
import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.*;
import itstep.learning.servlets.*;

public class WebModule extends ServletModule {
    @Override protected void configureServlets() {
        filter( "/*" ).through( CharsetFilter.class );
        filter( "/*" ).through( SessionAuthFilter.class );
        filter( "/shop/*" ).through( TokenAuthFilter.class );
        serve( "/"         ).with( HomeServlet.class     );
        serve( "/auth"     ).with( AuthServlet.class     );
        serve( "/file/*"   ).with( DownloadServlet.class );
        serve( "/servlets" ).with( ServletsServlet.class );
        serve( "/signup"   ).with( SignupServlet.class   );
        serve( "/spa"      ).with( SpaServlet.class      );
        serve( "/shop/category" ).with( CategoryServlet.class );
        serve( "/shop/product"  ).with( ProductServlet.class  );
    }
}
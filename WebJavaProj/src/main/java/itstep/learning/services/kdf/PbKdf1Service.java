package itstep.learning.services.kdf;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.services.hash.HashService;

@Singleton public class PbKdf1Service implements KdfService {
    private final static int dkLen = 20;
    private final HashService hashService;

    @Inject public PbKdf1Service(HashService hashService) {
        this.hashService = hashService;
    }
    @Override public String dk( String password, String salt ) {
        int iterationCount = 0, dotPos = salt.lastIndexOf( "." );
        if ( dotPos > 0 ) {
            try { iterationCount = Integer.parseInt( salt.substring( dotPos + 1 ) ); }
            catch ( NumberFormatException ignored ) {}
        }
        if( iterationCount < 1 || iterationCount >= 10 ) iterationCount = 3;

        String t = hashService.hash( password + salt ) ;
        for( int i = 1; i < iterationCount; i++ ) t = hashService.hash( t ) ;

        while( t.length() < dkLen ) t += t;
        return t.substring( 0, dkLen );
    }
}
package itstep.learning;
import java.util.Locale;

public class Bike extends Vehicle implements Trailer {
    String type;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Bike( String name, String type ) {
        super( name );
        this.setType( type );
    }
    @Override public String getInfo() {
        return String.format(Locale.ROOT,"Велосипеда: '%s', тип: '%s'", this.getName(), this.getType());
    }
    @Override public String trailerInfo() { return "Прицеп-люлька"; }
}
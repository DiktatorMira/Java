package itstep.learning;
import java.util.Locale;

public class Truck extends Vehicle implements LargeSized, Trailer {
    private double cargo;
    public double getCargo() { return cargo;  }
    public void setCargo(double cargo) { this.cargo = cargo; }

    public Truck(String name, double cargo) {
        this.cargo = cargo;
        super.setName(name);
    }
    @Override public String trailerInfo() { return "Грузовой прицеп"; }
    @Override public String getInfo() {
        return String.format(Locale.ROOT,"Грузовик: '%s' с грузом %.1f тонн", super.getName(), this.getCargo());
    }
}
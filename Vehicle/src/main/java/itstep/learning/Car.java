package itstep.learning;
import java.util.Locale;

public class Car extends Vehicle {
    private String type;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Car(String name, String type) {
        super(name);
        this.type = type;
    }
    @Override public String getInfo() {
        return String.format(Locale.ROOT, "Автомобиль: '%s', тип: '%s'", this.getName(), this.getType());
    }
}
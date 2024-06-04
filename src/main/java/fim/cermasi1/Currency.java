package fim.cermasi1;

public class Currency {
    //country|currency|quantity|code|course
    private String country;
    private String currency;
    private int quantity;
    private String code;
    private double course;
    Currency(String country, String currency, int quantity, String code, double course){
        this.country = country;
        this.currency = currency;
        this.quantity = quantity;
        this.code = code;
        this.course = course;
    }

    public double getCourse(){
        return course;
    }
    public double getQuantity(){return quantity;}

    // To string will be used in the combobox (it is Country - code)
    @Override
    public String toString() {
        return country + " - " + code;
    }

}

import java.sql.Date;

public class Discounts {
    private Integer id;
    private Integer amount;
    private String description;
    private Integer timeInCompany;


    public Discounts(Integer id, Integer amount, String description, Integer timeInCompany) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.timeInCompany = timeInCompany;
    }

    @Override
    public String toString() {
        return String.format("%s год", timeInCompany.toString());
    }
}

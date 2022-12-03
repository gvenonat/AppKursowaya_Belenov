import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Discount {
    private Integer id;
    private Integer amount;
    private String description;
    private Integer timeInCompany;


    public Discount(Integer id, Integer amount, String description, Integer timeInCompany) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.timeInCompany = timeInCompany;
    }

    public Discount() {
        this.id = -1;
    }

    @Override
    public String toString() {
        if (this.id == -1)
            return String.format("отсутствует");
        return String.format("%s год", timeInCompany.toString());
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTimeInCompany() {
        return timeInCompany;
    }
}

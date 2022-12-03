
import java.sql.Date;

public class Tariff {
    private Integer id;
    private String name;
    private Integer cost;
    private String description;
    private Date start_sales;
    private Date end_sales;

    public Tariff(Integer id, String name, Integer cost, String description, Date start_sales, Date end_sales) {
        this.id = id;
        this.name = name.replace(" ","");
        this.cost = cost;
        this.description = description;
        this.start_sales = start_sales;
        this.end_sales = end_sales;
    }

    @Override
    public String toString() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }

    public Date getEnd_sales() {
        return end_sales;
    }

    public Date getStart_sales() {
        return start_sales;
    }

    public void setEnd_sales(Date end_sales) {
        this.end_sales = end_sales;
    }
}



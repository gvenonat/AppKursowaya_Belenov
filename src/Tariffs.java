import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.SQLException;

@Getter
@Setter
public class Tariffs {
    private Integer id;
    private String name;
    private Integer cost;
    private String description;
    private Date start_sales;
    private Date end_sales;

    public Tariffs(Integer id, String name, Integer cost, String description, Date start_sales, Date end_sales) {
        this.id = id;
        this.name = name.replace("\\s+","");
        this.cost = cost;
        this.description = description;
        this.start_sales = start_sales;
        this.end_sales = end_sales;
    }

    @Override
    public String toString() {
        return name;
    }
}



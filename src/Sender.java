public class Sender {

    private static Integer countSales;
    private static Integer countStd1;
    private static Integer countStd2;
    private static Integer countStd3;

    public Sender(Integer countSales, Integer countStd1, Integer countStd2, Integer countStd3) {
        this.countSales = countSales;
        this.countStd1 = countStd1;
        this.countStd2 = countStd2;
        this.countStd3 = countStd3;
    }
    public Integer getCountSales() {
        return countSales;
    }
    public Integer getCountStd1() {
        return countStd1;
    }
    public Integer getCountStd2() {
        return countStd2;
    }
    public Integer getCountStd3() {
        return countStd3;
    }
}
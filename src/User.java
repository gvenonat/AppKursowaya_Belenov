public class User {

    User(String name, int age, int salary, boolean married) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.married = married;
    }

    public void changeSalary(int x) {
        salary = (salary + x <= 0)? 0: salary + x;
    }

    public String toString() {
        return name;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public String getSalary() {
        return String.valueOf(salary);
    }
    public String getMarried() {
        return (married)? ("married"):("single");
    }

    String name;
    int age;
    int salary;
    boolean married;
}
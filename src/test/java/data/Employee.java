package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Employee {

    private String name;
    private String job;

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }
}

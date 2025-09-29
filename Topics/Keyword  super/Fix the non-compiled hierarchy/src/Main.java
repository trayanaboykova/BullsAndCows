class Employee {

    protected String name;
    protected String birthDate;

    public Employee(String name, String birthDate) {
        this.name = name;          // assign this class' fields
        this.birthDate = birthDate;
    }
}

class RegularEmployee extends Employee {

    protected long salary;
    protected String hireDate;

    public RegularEmployee(String name, String birthDate, long salary, String hireDate) {
        super(name, birthDate);    // call base constructor
        this.salary = salary;      // assign this class' fields
        this.hireDate = hireDate;
    }
}

class ContractEmployee extends Employee {

    protected long payPerHour;
    protected String contractPeriod;

    public ContractEmployee(String name, String birthDate, long payPerHour, String contractPeriod) {
        super(name, birthDate);    // call base constructor
        this.payPerHour = payPerHour;
        this.contractPeriod = contractPeriod;
    }
}

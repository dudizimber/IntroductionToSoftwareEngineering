import javax.activation.UnsupportedDataTypeException;

abstract class Employee {
    String _firstName;
    String _lastName;
    Integer _id;

    Employee(String firstName, String lastName, Integer id) {
        _firstName = firstName;
        _lastName = lastName;
        _id = id;
    }

    Employee() {
        _firstName = "Ploni";
        _lastName = "Almoni";
        _id = 0;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public Integer getId() {
        return _id;
    }

    public void setFirstName(String name) throws UnsupportedDataTypeException {
        _firstName = name;
    }

    public void setLastName(String name) throws UnsupportedDataTypeException {
        _lastName = name;
    }

    public void setId(Integer id) throws UnsupportedDataTypeException {
        _id = id;
    }

    @Override
    public String toString() {
        return "Name: " + _firstName + " " + _lastName + "\nID: " + _id;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this)
            return true;

        if (!(other instanceof Employee))
            return false;

        Employee otherEmployee = (Employee) other;

        if (this.toString() == otherEmployee.toString()) {
            return true;
        } else {
            return false;
        }
    }

    public abstract Float earnings();
}

public class HourlyEmployee extends Employee {

    Integer _hours;
    Float _wage;

    HourlyEmployee(String firstName, String lastName, Integer id, Integer hours, Float wage) {
        super(firstName, lastName, id);
        _hours = hours;
        _wage = wage;
    }

    HourlyEmployee() {
        super();
        _hours = 0;
        _wage = 0f;
    }

    public Float earnings() {
        return _hours * _wage;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public Integer getId() {
        return super.getId();
    }

    public Integer getHours() {
        return _hours;
    }

    public Float getWage() {
        return _wage;
    }

    public void setFirstName(String name) throws UnsupportedDataTypeException {
        if (!(name instanceof String)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected String");
        }
        super.setFirstName(name);
        ;
    }

    public void setLastName(String name) throws UnsupportedDataTypeException {
        if (!(name instanceof String)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected String");
        }
        super.setLastName(name);
    }

    public void setId(Integer id) throws UnsupportedDataTypeException {
        if (!(id instanceof Integer)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Integer");
        }
        super.setId(id);
    }

    public void setHours(Integer hours) throws UnsupportedDataTypeException {
        if (!(hours instanceof Integer)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Integer");
        }
        _hours = hours;
    }

    public void setWage(Float wage) throws UnsupportedDataTypeException {
        if (!(wage instanceof Float)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Float");
        }
        _wage = wage;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHours: " + _hours + "\nWage: " + _wage;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this)
            return true;

        if (other == null || !(other instanceof HourlyEmployee))
            return false;

        HourlyEmployee otherEmployee = (HourlyEmployee) other;

        if (this.toString() == otherEmployee.toString()) {
            return true;
        } else {
            return false;
        }
    }

}

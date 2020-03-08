import javax.activation.UnsupportedDataTypeException;

public class BasePlusComissionEmployee extends ComissionEmployee {
    Float _baseSalary;

    BasePlusComissionEmployee(String firstName, String lastName, Integer id, Integer comission, Float grossSales,
            Float baseSalary) {
        super(firstName, lastName, id, comission, grossSales);
        _baseSalary = baseSalary;
    }

    BasePlusComissionEmployee() {
        super();
        _baseSalary = 0f;
    }

    public Float earnings() {
        return super.earnings() + _baseSalary;
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

    public Integer getComission() {
        return super.getComission();
    }

    public Float getGrossSales() {
        return super.getGrossSales();
    }

    public void setFirstName(String name) throws UnsupportedDataTypeException {
        super.setFirstName(name);
    }

    public void setLastName(String name) throws UnsupportedDataTypeException {
        super.setLastName(name);
    }

    public void setId(Integer id) throws UnsupportedDataTypeException {
        super.setId(id);
    }

    public void setComission(Integer comission) throws UnsupportedDataTypeException {
        super.setComission(comission);
    }

    public void setGrossSales(Float grossSales) throws UnsupportedDataTypeException {
        super.setGrossSales(grossSales);
    }

    public void setBaseSalary(Float baseSalary) throws UnsupportedDataTypeException {
        if (!(baseSalary instanceof Float)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Float");
        }
        _baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\nBase Salary: " + _baseSalary;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this)
            return true;

        if (other == null || !(other instanceof ComissionEmployee))
            return false;

        ComissionEmployee otherEmployee = (ComissionEmployee) other;

        if (this.toString() == otherEmployee.toString()) {
            return true;
        } else {
            return false;
        }
    }

}
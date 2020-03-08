import javax.activation.UnsupportedDataTypeException;

public class ComissionEmployee extends Employee {
    Float _grossSales;
    Integer _comission;

    ComissionEmployee(String firstName, String lastName, Integer id, Integer comission, Float grossSales) {
        super(firstName, lastName, id);
        _comission = comission;
        _grossSales = grossSales;
    }

    ComissionEmployee() {
        super();
        _comission = 0;
        _grossSales = 0f;
    }

    public Float earnings() {
        Float a = (float) _comission / 100;
        return a * _grossSales;
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
        return _comission;
    }

    public Float getGrossSales() {
        return _grossSales;
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
        if (!(comission instanceof Integer)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Integer");
        }
        _comission = comission;
    }

    public void setGrossSales(Float grossSales) throws UnsupportedDataTypeException {
        if (!(grossSales instanceof Float)) {
            throw new UnsupportedDataTypeException("Invalid input. Expected Float");
        }
        _grossSales = grossSales;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGross Sales: " + _grossSales + "\nComission: " + _comission;
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
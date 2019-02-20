package XMLProcessing;

public class Person {
    private String firstname;
    private String lastname;
    private int phone;
    private int fax;

    public Person(String firstname, String lastname, int phone, int fax) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                ", fax=" + fax +
                '}';
    }
}
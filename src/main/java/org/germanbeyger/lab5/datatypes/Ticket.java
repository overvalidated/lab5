package org.germanbeyger.lab5.datatypes;
import java.time.Instant;
import java.util.Date;

/**
 * Class for saving data about tickets.
 * <p>
 * Contains information about when it was created, it's name, coordinates, ticket type and person.
 * Also can contain information about price
 * <p>
 * @see TargetCollection
 * @see Coordinates
 * @see TicketType
 * @see Person
 */
public class Ticket implements Comparable<Ticket> {
    private int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private boolean refundable;
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    /**
     * Full Ticket object constructor
     * 
     * <p>
     * This constructor must be used when loading from XML.
     * If you create an instance from CLI please consider using simplified constructor {@link Ticket#Ticket(int, String, Coordinates, long, double, boolean, TicketType, Person)}
     * <p>
     * @param name name of ticket. Must be not null and not an empty string.
     * @param coordinates instance of class {@link Coordinates}
     * @param price positive Long value, can be null.
     * @param discount positive double value that must be in range between 0 and 100.
     * @param refundable positive boolean, can be null.
     * @param type {@link TicketType}
     * @param person {@link Person}
     * @throws IllegalArgumentException if entered or loaded values do not fit the conditions.
     */
    public Ticket(int id, String name, Coordinates coordinates, Date creationDate, 
            long price, double discount, boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        
        
        // Checking neccesary conditions
        if (name == null || coordinates == null || type == null || person == null) 
            throw new IllegalArgumentException("Required field is missing, check that necessary fields are not nulls");

        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        
        if (id < 1) throw new IllegalArgumentException("id must be positive.");
        
        if (discount <= 0 || discount > 100) 
            throw new IllegalArgumentException("Discount must be positive and less than 100");

        // Assigning fields
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.refundable = refundable;
        this.type = type;
        this.person = person;
    }

    /**
     * Simplified constructor
     * <p>
     * This constructor simplifies creation process for CLI by automatically saving current data.
     * 
     * <p>
     * @param name
     * @param coord
     * @param price
     * @param discount
     * @param refundable
     * @param type
     * @param person
     * @throws IllegalArgumentException
     */
    public Ticket(int id, String name, Coordinates coordinates, long price, double discount, boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        this(id, name, coordinates, Date.from(Instant.now()), price, discount, refundable, type, person);
    }

    
    /**
     * <p>
     * Ticket is compared to another ticket by price, then by {@link TicketType} and then by the creation date.
     * <p>
     * 
     * Note: this class has a natural ordering that is inconsistent with equals
     */
    public int compareTo(Ticket ticket) {
        // Sort by price
        if (this.price != ticket.price)
            return (this.price - ticket.price) > 0 ? 1 : -1;
        // Then sort by ticket type
        if (!this.type.equals(ticket.type))
            return this.type.compareTo(ticket.type);
        // Then sort by creation date
        if (!this.creationDate.equals(ticket.creationDate))
            return this.creationDate.compareTo(ticket.creationDate);
        
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", price='" + getPrice() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", refundable='" + isRefundable() + "'" +
            ", type='" + getType() + "'" +
            ", person='" + getPerson() + "'" +
            "}";
    }
    // returns true if Ticket is correct
    public boolean verify() {
        if (name == null || coordinates == null || type == null || person == null || creationDate == null) return false;
        if (price <= 0) return false;
        if (id < 1) return false;
        if (discount <= 0 || discount > 100) return false;
        return (getCoordinates().verify() && getPerson().verify());
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Long getPrice() {
        return this.price;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Boolean getRefundable() {
        return this.refundable;
    }

    public Boolean isRefundable() {
        return this.refundable;
    }

    public TicketType getType() {
        return this.type;
    }

    public Person getPerson() {
        return this.person;
    }
}

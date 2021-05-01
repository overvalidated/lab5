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
    private Long price; //Значение поля должно быть больше 0
    private Double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable;
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    /**
     * Full Ticket object constructor
     * 
     * <p>
     * This constructor must be used when loading from XML.
     * If you create an instance from CLI please consider using simplified constructor {@link Ticket#Ticket(int, String, Coordinates, Long, Double, Boolean, TicketType, Person)}
     * <p>
     * @param name name of ticket. Must be not null and not an empty string.
     * @param coord instance of class {@link Coordinates}
     * @param price positive Long value, can be null.
     * @param discount positive double value that must be in range between 0 and 100.
     * @param refundable positive boolean, can be null.
     * @param type {@link TicketType}
     * @param person {@link Person}
     * @throws IllegalArgumentException if entered or loaded values do not fit the conditions.
     */
    public Ticket(int id, String name, Coordinates coord, Date creationDate, 
            Long price, Double discount, Boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        
        
        // Checking neccesary conditions
        if (name == null || coord == null || type == null || person == null) 
            throw new IllegalArgumentException("Required field is missing, check if necessary field is not nulls");

        if (price != null) {
            if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        } 
        
        if (discount != null) {
            if (discount <= 0 || discount > 100) 
                throw new IllegalArgumentException("Discount must be positive and less than 100");
        }

        // Assigning fields
        this.id = id;
        this.name = name;
        this.coordinates = coord;
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
    public Ticket(int id, String name, Coordinates coord, Long price, Double discount, Boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        this(id, name, coord, Date.from(Instant.now()), price, discount, refundable, type, person);
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
        if (!this.price.equals(ticket.price)) 
            return this.price.compareTo(ticket.price);
        // Then sort by ticket type
        if (!this.type.equals(ticket.type))
            return this.type.compareTo(ticket.type);
        // Then sort by creation date
        if (!this.creationDate.equals(ticket.creationDate))
            return this.creationDate.compareTo(ticket.creationDate);
        
        return 0;
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

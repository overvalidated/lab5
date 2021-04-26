package org.example;
import java.util.Date;
import java.time.Instant;

/**
 * 
 */
public class Ticket { // implements Comparable<Ticket> {
    public static int counter = 0; 
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
     * This constructor creates new objects and is called from others.
     * It doesn't check if input is corrupted but since it's called from another constructor it checks all class requirements
     * @param name
     * @param coord
     * @param price
     * @param d
     */
    public Ticket(Integer id, String name, Coordinates coord, Date creationDate, 
            Long price, Double discount, Boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        
        
        // Checking neccesary conditions
        if (name == null || coord == null || type == null || person == null) 
            throw new IllegalArgumentException("Required field is missing, check if necessary field are not nulls");

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

    public Ticket(String name, Coordinates coord, Long price, Double discount, Boolean refundable, 
            TicketType type, Person person) throws IllegalArgumentException {
        this(++counter, name, coord, Date.from(Instant.now()), price, discount, refundable, type, person);
    }

}

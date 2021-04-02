package org.example;

public class Ticket implements Comparable<Ticket> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private boolean refundable;
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    public int compareTo(Ticket ticket) {
        boolean createdBefore = this.creationDate.compareTo(ticket.creationDate) < 0;
        
        if (createdBefore) {
            
        } else {

        }
    }
}

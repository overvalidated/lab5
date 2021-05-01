package org.example.interfaces;
import java.util.function.Predicate;
import org.example.Ticket;
/**
 * Interface for TargetCollection class
 * 
 * <p>
 * All indexes start from 1, not 0, since this functions are only invoked by the user. 
 * TargetCollection uses LinkedList under the hood. This provides high efficiency for removing and filtering objects.
 * Supports only {@link Ticket} class. 
 * <p>
 * 
 * @see {@link java.util.LinkedList LinkedList}
 * 
 */
public interface ITargetCollection extends Iterable<Ticket> {
    public boolean add(Ticket ticket); // возможность добавить в коллекцию, true если добавлен
    public void remove(int idx);  // возможность удалить объект по индексу
    public void update(int idx, Ticket ticket); // возможность заменить имеющийся объект
    public void clear(); // очистить элементы коллекции, при этом сохранив метаданные
    public void filter(Predicate<Ticket> predicate); // оставить элементы, подходящие под условие
    public int getNextId();
}

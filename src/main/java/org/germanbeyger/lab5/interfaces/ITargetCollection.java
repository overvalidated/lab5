package org.germanbeyger.lab5.interfaces;
import java.util.LinkedList;
import java.util.function.Predicate;

import org.germanbeyger.lab5.datatypes.Ticket;
/**
 * Interface for TargetCollection class
 * 
 * <p>
 * TargetCollection uses LinkedList under the hood. This provides high efficiency for removing and filtering objects.
 * Supports only {@link Ticket} class. 
 * <p>
 * 
 * 
 */
public interface ITargetCollection extends Iterable<Ticket> {
    void add(Ticket ticket); // возможность добавить в коллекцию, true если добавлен
    void addIfMax(Ticket ticket); 
    void remove(int idx);  // возможность удалить объект по id
    void update(int idx, Ticket ticket); // возможность заменить имеющийся объект
    void clear(); // очистить элементы коллекции, при этом сохранив метаданные
    void removeIf(Predicate<Ticket> predicate); // оставить элементы, подходящие под условие
    LinkedList<Ticket> filter(Predicate<Ticket> predicate);

    String getInfoAboutCollection();
    int countElements(); // returns number of elements in collection

    boolean verify();
}

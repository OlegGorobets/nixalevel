package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Garage<T extends Vehicle> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        private final String restyling;
        private final String date;

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
            this.restyling = UUID.randomUUID().toString();
            this.date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        }

        @Override
        public String toString() {
            return item +
                    ", restyling='" + restyling + '\'' +
                    ", date='" + date + '\'';
        }
    }

    public String getRestylingCount() {
        return String.valueOf(size);
    }


    public void add(T vehicle) {
        Node<T> temp;
        Node<T> newNode = new Node<>(vehicle, null, null);
        if (first == null) {
            first = newNode;
        } else {
            temp = first;
            first = newNode;
            first.next = temp;
            temp.prev = first;
        }
        size++;
    }

    public String getRestyling(T vehicle) {
        Node<T> temp = first;
        while (temp != null) {
            if (temp.item.equals(vehicle)) {
                return temp.restyling;
            }
            temp = temp.next;
        }
        return "A " + vehicle.getClass().getSimpleName() + " with such restyling is not in the garage.";
    }

    public String set(String restyling, T vehicle) {
        Node<T> temp = first;
        while (temp != null) {
            if (temp.restyling.equals(restyling)) {
                temp.item = vehicle;
                return temp.toString();
            }
            temp = temp.next;
        }
        return temp.toString();
    }

    public String getFirst() {
        Node<T> temp = first;
        if (temp == null) {
            return "The garage is empty";
        } else {
            return temp.item + "'" + temp.date + "'";
        }
    }

    public String getLast() {
        Node<T> temp = first;
        if (temp == null) {
            return "The garage is empty";
        } else {
            while (true) {
                if (temp.next == null) {
                    return temp.item + "'" + temp.date + "'";
                }
                temp = temp.next;
            }
        }
    }

    public String search(String restyling) {
        Node<T> temp = first;
        while (temp != null) {
            if (temp.restyling.equals(restyling)) {
                return temp.toString();
            } else {
                temp = temp.next;
            }
        }
        return null;
    }

    public Node<T> searchForRemove(String restyling) {
        Node<T> temp = first;
        while (temp != null) {
            if (temp.restyling.equals(restyling)) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }

    public String remove(String restyling) {
        Node<T> temp = searchForRemove(restyling);
        Node<T> before = temp.prev;
        Node<T> next = temp.next;
        if( before == null ) {
            return removeFirst(temp);
        }
        else if(next == null) {
            return removeLast(temp);
        }
        else {
            temp.item = null;
            temp.next = null;
            temp.prev = null;
            before.next = next;
            next.prev = before;
        }
        size--;
        return "Item was deleted.";
    }

    public String removeFirst(Node<T> firstTemp) {
        final Node<T> next = firstTemp.next;
        first = next;
        firstTemp.item = null;
        firstTemp.next = null;
        if(next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return "Item was deleted.";
    }

    public String removeLast(Node<T> lastTemp) {
        final Node<T> newLast = lastTemp.prev;
        lastTemp.prev = null;
        lastTemp.item = null;
        last = newLast;
        if(newLast == null) {
            first = null;
        } else {
            newLast.next = null;
        }
        size--;
        return "Item was deleted.";
    }

    public String printAll() {
        final StringBuilder builder = new StringBuilder();
        Node<T> temp = first;
        while (temp != null) {
            builder.append(temp.item)
                    .append(", restyling=").append("'").append(temp.restyling).append("'")
                    .append(", date=").append("'").append(temp.date).append("'");
            if (temp.next != null) {
                builder.append("\n");
            }
            temp = temp.next;
        }
        return "\n" + builder;
    }

    public Iterator<T> iterator() {
        return new GarageIterator();
    }

    private class GarageIterator implements Iterator<T> {
        private Node<T> first;

        public GarageIterator() {
            this.first = Garage.this.first;
        }

        @Override
        public boolean hasNext() {
            return this.first != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = first.item;
            first = first.next;
            return item;
        }
    }

}



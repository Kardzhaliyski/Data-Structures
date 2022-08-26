package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> tail;
    private int size;

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null) {
            tail = newNode;
            size++;
            return;
        }

        Node<E> current = tail;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setNext(tail);
        tail = newNode;
        size++;
    }

    @Override
    public E removeFirst() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty");
        }

        if (tail.getNext() == null) {
            size--;
            E value = tail.getValue();
            tail = null;
            return value;
        }

        Node<E> current = tail;
        Node<E> next = tail.getNext();
        while (next.getNext() != null) {
            current = next;
            next = current.getNext();
        }

        current.setNext(null);
        size--;
        return next.getValue();
    }

    @Override
    public E removeLast() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty");
        }

        Node<E> temp = tail;
        tail = tail.getNext();

        return temp.getValue();
    }

    @Override
    public E getFirst() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty");
        }

        Node<E> current = tail;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        return current.getValue();
    }

    @Override
    public E getLast() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty");
        }

        return tail.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = tail;

            @Override
            public boolean hasNext() {
                return tail != null;
            }

            @Override
            public E next() {
                E value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }
}

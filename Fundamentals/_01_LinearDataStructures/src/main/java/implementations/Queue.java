package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> tail;
    private int size;

    public Queue() {
        size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (tail == null) {
            tail = newNode;
            size++;
            return;
        }

        Node<E> current = tail;
        while (current.getNext() != null) {
            current.setNext(newNode);
        }

        size++;
    }

    @Override
    public E poll() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty!");
        }

        Node<E> toRemove = tail;
        tail = tail.getNext();
        size--;
        return toRemove.getValue();
    }

    @Override
    public E peek() {
        if (tail == null) {
            throw new IllegalStateException("Queue must not be empty!");
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
                return current != null;
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

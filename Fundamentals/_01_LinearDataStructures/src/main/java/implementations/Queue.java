package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;

    public Queue() {
        size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node<E> current = head;
        while (current.getNext() != null) {
            current.setNext(newNode);
        }

        size++;
    }

    @Override
    public E poll() {
        if (head == null) {
            throw new IllegalStateException("Queue must not be empty!");
        }

        Node<E> toRemove = head;
        head = head.getNext();
        size--;
        return toRemove.getValue();
    }

    @Override
    public E peek() {
        if (head == null) {
            throw new IllegalStateException("Queue must not be empty!");
        }

        return head.getValue();
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
            Node<E> current = head;

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

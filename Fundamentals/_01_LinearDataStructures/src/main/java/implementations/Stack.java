package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> head;
    private int size;

    public Stack() {
        size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    @Override
    public E pop() {
        if(head == null) {
            throw new IllegalStateException("Cant pop from empty stack!");
        }

        Node<E> temp = head;
        head = head.getNext();
        size--;
        return temp.getValue();
    }

    @Override
    public E peek() {
        if(head == null) {
            throw new IllegalStateException("Cant pop from empty stack!");
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

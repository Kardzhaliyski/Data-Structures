package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 4;
    private Object[] elements;
    private int capacity;
    private int size;

    public ArrayList() {
        this.capacity = INITIAL_CAPACITY;
        this.elements = new Object[capacity];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();

        elements[size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        validIndex(index);
        ensureCapacity();

        shiftRight(index);
        elements[index] = element;
        return true;
    }

    @Override
    public E get(int index) {
        validIndex(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        validIndex(index);
        Object tmp = elements[index];
        elements[index] = element;
        return (E) tmp;
    }

    @Override
    public E remove(int index) {
        validIndex(index);

        Object tmp = elements[index];
        shiftLeft(index);
        return (E) tmp;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                return get(currentIndex++);
            }
        };
    }

    private void ensureCapacity() {
        if (capacity == size) {
            grow();
        }
    }

    private void grow() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }


    private void shiftRight(int index) {
        for (int i = size; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        size++;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be in list boundaries! For: " + index);
        }
    }
}

package edu.nyu.cs9053.homework7;
import java.util.*;
import java.lang.reflect.Array;
public class Wallet<T> {

    private T[] elementData;
    private int size;

    public Wallet(Class<T> aClass, int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = getArrayOfT(aClass, initialCapacity);
            if (elementData != null) {
                this.size = 0;
            } else{
                throw new IllegalArgumentException("Array is null!");
            }
        }else {
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    private T[] getArrayOfT(Class<T> aClass, int capacity) {
        try {
            return (T[]) Array.newInstance(aClass, capacity);
        }catch (Exception e) {
            System.out.printf("%s", e.getMessage());
            return null;
        }
    }

    public boolean add(T value) {
        if (! contains(value)) {
            if (ensureExplicitCapacity(size + 1)) {
                elementData[size++] = value;
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return true;
        } else {
            for (int i = 0; i < size; i++)
                if (obj.equals(elementData[i]))
                    return true;
        }
        return false;
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    removeFromIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++)
                if (obj.equals(elementData[i])) {
                    removeFromIndex(i);
                    return true;
                }
        }
        return false;
    }


    public T get(int index) {
        rangeCheck(index);
        return  this.elementData[index];
    }

    public int size() {
        return this.size;
    }

    private boolean ensureExplicitCapacity(int capacity) {
        if (capacity - this.elementData.length > 0) {
            int length = this.elementData.length;
            if ((long) length << 1 > Integer.MAX_VALUE) {
                return false;
            } else {
                int newLength = length << 1;
                if (newLength - capacity < 0) {
                    newLength = capacity;
                }
                this.elementData = Arrays.copyOf(this.elementData, newLength);
            }
        }
        return true;
    }

    private void removeFromIndex(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    private void rangeCheck(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Illegal index: " + index + ", Size: " + this.size);
        }
    }

}

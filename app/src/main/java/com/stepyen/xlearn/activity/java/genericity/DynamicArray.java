package com.stepyen.xlearn.activity.java.genericity;

/**
 * date：2019/5/19
 * author：stepyen
 * description：
 */

import java.util.Arrays;

/**
 * 内部有个动态数组
 * @param <E>
 */
public class DynamicArray<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldSize = elementData.length;
        if (oldSize >= minCapacity) {
            return;
        }

        int newSize = oldSize * 2;
        if (minCapacity > newSize) {
            newSize = minCapacity;
        }

        elementData =  Arrays.copyOf(elementData, newSize);

    }

    public void add(E e) {
        ensureCapacity(size +1);
        elementData[++size] = e;
    }

    public E get(int index) {
        if (index<0 ||index >= size) {
            return null;
        }

        return (E) elementData[index];

    }

    public E set(int index, E element) {
        elementData[index] = element;
        return element;
    }

    public int getSize() {
        return size;
    }


    public void addAll(DynamicArray<? extends E> dynamicArray ) {
        for (int i = 0; i < dynamicArray.size; i++) {
            add(dynamicArray.get(i));
        }
    }


    /**
     * 演示 接受不确定类型时，又要进行存入操作的 写法
     * @param array
     * @param i
     * @param y
     */
    public static void swap(DynamicArray<?> array, int i, int y) {
        swapInternal(array, i,y);
    }

    private static <T> void swapInternal(DynamicArray<T> array, int i, int y) {
        T t = array.get(i);
        array.set(i, array.get(y));
        array.set(y, t);
    }
}

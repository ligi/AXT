package org.ligi.axt.helpers;

public class ArrayHelper<T> {

    private final T[] arr;

    public ArrayHelper(T[] arr) {
        this.arr = arr;
    }

    public T[] combineWith(T[] arr2) {
        final int alen = arr.length;
        final int blen = arr2.length;
        @SuppressWarnings("unchecked")
        final T[] result = (T[]) java.lang.reflect.Array.
                newInstance(arr.getClass().getComponentType(), alen + blen);
        System.arraycopy(arr, 0, result, 0, alen);
        System.arraycopy(arr2, 0, result, alen, blen);
        return result;
    }
}

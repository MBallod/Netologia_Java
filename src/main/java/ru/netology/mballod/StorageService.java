package ru.netology.mballod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageService<T> implements Serializable {
    protected List<T> values = new ArrayList<>();

    public void add(int num, T newValue) {
        values.add(num, newValue);
    }
    public void add(T newValue) {
        values.add(newValue);
    }

    static public int[] ArrayIntAppend(int[] arr, int a){  // to StorageService
        int[] array;
        if (arr == null){
            array= new int[1];
            array[0] =a;
        }else {
            array = Arrays.copyOf(arr, arr.length+1);
            array[array.length-1] = a;
        }
        return array;
    }
/*    static public Operation[] ArrayOpAppend(Operation[] arr, Operation a){ // to StorageService
        Operation[] array;
        if (arr == null){
            array= new Operation[1];
            array[0] =a;
        }else {
            array = Arrays.copyOf(arr, arr.length+1);
            array[array.length-1] = a;
        }
        return array;
    }
*/
}

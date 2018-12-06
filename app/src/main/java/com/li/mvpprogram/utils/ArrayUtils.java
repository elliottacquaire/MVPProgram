/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by lison on 8/8/16.
 */
public class ArrayUtils {

    private ArrayUtils() {
        throw new AssertionError();
    }

    /**
     * is null or its length is 0
     *
     * @param <V>
     * @param sourceArray
     * @return
     */
    public static <V> boolean isEmpty(V[] sourceArray) {
        return (sourceArray == null || sourceArray.length == 0);
    }

    /**
     * get last element of the target element, before the first one that match the target element front to back
     * <ul>
     * <li>if array is empty, return defaultValue</li>
     * <li>if target element is not exist in array, return defaultValue</li>
     * <li>if target element exist in array and its index is not 0, return the last element</li>
     * <li>if target element exist in array and its index is 0, return the last one in array if isCircle is true, else
     * return defaultValue</li>
     * </ul>
     *
     * @param <V>
     * @param sourceArray
     * @param value        value of target element
     * @param defaultValue default return value
     * @param isCircle     whether is circle
     * @return
     */
    public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == 0) {
            return isCircle ? sourceArray[sourceArray.length - 1] : defaultValue;
        }
        return sourceArray[currentPosition - 1];
    }

    /**
     * get next element of the target element, after the first one that match the target element front to back
     * <ul>
     * <li>if array is empty, return defaultValue</li>
     * <li>if target element is not exist in array, return defaultValue</li>
     * <li>if target element exist in array and not the last one in array, return the next element</li>
     * <li>if target element exist in array and the last one in array, return the first one in array if isCircle is
     * true, else return defaultValue</li>
     * </ul>
     *
     * @param <V>
     * @param sourceArray
     * @param value        value of target element
     * @param defaultValue default return value
     * @param isCircle     whether is circle
     * @return
     */
    public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == sourceArray.length - 1) {
            return isCircle ? sourceArray[0] : defaultValue;
        }
        return sourceArray[currentPosition + 1];
    }

    /**
     * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} defaultValue is null
     */
    public static <V> V getLast(V[] sourceArray, V value, boolean isCircle) {
        return getLast(sourceArray, value, null, isCircle);
    }

    /**
     * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} defaultValue is null
     */
    public static <V> V getNext(V[] sourceArray, V value, boolean isCircle) {
        return getNext(sourceArray, value, null, isCircle);
    }

    /**
     * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object is Long
     */
    public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Long[] array = ObjectUtils.transformLongArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);

    }

    /**
     * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object is Long
     */
    public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Long[] array = ObjectUtils.transformLongArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }

    /**
     * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object is Integer
     */
    public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtils.transformIntArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);

    }

    /**
     * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object is Integer
     */
    public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtils.transformIntArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }

    public static char[] reverseArray2(char[] Array) {
        char[] new_array = new char[Array.length];
        for (int i = 0; i < Array.length; i++) {
            // 反转后数组的第一个元素等于源数组的最后一个元素：
            new_array[i] = Array[Array.length - i - 1];
        }
        return new_array;
    }

    /**
     * 移动一个列表中的元素位置
     * <p>
     * A B C D 四个元素，移动2坐标移动到0坐标，
     * 结果： C A B D
     *
     * @param collection   列表
     * @param fromPosition 起始位置
     * @param toPosition   目标位置
     * @param <T>          元素
     * @return 列表
     */
    public static <T> Collection<T> move(List<T> collection, int fromPosition, int toPosition) {
        int maxPosition = collection.size() - 1;
        if (fromPosition == toPosition || fromPosition > maxPosition || toPosition > maxPosition)
            return collection;

        if (fromPosition < toPosition) {
            T fromModel = collection.get(fromPosition);
            T toModel = collection.get(toPosition);

            collection.remove(fromPosition);
            collection.add(collection.indexOf(toModel) + 1, fromModel);
        } else {
            T fromModel = collection.get(fromPosition);
            collection.remove(fromPosition);
            collection.add(toPosition, fromModel);
        }

        return collection;
    }


    /**
     * List集合转换为数组
     *
     * @param items  List数据
     * @param tClass 数据的类型class
     * @param <T>    Class
     * @return 转换完成后的数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> items, Class<T> tClass) {
        if (items == null || items.size() == 0)
            return null;
        int size = items.size();
        try {
            T[] array = (T[]) Array.newInstance(tClass, size);
            return items.toArray(array);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 数组集合转换为ArrayList集合
     *
     * @param items 数组集合
     * @param <T>   Class
     * @return 转换完成后的ArrayList集合
     */
    public static <T> ArrayList<T> toArrayList(T[] items) {
        if (items == null || items.length == 0)
            return null;
        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, items);
        return list;
    }

    /**
     * 通过选择的value值 获取code
     */
    public static String getArrayItemCode(Context context, int itemArray, int numberArray, String itemValue) {
        String[] itemArrays = context.getResources().getStringArray(itemArray);
        String[] numberArrays = context.getResources().getStringArray(numberArray);
        int position = getPosition(itemArrays, itemValue);
        if (position == -1 || position > numberArrays.length - 1) {
            return "-1";
        } else {
            return numberArrays[position];
        }
    }

    /**
     * 通过code获取value
     */
    public static String getArrayItemByCode(Context context, int itemArray, int numberArray, String itemCode) {
        String[] itemArrays = context.getResources().getStringArray(itemArray);
        String[] numberArrays = context.getResources().getStringArray(numberArray);
        int position = getPosition(numberArrays, itemCode);
        if (position == -1 || position > itemArrays.length - 1) {
            return "";
        } else {
            return itemArrays[position];
        }
    }

    /**
     * 查询字符串在字符数组中的位置
     */
    public static int getPosition(String[] strArray, String itemNum) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(itemNum))
                return i;
        }
        return -1;
    }
}

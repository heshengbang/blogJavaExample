package com.hsb.java;/**
 * Created by heshengbang on 2018/6/3.
 */

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by heshengbang on 2018/6/3.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public interface Map<K, V> {
    // 查询类操作

    // 返回此Map中Entry的数量
    int size();
    // 检查该Map是否为空
    boolean isEmpty();
    // 查询该Map中是否含有指定的key值
    boolean containsKey(Object key);
    // 检查该Map中是否含有指定的value值
    boolean containsValue(Object value);
    // 获取map中含有key的Entry的value值
    V get(Object key);
    // 关联Map中指定的key和value，在map生成一个Entry（可选操作）
    // 如果key已存在，具体行为见具体实现类
    V put(K key, V value);
    // 移出map中指定key对应的Entry
    V remove(Object key);

    interface Entry<K, V> {
        // 返回此Entry对应的key
        K getKey();
        // 返回此Entry对应的value
        V getValue();
        // 用指定的值替换这个Entry对应的value（可选操作）
        // 如果Entry已经被从Map中移出（通过迭代器），则这个操作的行为是未知的
        V setValue(V value);
        // 比较指定的值与这个Entry是否相同
        // 值得注意的是，这个比较结果是键值都参与比较的结果
        boolean equals(Object o);
        // 返回这个Entry条目的hash值
        int hashCode();
        // since 1.8 返回一个比较器，它在键上按自然顺序比较Map.Entry
        public static <K extends Comparable<? super K>, V> Comparator<Entry<K, V>> comparingByKey() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }
        // since 1.8 返回一个比较器，它在值上按自然顺序比较Map.Entry
        public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }
        // since 1.8 返回一个比较器，它使用给定的Comparator通过键比较Map.Entry
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }
        // since 1.8 返回一个比较器，它使用给定的Comparator通过值比较Map.Entry
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }
    }

    // 批量操作

    // 将一个Map中所有的Entry放入到当前Map中，类似于合并两个Map
    void putAll(Map<? extends K, ? extends V> m);
    // 移出Map中所有的Entry，这个操作完成后Map中将变成空
    void clear();

    // 获取视图

    // 获取此Map中所有key的集合
    Set<K> keySet();
    // 获取此Map中所有的value的集合
    Collection<V> values();
    // 获取此Map中所有Entry的集合
    Set<Map.Entry<K, V>> entrySet();

    // 比较和哈希

    // 比较指定的对象和此Map是否相等
    boolean equals(Object o);
    // 获取此Map的hashcode
    int hashCode();

    //默认的方法

    // since 1.8 返回指定键映射到的值;如果此映射不包含键映射，则返回defaultValue
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
                ? v
                : defaultValue;
    }
    // since 1.8 对此Mao中的每个Entry执行给定操作，直到处理完所有Entry或操作抛出异常为止
    // BiConsumer是一个函数式接口，代表了一个接受两个输入参数的操作，并且不返回任何结果
    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }
    // since 1.8 对此Map的所有Entry调用给定函数式接口，并将结果替换每个Entry的值，直到处理完所有条目或者该函数抛出异常
    // 函数抛出的异常会传递给调用者
    // BiFunction是一个函数式接口，传入两个参数，执行指定操作并返回结果
    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }

            // ise thrown from function is not a cme.
            v = function.apply(k, v);

            try {
                entry.setValue(v);
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
        }
    }
    // 如果Map中不存在指定的key就将其与value关联起来，并返回null
    // 如果Map中存在指定的key就将其value返回
    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }
        return v;
    }
    // 移出指定的key/value键值对
    default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value) ||
                (curValue == null && !containsKey(key))) {
            return false;
        }
        remove(key);
        return true;
    }
    // 使用指定的value去替换存在的key-value键值对中的value
    default boolean replace(K key, V oldValue, V newValue) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, oldValue) ||
                (curValue == null && !containsKey(key))) {
            return false;
        }
        put(key, newValue);
        return true;
    }
    // 仅当指定键的条目映射到某个值时才替换该条目
    default V replace(K key, V value) {
        V curValue;
        if (((curValue = get(key)) != null) || containsKey(key)) {
            curValue = put(key, value);
        }
        return curValue;
    }
    // 如果指定的键尚未与值关联（或关联到null），则尝试使用给定的映射函数计算其值，并将其输入到此Map中，除非{null}。
    default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                put(key, newValue);
                return newValue;
            }
        }

        return v;
    }
    // 如果指定键的值存在且非空，则尝试计算给定key和当前映射值的新映射
    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if ((oldValue = get(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                put(key, newValue);
                return newValue;
            } else {
                remove(key);
                return null;
            }
        } else {
            return null;
        }
    }
    // 尝试计算指定键和其当前映射值的映射（如果没有当前映射，则为{null}）
    default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);

        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            // delete mapping
            if (oldValue != null || containsKey(key)) {
                // something to remove
                remove(key);
                return null;
            } else {
                // nothing to do. Leave things as they were.
                return null;
            }
        } else {
            // add or replace old mapping
            put(key, newValue);
            return newValue;
        }
    }
    // 如果指定的key还没有关联到value或者和null关联，则将其和一个非null值进行关联
    // 否则，使用给定的重映射函数的结果替换关联的值，或者如果结果为null，则将其删除
    default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = (oldValue == null) ? value :
                remappingFunction.apply(oldValue, value);
        if (newValue == null) {
            remove(key);
        } else {
            put(key, newValue);
        }
        return newValue;
    }
}

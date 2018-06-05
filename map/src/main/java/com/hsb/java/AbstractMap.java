package com.hsb.java;

import java.util.*;

/**
 * Created by heshengbang on 2018/6/3.
 */

public abstract class AbstractMap<K, V> implements Map<K, V> {
    // 唯一的构造方法， 子类调用父类的构造方法通常是隐式的
    protected AbstractMap() {
    }

    // 查询操作

    // 返回此Map有多少个Entry
    public int size() {
        return entrySet().size();
    }
    // 判断该Map是否为空
    public boolean isEmpty() {
        return size() == 0;
    }
    // 判断此类是否包含指定的value
    public boolean containsValue(Object value) {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (value == null) {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getValue() == null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
    }
    // 判断此类是否包含指定的key
    public boolean containsKey(Object key) {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        if (key == null) {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getKey() == null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
    }
    // 获取指定key对应的value，如果没有返回null
    public V get(Object key) {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (key == null) {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getKey() == null)
                    return e.getValue();
            }
        } else {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return null;
    }

    // 修改操作

    // 设置key-value键值对，抛出UnsupportedOperationException
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }
    // 移出指定key
    public V remove(Object key) {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        Entry<K, V> correctEntry = null;
        if (key == null) {
            while (correctEntry == null && i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getKey() == null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry == null && i.hasNext()) {
                Entry<K, V> e = i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }
        V oldValue = null;
        if (correctEntry != null) {
            oldValue = correctEntry.getValue();
            i.remove();
        }
        return oldValue;
    }

    // 批量操作

    // 将指定Map中所有元素放入到此Map中
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }
    // 清空当前Map中的所有Entry
    public void clear() {
        entrySet().clear();
    }
    // key的集合，注意，该集合是volatile的
    transient volatile Set<K> keySet;
    // value的集合，注意，该集合是volatile的
    transient volatile Collection<V> values;
    // 获取key的集合
    public Set<K> keySet() {
        if (keySet == null) {
            keySet = new AbstractSet<K>() {
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<Entry<K, V>> i = entrySet().iterator();
                        public boolean hasNext() {
                            return i.hasNext();
                        }
                        public K next() {
                            return i.next().getKey();
                        }
                        public void remove() {
                            i.remove();
                        }
                    };
                }
                public int size() {
                    return AbstractMap.this.size();
                }
                public boolean isEmpty() {
                    return AbstractMap.this.isEmpty();
                }
                public void clear() {
                    AbstractMap.this.clear();
                }
                public boolean contains(Object k) {
                    return AbstractMap.this.containsKey(k);
                }
            };
        }
        return keySet;
    }
    // 返回所有值的集合
    public Collection<V> values() {
        if (values == null) {
            values = new AbstractCollection<V>() {
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<Entry<K, V>> i = entrySet().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public V next() {
                            return i.next().getValue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return AbstractMap.this.size();
                }

                public boolean isEmpty() {
                    return AbstractMap.this.isEmpty();
                }

                public void clear() {
                    AbstractMap.this.clear();
                }

                public boolean contains(Object v) {
                    return AbstractMap.this.containsValue(v);
                }
            };
        }
        return values;
    }
    // 获取Entry的集合
    public abstract Set<Entry<K, V>> entrySet();

    // 比较和hashcode

    // 判断是否相等
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map))
            return false;
        Map<?, ?> m = (Map<?, ?>) o;
        if (m.size() != size())
            return false;
        try {
            Iterator<Entry<K, V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (!(m.get(key) == null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
        return true;
    }
    // 获取hashcode
    public int hashCode() {
        int h = 0;
        Iterator<Entry<K, V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }
    // 重写了toString方法
    public String toString() {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (!i.hasNext())
            return "{}";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (; ; ) {
            Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (!i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }
    // 重写了clone方法，实际上还是用的底层native方法去完成，clone出来的Map它的key集合和value集合都是空
    protected Object clone() throws CloneNotSupportedException {
        AbstractMap<?, ?> result = (AbstractMap<?, ?>) super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }
    // 一个私有的工具类方法，判断两个对象是否相等，提供给内部类使用
    private static boolean eq(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static class SimpleImmutableEntry<K, V> implements Map.Entry<K, V>, java.io.Serializable {
        private static final long serialVersionUID = 7138329143949025153L;
        // 与SimpleEntry不同的是，SimpleImmutableEntry的value也是不可变的
        private final K key;
        private final V value;

        // 同样提供了两种构造函数
        public SimpleImmutableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleImmutableEntry(Map.Entry<? extends K, ? extends V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        // 返回这个Entry的key
        public K getKey() {
            return key;
        }

        // 返回这个Entry的value
        public V getValue() {
            return value;
        }

        // 当调用这个类的setValue时，会抛出UnsupportedOperationException异常
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        // 判断该类与另一个对象是否相等
        // 这里面调用了AbstractMap中的eq静态工具方法
        // return o1 == null ? o2 == null : o1.equals(o2);
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        // 返回Entry对应的hashcode
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        // 重写了toString()方法
        public String toString() {
            return key + "=" + value;
        }
    }

    public static class SimpleEntry<K, V> implements Map.Entry<K, V>, java.io.Serializable {
        private static final long serialVersionUID = -8499721149061103585L;
        // 注意，key是final的
        private final K key;
        private V value;

        //  提供了两种类型的构造函数
        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        // 获取key
        public K getKey() {
            return key;
        }

        // 获取value
        public V getValue() {
            return value;
        }

        // 设置value，并返回之前的值
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        // 判断与另一个对象是否相等
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        // 获取hashcode
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        // 重写了toString()方法
        public String toString() {
            return key + "=" + value;
        }
    }
}
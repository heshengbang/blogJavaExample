package com.hsb.java.blog.myReflect;
        /*
         * Copyright ©2011-2017 hsb
         */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyTshirt <C> extends MyObject implements MarkInterface, ParameterizedType, Comparable{
    private C c;
    public void wear(C color) {
        this.c = color;
    }

    public C getColor() {
        return c;
    }

    /**
     * Returns an array of {@code Type} objects representing the actual type
     * arguments to this type.
     * <p>
     * <p>Note that in some cases, the returned array be empty. This can occur
     * if this type represents a non-parameterized type nested within
     * a parameterized type.
     *
     * @return an array of {@code Type} objects representing the actual type
     * arguments to this type
     * @throws TypeNotPresentException             if any of the
     *                                             actual type arguments refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if any of the
     *                                             actual type parameters refer to a parameterized type that cannot
     *                                             be instantiated for any reason
     * @since 1.5
     */
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[0];
    }

    /**
     * Returns the {@code Type} object representing the class or interface
     * that declared this type.
     *
     * @return the {@code Type} object representing the class or interface
     * that declared this type
     * @since 1.5
     */
    @Override
    public Type getRawType() {
        return null;
    }

    /**
     * Returns a {@code Type} object representing the type that this type
     * is a member of.  For example, if this type is {@code O<T>.I<S>},
     * return a representation of {@code O<T>}.
     * <p>
     * <p>If this type is a top-level type, {@code null} is returned.
     *
     * @return a {@code Type} object representing the type that
     * this type is a member of. If this type is a top-level type,
     * {@code null} is returned
     * @throws TypeNotPresentException             if the owner type
     *                                             refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if the owner type
     *                                             refers to a parameterized type that cannot be instantiated
     *                                             for any reason
     * @since 1.5
     */
    @Override
    public Type getOwnerType() {
        return null;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

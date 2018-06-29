package com.hsb.java.blog.thoughtswork;/**
 * Created by heshengbang on 2018/6/24.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Created by heshengbang on 2018/6/24.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class Node {
    // default_capacity for both from and to List
    private static final int DEFAULT_CAPACITY = 8;

    // from current node to other node
    private HashSet<Element> from;
    // from other node to current node
    private HashSet<Element> to;

    // the value of current node
    private char current;

    public Node(char current) {
        from = new HashSet<>(DEFAULT_CAPACITY);
        to = new HashSet<>(DEFAULT_CAPACITY);
        this.current = current;
    }

    public List<Character> getAllTo() {
        if (to == null) {
            return null;
        } else {
            List<Character> list = new ArrayList<>();
            for (Element element: from) {
                list.add(element.getTo());
            }
            return list;
        }
    }

    public void addFrom(Element element) {
        if (from == null) {
            from = new HashSet<>(DEFAULT_CAPACITY);
        }
        from.add(element);
    }

    public void addTo(Element element) {
        if (to == null) {
            to = new HashSet<>(DEFAULT_CAPACITY);
        }
        to.add(element);
    }

    public char getCurrent() {
        return current;
    }

    public void setCurrent(char current) {
        this.current = current;
    }

    public boolean hasTo(char to) {
        Element element = new Element(current, to, 0);
        return from.contains(element);
    }

    public Element getToElement(char to) {
        Element element = new Element(current, to, 0);
        for (Element ele: this.from) {
            if (ele.equals(element)) {
                return ele;
            }
        }
        return null;
    }

    public Element getFromElement(char from) {
        Element element = new Element(from, current, 0);
        for (Element ele: this.to) {
            if (ele.equals(element)) {
                element.distance = ele.distance;
                break;
            }
        }
        return element;
    }

    public HashSet<Element> getFrom() {
        return from;
    }

    public void setFrom(HashSet<Element> from) {
        this.from = from;
    }

    public HashSet<Element> getTo() {
        return to;
    }

    public void setTo(HashSet<Element> to) {
        this.to = to;
    }

    /**
     * inner class
     */
    public static final class Element {
        private char from;
        private char to;
        private int distance;

        public Element(char from, char to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        public char getFrom() {
            return from;
        }

        public void setFrom(char from) {
            this.from = from;
        }

        public char getTo() {
            return to;
        }

        public void setTo(char to) {
            this.to = to;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return from == element.from &&
                    to == element.to;
        }
        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}

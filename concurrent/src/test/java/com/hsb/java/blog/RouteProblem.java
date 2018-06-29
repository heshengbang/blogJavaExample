package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/19.
 */

import java.util.Objects;

/**
 * Created by heshengbang on 2018/6/19.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class RouteProblem {
    class Node {
        private char from;
        private char to;
        private int distance;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return from == node.from &&
                    to == node.to &&
                    distance == node.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, distance);
        }
    }
    public void main (String[] args) {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        Node[] routes = parseInput(input);
    }

    private Node[] parseInput(String input) {
        Node[] routes = null;
        if (!validateInputIllegal(input)) {
            return routes;
        }
        return routes;
    }

    private boolean validateInputIllegal(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        if (input.length() != 3 && !input.contains(",")) {
            return false;
        }
        String[] routes = input.split(",");
        for (String element : routes) {
            if (element == null || element.contains(",")) {
                return false;
            }
            char[] elementChars = element.toCharArray();
            if (elementChars.length != 3) {
                return false;
            }
            if (elementChars[0] < 'A' || elementChars[0] > 'Z') {
                return false;
            }
            if (elementChars[1] < 'A' || elementChars[1] > 'Z') {
                return false;
            }
        }
        return true;
    }
}

package com.hsb.java.blog;

import com.hsb.java.blog.thoughtswork.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by heshengbang on 2018/6/24.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class Problem {
    private static HashMap<Character, Node> nodeHash;
    public static void main(String[] args) {
        initData();
        problemOne();
        problemTwo();
        problemThree();
        problemFour();
        problemFive();
        problemSix();
        problemSeven();
        problemEight();
        problemNine();
        problemTen();
    }

    // The number of different routes from C to C with a distance of less than 30.
    // In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
    private static void problemTen() {
        List<List> paths = new ArrayList<>();
        int count = 0;

        Node node = nodeHash.get('C');
        if (node != null) {
            HashSet<Node.Element> hashSet = node.getFrom();
            if (hashSet != null) {
                for (Node.Element element: hashSet) {
                    List<List> recur = findPath(element.getTo(), 'C', ' ', true);
                    for (List path:recur) {
                        path.add(0, 'C');
                        paths.add(path);
                    }
                }
                if (paths.size() > 0) {
                    for (List path : paths) {
                        int length = getLengthOfPath(path);
                        if (length < 30) {
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    // The length of the shortest route (in terms of distance to travel) from B to B.
    private static void problemNine() {
        List<Integer> lengthList;
        int shortest = 0;

        Node nodeB = nodeHash.get('B');
        HashSet<Node.Element> fromHashSetOfB = nodeB.getFrom();



        for (Node.Element element: fromHashSetOfB) {
            HashSet<Character> existNode = new HashSet<>();
            existNode.add('B');
            ArrayList<List> recur = findPath(element.getTo(), 'B', 'B', false);
            if (recur != null && recur.size() > 0) {
                for (List list: recur) {
                    list.add(0, 'B');
                }
                lengthList = new ArrayList<>();
                for (List list: recur) {
                    int length = getLengthOfPath(list);
                    lengthList.add(length);
                }
                if (lengthList.size() > 0) {
                    for (Integer integer: lengthList) {
                        if (integer < shortest || shortest == 0) {
                            shortest = integer;
                        }
                    }
                }
            }
        }
        System.out.println(shortest);
    }

    // The length of the shortest route (in terms of distance to travel) from A to C.
    private static void problemEight() {
        List<Integer> lengthList;
        int shortest = 0;

        HashSet<Character> existNode = new HashSet<>();

        List<List> paths = findPath('A', 'C', ' ', false);
        if (paths != null && paths.size() > 0) {
            lengthList = new ArrayList<>();
            for (List path: paths) {
                int length = getLengthOfPath(path);
                lengthList.add(length);
            }
            if (lengthList.size() > 0) {
                for (Integer integer: lengthList) {
                    if (integer < shortest || shortest == 0) {
                        shortest = integer;
                    }
                }
            }
        }
        System.out.println(shortest);
    }

    private static int getLengthOfPath(List<Character> path) {
        int result = 0;
        if (path != null && path.size() > 0) {
            List<Node.Element> elements = new ArrayList<>();
            for (int i = 0; i < path.size() - 1; i++) {
                Node.Element element = new Node.Element(path.get(i), path.get(i+1), 0);
                elements.add(element);
            }
            if (elements.size() > 0) {
                for (Node.Element element: elements) {
                    Node node = nodeHash.get(element.getFrom());
                    if (node != null) {
                        Node.Element toElement = node.getToElement(element.getTo());
                        if (toElement != null) {
                            result += toElement.getDistance();
                        }
                    }
                }
            }
        }
        return result;
    }

    // The number of trips starting at A and ending at C with exactly 4 stops.
    // In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    private static void problemSeven() {
        int count = 0;
        HashMap<Integer, List<Character>> hashMap = new HashMap<>();

        Node nodeA = nodeHash.get('A');
        List<Character> listOne = nodeA.getAllTo();
        // through 1 step could reach node List
        hashMap.put(1, listOne);

        List<Character> listTwo = new ArrayList<>();
        for (Character character: listOne) {
            Node two = nodeHash.get(character);
            if (two != null && two.getAllTo() != null) {
                listTwo.addAll(two.getAllTo());
            }
        }
        // through 2 steps could reach all node List
        hashMap.put(2, listTwo);

        List<Character> listThree = new ArrayList<>();
        for (Character character: listTwo) {
            Node three = nodeHash.get(character);
            if (three != null && three.getAllTo() != null) {
                listThree.addAll(three.getAllTo());
            }
        }
        // through 3 steps could reach all node List
        hashMap.put(3, listThree);

        List<Character> listFour = new ArrayList<>();
        for (Character character: listThree) {
            Node four = nodeHash.get(character);
            if (four != null && four.getAllTo() != null) {
                listFour.addAll(four.getAllTo());
            }
        }
        // through 4 steps could reach all node List
        hashMap.put(4, listFour);

        List<Character> list = hashMap.get(4);
        for (Character character: list) {
            if (character == 'C') {
                count ++;
            }
        }
        System.out.println(count);
    }

    // The number of trips starting at C and ending at C with a maximum of 3 stops.
    // In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops)
    private static void problemSix() {
        int count = 0;
        Node nodeC = nodeHash.get('C');
        HashSet<Node.Element> fromHashSetOfC = nodeC.getFrom();
        for (Node.Element element: fromHashSetOfC) {
            HashSet<Character> existNode = new HashSet<>();
            existNode.add('C');
            ArrayList<List> recur = findPath(element.getTo(), 'C', 'C', true);
            if (recur != null && recur.size() > 0) {
                for (List list: recur) {
                    list.add(0, 'C');
                }
                for (List list: recur) {
                    if (list.size() < 5) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    /**
     *  find path from one to other. when from == to, don't call this method proactive.
     */
    private static ArrayList<List> findPath(char from, char to, char beforeOfFrom, boolean isRepeat) {
        ArrayList<List> paths = null;
        if (from == to) {
            ArrayList<Character> path = new ArrayList<>();
            path.add(to);
            paths = new ArrayList<>();
            paths.add(path);
        } else {
            Node node = nodeHash.get(from);
            if (node != null) {
                List<Character> toNode = node.getAllTo();
                // initialize out of recursive
                paths = new ArrayList<>();
                for (Character character: toNode) {
                    if (isRepeat) {
                        ArrayList<List> recur = findPath(character, to, from, isRepeat);
                        if (recur != null && recur.size() > 0) {
                            for (List path: recur) {
                                path.add(0, from);
                                paths.add(path);
                            }
                        }
                    } else {
                        if (character != beforeOfFrom) {
                            ArrayList<List> recur = findPath(character, to, from, isRepeat);
                            if (recur != null && recur.size() > 0) {
                                for (List path: recur) {
                                    path.add(0, from);
                                    paths.add(path);
                                }
                            }
                        }
                    }
                }
            }
        }
        return paths;
    }

    // The distance of the route A-E-D
    private static void problemFive() {
        int distance = 0;
        boolean noSuchRoute = false;

        Node nodeA = nodeHash.get('A');
        Node.Element eleAB = nodeA.getToElement('E');
        if (eleAB != null) {
            distance += eleAB.getDistance();
            Node nodeB = nodeHash.get('E');
            Node.Element eleBC = nodeB.getToElement('D');
            if (eleBC != null) {
                distance += eleBC.getDistance();
            } else {
                noSuchRoute = true;
            }
        } else {
            noSuchRoute = true;
        }

        if (distance != 0 && !noSuchRoute) {
            System.out.println(distance);
        } else {
            System.out.println("NO SUCH ROUTE");
        }
    }

    // The distance of the route A-E-B-C-D
    private static void problemFour() {
        boolean noSuchRoute = false;
        int distance = 0;
        // AE
        Node node = nodeHash.get('A');
        Node.Element element = node.getToElement('E');
        if (element != null) {
            distance += element.getDistance();
        } else {
            noSuchRoute = true;
        }
        // EB
        node = nodeHash.get('E');
        element = node.getToElement('B');
        if (element != null) {
            distance += element.getDistance();
        } else {
            noSuchRoute = true;
        }
        // BC
        node = nodeHash.get('B');
        element = node.getToElement('C');
        if (element != null) {
            distance += element.getDistance();
        } else {
            noSuchRoute = true;
        }
        // CD
        node = nodeHash.get('C');
        element = node.getToElement('D');
        if (element != null) {
            distance += element.getDistance();
        } else {
            noSuchRoute = true;
        }
        if (distance != 0 && !noSuchRoute) {
            System.out.println(distance);
        } else {
            System.out.println("NO SUCH ROUTE");
        }
    }

    // The distance of the route A-D-C.
    private static void problemThree() {
        int distance = 0;
        boolean noSuchRoute = false;

        Node nodeA = nodeHash.get('A');
        Node.Element eleAB = nodeA.getToElement('D');
        if (eleAB != null) {
            distance += eleAB.getDistance();
            Node nodeB = nodeHash.get('D');
            Node.Element eleBC = nodeB.getToElement('C');
            if (eleBC != null) {
                distance += eleBC.getDistance();
            } else {
                noSuchRoute = true;
            }
        } else {
            noSuchRoute = true;
        }

        if (distance != 0 && !noSuchRoute) {
            System.out.println(distance);
        } else {
            System.out.println("NO SUCH ROUTE");
        }
    }

    // The distance of the route A-D
    private static void problemTwo() {
        int distance = 0;
        Node node = nodeHash.get('A');
        if (node != null) {
            Node.Element element = node.getToElement('D');
            if (element != null) {
                distance += element.getDistance();
            }
        }
        if (distance != 0) {
            System.out.println(distance);
        } else {
            System.out.println("NO SUCH ROUTE");
        }
    }
    // The distance of the route A-B-C
    private static void problemOne() {
        int distance = 0;
        boolean noSuchRoute = false;

        Node nodeA = nodeHash.get('A');
        Node.Element eleAB = nodeA.getToElement('B');
        if (eleAB != null) {
            distance += eleAB.getDistance();
            Node nodeB = nodeHash.get('B');
            Node.Element eleBC = nodeB.getToElement('C');
            if (eleBC != null) {
                distance += eleBC.getDistance();
            } else {
                noSuchRoute = true;
            }
        } else {
            noSuchRoute = true;
        }

        if (distance != 0 && !noSuchRoute) {
            System.out.println(distance);
        } else {
            System.out.println("NO SUCH ROUTE");
        }
    }

    // Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    private static void initData() {
        Node.Element element1 = new Node.Element('A', 'B', 5);
        Node.Element element2 = new Node.Element('B', 'C', 4);
        Node.Element element3 = new Node.Element('C', 'D', 8);
        Node.Element element4 = new Node.Element('D', 'C', 8);
        Node.Element element5 = new Node.Element('D', 'E', 6);
        Node.Element element6 = new Node.Element('A', 'D', 5);
        Node.Element element7 = new Node.Element('C', 'E', 2);
        Node.Element element8 = new Node.Element('E', 'B', 3);
        Node.Element element9 = new Node.Element('A', 'E', 7);

        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');

        a.addFrom(element1);
        a.addFrom(element6);
        a.addFrom(element9);

        b.addFrom(element2);
        b.addTo(element1);
        b.addTo(element8);

        c.addFrom(element3);
        c.addFrom(element7);
        c.addTo(element2);
        c.addTo(element4);

        d.addFrom(element4);
        d.addFrom(element5);
        d.addTo(element3);
        d.addTo(element6);

        e.addFrom(element8);
        e.addTo(element5);
        e.addTo(element7);
        e.addTo(element9);

        nodeHash = new HashMap<>();

        nodeHash.put('A', a);
        nodeHash.put('B', b);
        nodeHash.put('C', c);
        nodeHash.put('D', d);
        nodeHash.put('E', e);
    }
}

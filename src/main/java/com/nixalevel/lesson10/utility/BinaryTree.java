package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.model.Vehicle;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;

public class BinaryTree<T extends Vehicle> {
    private static final Comparator<Vehicle> sortByPrice = new Vehicle.SortByPrice();
    private static BigDecimal leftSum = BigDecimal.ZERO;
    private static BigDecimal rightSum = BigDecimal.ZERO;
    Node<T> root;

    private static class Node<T> {
        private final T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node<T> add(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (sortByPrice.compare(value, current.value) < 0) {
            current.left = add(current.left, value);
        } else if (sortByPrice.compare(value, current.value) > 0) {
            current.right = add(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(T value) {
        root = add(root, value);
    }

    public void traversePreOrder(Node<T> node) {
        LinkedList<Node<T>> nodes = new LinkedList<>();
        nodes.add(node);
        int i = 0;
        while (i < nodes.size() && nodes.get(i) != null) {
            System.out.print("Parent: " + nodes.get(i).value.getPrice()
                    + ", Model: " + nodes.get(i).value.getModel() + "\n");
            if (nodes.get(i).left != null && nodes.get(i).right != null) {
                System.out.println("Child: " + nodes.get(i).left.value.getPrice()
                        + ", Model: " + nodes.get(i).value.getModel() + " & "
                        + "Child: " + nodes.get(i).right.value.getPrice() + ", Model: " + nodes.get(i).value.getModel());
            }
            traversePreOrder(nodes.get(i).left);
            traversePreOrder(nodes.get(i).right);
            i++;
        }
    }

    public void traverseInOrder(Node<T> node) {
        LinkedList<Node<T>> nodes = new LinkedList<>();
        nodes.add(node);
        int i = 0;
        while (i < nodes.size() && nodes.get(i) != null) {
            if (node.value.getPrice().compareTo(root.value.getPrice()) > 0) {
                rightSum = rightSum.add(node.value.getPrice());
            } else if (node.value.getPrice().compareTo(root.value.getPrice()) < 0) {
                leftSum = leftSum.add(node.value.getPrice());
            }
            traverseInOrder(node.left);
            traverseInOrder(node.right);
            i++;
        }
    }

    public void print() {
        traversePreOrder(root);
    }

    public void sumTree() {
        traverseInOrder(root);
        System.out.println("Sum of left subtree: " + (leftSum.add(root.value.getPrice())) +
                ", Sum of right subtree: " + (rightSum.add(root.value.getPrice())));
    }
}

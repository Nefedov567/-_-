import java.util.*;

class BinomialHeap<T extends Comparable<T>> {
    private static class Node<T> {
        T key;
        int degree;
        Node<T> parent, child, sibling;
        
        Node(T key) {
            this.key = key;
            this.degree = 0;
        }
    }
    
    private Node<T> head;
    
    // Вставка элемента
    public void insert(T key) {
        Node<T> newNode = new Node<>(key);
        head = union(head, newNode);
    }
    
    // Получить минимальный элемент
    public T getMin() {
        if (head == null) return null;
        return findMinNode().key;
    }
    
    // Извлечь минимальный элемент
    public T extractMin() {
        if (head == null) return null;
        
        Node<T> minNode = findMinNode();
        head = removeNode(head, minNode);
        
        // Переворачиваем список детей и объединяем
        Node<T> childHeap = reverseChildren(minNode.child);
        head = union(head, childHeap);
        
        return minNode.key;
    }
    
    // Объединение двух куч
    private Node<T> union(Node<T> h1, Node<T> h2) {
        Node<T> newHead = merge(h1, h2);
        if (newHead == null) return null;
        
        Node<T> prev = null, curr = newHead, next = curr.sibling;
        
        while (next != null) {
            if (curr.degree != next.degree || 
               (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else if (curr.key.compareTo(next.key) <= 0) {
                curr.sibling = next.sibling;
                linkTrees(curr, next);
            } else {
                if (prev == null) newHead = next;
                else prev.sibling = next;
                linkTrees(next, curr);
                curr = next;
            }
            next = curr.sibling;
        }
        return newHead;
    }
    
    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        
        Node<T> newHead, current;
        if (h1.degree <= h2.degree) {
            newHead = h1;
            h1 = h1.sibling;
        } else {
            newHead = h2;
            h2 = h2.sibling;
        }
        
        current = newHead;
        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) {
                current.sibling = h1;
                h1 = h1.sibling;
            } else {
                current.sibling = h2;
                h2 = h2.sibling;
            }
            current = current.sibling;
        }
        
        current.sibling = (h1 != null) ? h1 : h2;
        return newHead;
    }
    
    private void linkTrees(Node<T> parent, Node<T> child) {
        child.parent = parent;
        child.sibling = parent.child;
        parent.child = child;
        parent.degree++;
    }
    
    private Node<T> findMinNode() {
        Node<T> min = head, current = head;
        while (current != null) {
            if (current.key.compareTo(min.key) < 0) min = current;
            current = current.sibling;
        }
        return min;
    }
    
    private Node<T> removeNode(Node<T> head, Node<T> node) {
        if (head == node) return head.sibling;
        Node<T> current = head;
        while (current.sibling != node) current = current.sibling;
        current.sibling = node.sibling;
        return head;
    }
    
    private Node<T> reverseChildren(Node<T> child) {
        Node<T> prev = null, current = child, next;
        while (current != null) {
            next = current.sibling;
            current.sibling = prev;
            current.parent = null;
            prev = current;
            current = next;
        }
        return prev;
    }
    
    public boolean isEmpty() { return head == null; }
}

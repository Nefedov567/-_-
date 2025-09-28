public class SimpleFibonacciHeap {
    
    private static class Node {
        int key;
        Node left, right, child;
        
        Node(int key) {
            this.key = key;
            this.left = this;
            this.right = this;
        }
    }
    
    private Node minNode;
    
    public SimpleFibonacciHeap() {
        minNode = null;
    }
    
    public void insert(int key) {
        Node newNode = new Node(key);
        if (minNode == null) {
            minNode = newNode;
        } else {
            // Простое добавление в список
            newNode.left = minNode;
            newNode.right = minNode.right;
            minNode.right.left = newNode;
            minNode.right = newNode;
            if (key < minNode.key) {
                minNode = newNode;
            }
        }
    }
    
    public int getMin() {
        if (minNode == null) {
            throw new RuntimeException("Heap is empty");
        }
        return minNode.key;
    }
    
    public int extractMin() {
        if (minNode == null) {
            throw new RuntimeException("Heap is empty");
        }
        
        int minValue = minNode.key;
        
        // Добавляем детей в корневой список
        if (minNode.child != null) {
            Node child = minNode.child;
            do {
                Node next = child.right;
                child.left = minNode;
                child.right = minNode.right;
                minNode.right.left = child;
                minNode.right = child;
                child = next;
            } while (child != minNode.child);
        }
        
        // Удаляем minNode
        minNode.left.right = minNode.right;
        minNode.right.left = minNode.left;
        
        // Находим новый минимум
        if (minNode.right == minNode) {
            minNode = null;
        } else {
            minNode = minNode.right;
            Node current = minNode;
            do {
                if (current.key < minNode.key) {
                    minNode = current;
                }
                current = current.right;
            } while (current != minNode);
        }
        
        return minValue;
    }
    
    public boolean isEmpty() {
        return minNode == null;
    }
    
    public static void main(String[] args) {
        SimpleFibonacciHeap heap = new SimpleFibonacciHeap();
        
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(3);
        heap.insert(8);
        
        System.out.println("Value of minimum element — " + heap.getMin());
        
        System.out.println("Extracting elements:");
        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
    }
}

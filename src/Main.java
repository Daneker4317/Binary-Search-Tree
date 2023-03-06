class BinarySearchTree {
    static class Node{
        int key;
        Node left , right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }
    Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    void deleteKey(int key){
        root = deleteRecursive(root , key);
    }

    private Node deleteRecursive(Node root, int key) {
        if (root == null){
            return null;
        }
        if (key < root.key){
            root.left = deleteRecursive(root.left , key);
        }else if (key > root.key){
            root.right = deleteRecursive(root.right , key);
        }else {
            if (root.left == null){
                return root.right;
            } else if (root.right == null){
                return root.left;
            }
            root.key = minValue(root.right);
            root.right = deleteRecursive(root.right , root.key);
        }
        return root;
    }
    private int minValue(Node root) {
        int min = root.key;
        while (root.left!=null){
            min = root.left.key;
            root = root.left;
        }
        return min;
    }
    void insert(int key){
        root = insertRecursive(root , key);
    }

    private Node insertRecursive(Node root, int key) {
        if (root == null){
            root = new Node(key);
            return root;
        }
        if (key < root.key){
            root.left = insertRecursive(root.left , key);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right , key);
        }
        return root;
    }
    void inorder(){
        inorderRecursive(root);
    }

    void inorderRecursive(Node root) {
        if (root != null){
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }
    boolean search(int key){
        root = searchRecursive(root , key);
        return root!=null;
    }

    private Node searchRecursive(Node root, int key) {
        if (root == null || root.key == key){
            return root;
        }
        return root.key > key ? searchRecursive(root.left , key) : searchRecursive(root.right , key);
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);

        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inorder();

        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inorder();

        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inorder();

        //delete node with two children
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.deleteKey(45);
        bst.inorder();

        //search a key in the BST
        boolean ret_val = bst.search (50);
        System.out.println("\nKey 50 found in BST:" + ret_val );

        ret_val = bst.search (12);
        System.out.println("\nKey 12 found in BST:" + ret_val );

    }
}
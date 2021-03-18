/**
 * Learn from 《Algorithms 4th edition》
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.color = BLACK;
    }


    private Node put(Node h, Key key, Value val)
    {
        if (h == null) {
            // Do standard insert, with red link to parent.
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);

        }  else if (cmp > 0) {
            h.right = put(h.right, key, val);

        } else {
            h.value = val;
        }

        if (h.right.isRed() && !h.left.isRed()) {
            h = rotateLeft(h);
        }
        if (h.left.isRed()&& h.left.left.isRed()) {
            h = rotateRight(h);
        }
        if (h.left.isRed() && h.right.isRed()) {
            flipColors(h);
        }
        h.numberOfNodesInThisSubtree = size(h.left) + size(h.right) + 1;
        return h;
    }

    public boolean isRed(Node node) {
        return node.isRed();
    }

    Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.numberOfNodesInThisSubtree = h.numberOfNodesInThisSubtree;
        h.numberOfNodesInThisSubtree = 1 + size(h.left)
            + size(h.right);
        return x;
    }


    Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.numberOfNodesInThisSubtree = h.numberOfNodesInThisSubtree;
        h.numberOfNodesInThisSubtree = 1 + size(h.left)
            + size(h.right);
        return x;
    }


    void flipColors(Node h)
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size(Node node) {
        if(node == null){
            return 0;
        }
        return node.numberOfNodesInThisSubtree;
    }

    public class Node {
        public Key key;
        public Value value;

        public Node left;
        public Node right;

        public int numberOfNodesInThisSubtree;

        public boolean color; // link from parent


        Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.value = val;
            this.numberOfNodesInThisSubtree = N;
            this.color = color;
        }


        public boolean isRed()
        {
            return this.color == RED;
        }

    }
}

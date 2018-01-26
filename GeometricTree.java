package comp2402a4;

import java.util.*;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {


    public GeometricTree() {
        super (new GeometricTreeNode());
    }

    public void inorderDraw() {
        assignLevels();
        int number = 0;
        // TODO: use your code here instead
        randomXpart1();
    }


    protected void randomX(GeometricTreeNode u, Random r) {
        if (u == null) return;
        u.position.x = r.nextInt(60);
        randomX(u.left, r);
        randomX(u.right, r);
    }


    protected void randomXpart1() {
        int number = 0;
       GeometricTreeNode u = firstNode();
       while(u != nil) {
           number++;
           u.position.x =  number;
           GeometricTreeNode next = nextNode(u);
           //if (next != nil)
               u = next;
         }
    }

    protected void randomXpart2() {
        int number = 0, oldY = 0;
        Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
        q.add(r);
        while (!q.isEmpty()) {
            GeometricTreeNode u = q.remove();
            if (oldY != u.position.y) {
                number = 0;
                oldY = u.position.y;
            }
            if (u.left != nil) {
                q.add(u.left);
            }
            if (u.right != nil) {
                q.add(u.right);
            }
            u.position.x = number;
            number++;
        }
    }

    protected void randomXpart3() {
        Map <GeometricTreeNode, Integer> x =  new HashMap<>();
        GeometricTreeNode u = new GeometricTreeNode();
        int number = 0, number2 = 0, count = 0;

        Stack<GeometricTreeNode> nodes = new Stack<GeometricTreeNode>();
        Stack<GeometricTreeNode> nodes2 = new Stack<GeometricTreeNode>();
        nodes.push(r);

        while (!nodes.isEmpty()) {
            u = nodes.pop();
            nodes2.push(u);
            if (u.left != nil) {
                nodes.push(u.left);
            }
            if (u.right != nil)
                nodes.push(u.right);
            u.position.x = count;
            x.put(u, size(u));
        }

        int y = 0, size = 0, size2 = 0;
        while (!nodes2.isEmpty()) {
            u = nodes2.pop();
            if (u.left != nil)
                size = x.get(u.left);
            else
                size = 0;
            if (u.right != nil)
                size2 = x.get(u.right);
            else
                size2 = 0;
            if (u.left != nil || u.right != nil) {
                if (size > size2) {
                    u.left.position.y = u.position.y;
                    u.left.position.x = count++;
                } else {
                    u.right.position.y = u.position.y;
                    u.right.position.x = count++;
                }
            }
          //  System.out.println(u.position.toString());
        }
    }


    /**
     * Draw each node so that it's x-coordinate is as small
     * as possible without intersecting any other node at the same level
     * the same as its parent's
     */
    public void leftistDraw() {
        assignLevels();
        randomXpart2();
       // Random rand = new Random();
        //randomX(r, rand);
    }


    public void balancedDraw() {
        assignLevels();
        randomXpart3();
        //Random rand = new Random();
        //randomX(r, rand);
    }

    protected void assignLevels() {
        assignLevels(r, 0);
    }

    protected void assignLevels(GeometricTreeNode u, int i) {
        if (u == null) return;
        u.position.y = i;
        assignLevels(u.left, i+1);
        assignLevels(u.right, i+1);
    }

    public static void main(String[] args) {
     GeometricTree t = new GeometricTree();
    GeometricTree.completeBinaryTree(t,100 );
    t.balancedDraw();
    }

}


import Bin.BinarySearchTree;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 1244821 Gabriela Orellana
 */

public class RBTree extends JPanel
{
    private static final int DEFAULT_WIDTH = 1800;
    private static final int DEFAULT_HEIGHT = 600;
    
    JPanel I, S;
    
    public RBTree() 
    {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        
        I = new JPanel();
        S = new JPanel();
        
        BinarySearchTree<String> binaryTreeS = new BinarySearchTree<>();
        binaryTreeS.insert("Jane");
        binaryTreeS.insert("Zaire");
        binaryTreeS.insert("Laura");
        binaryTreeS.insert("Allison");
        binaryTreeS.insert("Darius");
        binaryTreeS.insert("Ro");
        binaryTreeS.insert("Urumu");
        binaryTreeS.insert("Gary");
        binaryTreeS.insert("Boo");
        binaryTreeS.insert("Yu");
        BinarySearchTree<Integer> binaryTreeI = new BinarySearchTree<>();
        binaryTreeI.insert(20);
        binaryTreeI.insert(35);
        binaryTreeI.insert(50);
        binaryTreeI.insert(100);
        binaryTreeI.insert(25);
        binaryTreeI.insert(75);
        binaryTreeI.insert(65);
        binaryTreeI.insert(100);
        binaryTreeI.insert(45);
        binaryTreeI.insert(95);
        
        GUITree<String> treePanel = new GUITree<>(binaryTreeS);
        GUITree<Integer> treePanelI = new GUITree<>(binaryTreeI);
        treePanel.setPreferredSize(new Dimension(700, 500));
        treePanelI.setPreferredSize(new Dimension(700, 500));
        add(treePanel, BorderLayout.WEST);
        add(treePanelI, BorderLayout.EAST);
        
        ControlPanel controlPanel = new ControlPanel(binaryTreeS, binaryTreeI, treePanel, treePanelI);
        add(controlPanel, BorderLayout.SOUTH);        
        
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("GUI Tree Assignment 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RBTree assignment2 = new RBTree();
        assignment2.buildTree(40);
        frame.setContentPane(assignment2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void buildChildren(int start, int end, BinarySearchTree<Integer> binaryTree) {
        if (start < end + 1) {
            int mid = start + (end - start) / 2; 
            binaryTree.insert(mid);
            buildChildren(start, mid - 1, binaryTree);
            buildChildren(mid + 1, end, binaryTree);
        }
    }

    private  BinarySearchTree<Integer> buildTree(int n) {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        buildChildren(1, n - 1, binaryTree);
        return binaryTree;
    }
}

import Bin.BinarySearchTree;
import Bin.BinarySearchTree.Node;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author 1244821 Gabriela Orellana
 * @param <E>
 */

public class ControlPanel< E extends Comparable<? super E>> extends JPanel implements ActionListener 
{
    
    private final JLabel lblKey, lblValue;
    private final JTextField txtKey, txtValue;
    private final JButton insert, remove, search, 
            leftRotate, rightRotate, exit, change;
    private final BinarySearchTree<String> binaryTree;
    private final BinarySearchTree<Integer> binaryTreeI;
    private final GUITree treePanel, treePanelI;
    
    public ControlPanel(BinarySearchTree binaryTree, BinarySearchTree binaryTreeI, 
            GUITree treePanel, GUITree treePanelI) 
    {
        super(new GridLayout(3,4));
        
        this.binaryTree = binaryTree;
        this.binaryTreeI = binaryTreeI;
        this.treePanel = treePanel;
        this.treePanelI = treePanelI;
        
        lblKey = new JLabel("Key");
        lblKey.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblKey);
        
        txtKey = new JTextField();
        add(txtKey);
        
        lblValue = new JLabel("Value");
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValue);
        
        txtValue = new JTextField();
        add(txtValue);
        
        insert = new JButton("Insert");
        insert.addActionListener(this);
        add(insert);
        
        remove = new JButton("Remove");
        remove.addActionListener(this);
        add(remove);
        
        search = new JButton("Search");
        search.addActionListener(this);
        add(search);
        
        change = new JButton("Change");
        change.addActionListener(this);
        add(change);
        
        add(Box.createRigidArea(new Dimension(0,0)));
        
        leftRotate = new JButton("Left Rotate");
        leftRotate.addActionListener(this);
        add(leftRotate);
        
        rightRotate = new JButton("Right Rotate");
        rightRotate.addActionListener(this);
        add(rightRotate);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == insert) 
        {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() ) {
                Node<String> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    binaryTree.insert(node.getValue());
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                if (node2 != null) 
                {
                    binaryTree.insert(node2.toString());
                    treePanelI.repaint();
                }
            }  
            
        } else if (e.getSource() == change) 
        {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() ) {
                Node<String> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    binaryTree.change(node);
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                if (node2 != null) 
                {
                    //binaryTree.change(node2.getValue());
                    treePanelI.repaint();
                }
                 
            }
        } else if (e.getSource() == remove ) {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() ) {
                Node<String> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    binaryTree.delete(node);
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                
                if (node2 != null) 
                {
                    //binaryTree.delete(node2);
                    treePanelI.repaint();
                }
            }

        } else if (e.getSource() == search ) {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() ) {
                Node<String> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    treePanel.setCurrentNode(node);
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                if (node2 != null) 
                {
                    treePanelI.setCurrentNode(node2);
                    treePanelI.repaint();
                }
            }  
        } else if (e.getSource() == leftRotate) {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() ) {
                Node<String> node = binaryTree.search(txtKey.getText());
                if (node != null) {
                    binaryTree.leftRotate(node);
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                if (node2 != null) 
                {
                    //binaryTree.leftRotate(node2);
                    treePanelI.repaint();
                }
            }
        } else if (e.getSource() == rightRotate) 
        {
            if (!txtKey.getText().isEmpty() ||!txtValue.getText().isEmpty() )
            {
                Node<String> node1 = binaryTree.search(txtKey.getText());
                if (node1 != null) 
                {
                    binaryTree.rightRotate(node1);
                    treePanel.repaint();
                }
                
                Node<Integer> node2 = binaryTreeI.search(Integer.parseInt(txtValue.getText()));
                if (node2 != null) 
                {
                    //binaryTree.rightRotate(node2);
                    treePanelI.repaint();
                }
            }
        }
    }
    
}


import Bin.BinarySearchTree;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Objects;
import javax.swing.JPanel;
import Bin.BinarySearchTree.Node;

/**
 *
 * @author 1244821 Gabriela Orellana
 * @param <E>
 */
public class GUITree <E extends Comparable<? super E>> extends JPanel 
{
    private final BinarySearchTree<E> binaryTree;
    private int nodeRadius, verticalGap;
    private Node<E> currentNode;

    public GUITree(BinarySearchTree<E> binaryTree) 
    {
        this.binaryTree = binaryTree;
        currentNode = binaryTree.getRoot();
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        int gap = 50;
        verticalGap = (int) ((getHeight() - gap) / (binaryTree.getHeight() * 0.9));
        nodeRadius = (int) (verticalGap * 0.175);
        int horizontalGap = (int) (getWidth() / 4);
        if (binaryTree.getRoot() != null) {
            drawTree(g, binaryTree.getRoot(), getWidth() / 2, gap, horizontalGap);
        }
    }

    private void drawTree(Graphics g, Node<E> node, int x, int y, int horizontalGap) 
    {
        if (node == getCurrentNode()) 
        {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        
        Font nodeFont = getFont("SansSerif", Font.BOLD, nodeRadius);
        g.setFont(nodeFont);
        
        FontMetrics nodeFontMetrics = g.getFontMetrics(nodeFont);
        int stringWidth = nodeFontMetrics.stringWidth(node.toString());
        int stringHeight = nodeFontMetrics.getHeight();
        
        g.setColor(Color.WHITE);
        g.drawString(node.toString(), x - stringWidth / 2,
                y + stringHeight / 3);
        
        double distance = Math.sqrt(verticalGap * verticalGap + (x - (x
                - horizontalGap)) * (x - (x - horizontalGap)));
        g.setColor(Color.BLACK);
        
        if (node.getLeftChild() != null) 
        {
            g.drawLine((int) ((x - horizontalGap) + nodeRadius * (x - (x
                    - horizontalGap)) / distance),
                    (int) ((y + verticalGap) - nodeRadius * verticalGap / distance),
                    (int) (x - nodeRadius * (x - (x - horizontalGap)) / distance),
                    (int) (y + nodeRadius * verticalGap / distance));
            drawTree(g, node.getLeftChild(), x - horizontalGap, y + verticalGap,
                    (int) (horizontalGap / 2));
        }

        if (node.getRightChild() != null) 
        {
            g.drawLine((int) ((x + horizontalGap) - nodeRadius * ((x + horizontalGap) - x) / distance),
                    (int) ((y + verticalGap) - nodeRadius * verticalGap / distance),
                    (int) (x + nodeRadius * ((x + horizontalGap) - x) / distance),
                    (int) (y + nodeRadius * verticalGap / distance));

            drawTree(g, node.getRightChild(), x + horizontalGap, y
                    + verticalGap, (int) (horizontalGap / 2));
        }
    }

    public Node<E> getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node<E> currentNode) {
        this.currentNode = currentNode;
    }

    public Font getFont(String name, int style, int height) {
        int size = height;
        Boolean up = null;
        while (true) {
            Font font = new Font(name, style, size);
            int testHeight = getFontMetrics(font).getHeight();
            if (testHeight < height && !Objects.equals(up, Boolean.FALSE)) {
                size++;
                up = Boolean.TRUE;
            } else if (testHeight > height && !Objects.equals(up, Boolean.TRUE)) {
                size--;
                up = Boolean.FALSE;
            } else {
                return font;
            }
        }
    }
}

package Bin;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 1244821 Gabriela Orellana
 * @param <E>
 */

public class BinarySearchTree < E extends Comparable<? super E>>
{
    protected Node<E> root;
    protected int size;
    private TraversalType traversalType;

    public Node getRoot() 
    {
        return root;
    }
    
    public enum TraversalType
    {
        PREORDER, INORDER, POSTORDER, LEVELORDER
    }
    
    public BinarySearchTree()
    {
        root = null;
        size = 0;
        traversalType = TraversalType.INORDER;
    }
    
    public void setTraversalType(TraversalType traversalType)
    {
        this.traversalType = traversalType;
    }
    
    public Node<E> search(E value)
    {
        return binarySearch(root, value);
    }
    
    private Node<E> binarySearch(Node<E> node, E value)
    {
        if(node == null)
        {
            return null;
        }
        if(node.getValue().compareTo(value) == 0)
        {
            return node;
        } else {
            if(node.getValue().compareTo(value) < 0)
            {
                return binarySearch(node.getRightChild(), value);
            } else {
                return binarySearch(node.getLeftChild(), value);
            }
        }
    }
    
    public void leftRotate(Node<E> x)
    {
        if(x.getRightChild() == null)
        {
            return;
        }
        Node<E> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if(y.getLeftChild() != null)
        {
            y.getLeftChild().setParent(x);
            
        }
        
        y.setParent(x.getParent());
        
        if(x.getParent() == null)
        {
            root = y;
        } else {
            if(x == x.getParent().getLeftChild())
            {
                x.getParent().setLeftChild(y);
            } else {
                x.getParent().setRightChild(y);
            }
        }
        
        y.setLeftChild(x);
        x.setParent(y);
    }
    
    public void rightRotate(Node<E> x)
    {
        if(x.getLeftChild() == null)
        {
            return;
        }
        Node<E> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        
        if(y.getRightChild() != null)
        {
            y.getRightChild().setParent(x);
            
        }
        
        y.setParent(x.getParent());
        
        if(x.getParent() == null)
        {
            root = y;
        } else {
            if(x == x.getParent().getRightChild())
            {
                x.getParent().setRightChild(y);
            } else {
                x.getParent().setLeftChild(y);
            }
        }
        
        y.setRightChild(x);
        x.setParent(y);
    }
    
    public Node<E> insert(E value)
    {
        Node<E> node = new Node(value);
        if(root == null)
        {
            root = node;
            
        } else {
            insert(node, root);
        }
        size++;
        return node;
    }
    
    private void insert(Node<E> node, Node<E> parent)
    {
        if(node.getValue().compareTo(parent.getValue()) <= 0)
        {
            if(parent.getLeftChild() == null)
            {
                parent.setLeftChild(node);
                node.setIsLeftChild(true);
                node.setParent(parent);
            } else {
                insert(node, parent.getLeftChild());
            }
        } else {
            if(parent.getRightChild() == null)
            {
                parent.setRightChild(node);
                node.setIsRightChild(true);
                node.setParent(parent);
            } else {
                insert(node, parent.getRightChild());
            }
        } 
    }
    
    public Node<E> delete(Node<E> node)
    {
        if(node.getLeftChild() == null && node.getRightChild() == null)
        {
            if(node == root)
            {
                root = null;
            } else if(node.isLeftChild())
            {
                node.getParent().setLeftChild(null);
            } else {
                node.getParent().setRightChild(null);
            }
        } else if(node.getRightChild() == null)
        {
            if(node == root)
            {
                root = node.getLeftChild();
            } else if (node.isLeftChild())
            {
                node.getParent().setLeftChild(node.getLeftChild()); 
            } else {
                node.getParent().setRightChild(node.getLeftChild());
            }
        } else if(node.getLeftChild() == null)
        {
            if (node == root)
            {
                root = node.getRightChild();
                
            } else if(node.isLeftChild())
            {
                node.getParent().setLeftChild(node.getRightChild());
            } else {
                node.getParent().setRightChild(node.getRightChild());
            }
        } else {
            Node<E> successor = getSuccessor(node);
            if(node == root)
            {
                root = successor;
            } else if(node.isLeftChild())
            {
                node.getParent().setLeftChild(successor);
            } else {
                node.getParent().setRightChild(successor);
            }
            
            successor.setLeftChild(node.getLeftChild());
        }
        
        node.setParent(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        
        node.setIsRightChild(false);
        node.setIsLeftChild(false);
        size--;
        return node;
    }
    
    public Node<E> change(Node<E> node)
    {
        delete(node);
        insert(node.getValue());
        
        return node;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int getHeight()
    {
        return heightOfBinaryTree(root);
    }
    
    private int heightOfBinaryTree(Node<E> node)
    {
        if(node == null)
        {
            return 0;
        } else {
            return (1 + Math.max(heightOfBinaryTree(node.getLeftChild()), 
                    heightOfBinaryTree(node.getRightChild())));
        }
    }
    
    @Override
    public String toString()
    {
        if(root == null)
        {
            return "";
            
        } else {
            switch(traversalType)
            {
                case INORDER:
                    return root.toString(TraversalType.INORDER);
                    
                case PREORDER:
                    return root.toString(TraversalType.PREORDER);
                    
                case POSTORDER:
                    return root.toString(TraversalType.POSTORDER);
                    
                case LEVELORDER:
                    return root.toString(TraversalType.LEVELORDER);
                    
                default:
                    return "";
            }
        }
    }
    
    private Node<E> getSuccessor(Node<E> node)
    {
        Node<E> successorParent = node;
        Node<E> successor = node;
        Node<E> current = node.getRightChild();
        while(current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        if(successor != node.getRightChild())
        {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }
        
        return successor;
    }
    
    
    
    public static class Node< E extends Comparable<? super E>>
    {
        private E value;
        private Node<E> rightChild;
        private Node<E> leftChild;
        private Node<E> parent;
        private boolean isLeftChild;
        private boolean isRightChild;
        
        public Node(E value)
        {
            this.value = value;
            rightChild = null;
            leftChild = null;
            parent = null;  
            isRightChild = false;
            isLeftChild = false;
        }
        
        public boolean isLeftChild()
        {
            return isLeftChild;
        }
        
        public void setIsLeftChild(boolean isLeftChild)
        {
            this.isLeftChild = isLeftChild;
            this.isRightChild = !isLeftChild;
        }
        
        public boolean isRightChild()
        {
            return isRightChild;
        }
        
        public void setIsRightChild(boolean isRightChild)
        {
            this.isRightChild = isRightChild;
            this.isLeftChild = !isRightChild;
        }
        
        public Node<E> getParent()
        {
            return parent;
        }
        
        public void setParent(Node<E> parent)
        {
            this.parent = parent;
        }
        
        public E getValue()
        {
            return value;
        }
        
        public void setValue(E value)
        {
            this.value = value;
        }
        
        public Node<E> getRightChild()
        {
            return rightChild;
        }
        
        public void setRightChild(Node<E> rightChild)
        {
            this.rightChild = rightChild;
            if(rightChild != null)
            {
                rightChild.setIsRightChild(true);
                rightChild.setParent(this);
            }
        }
        
        public Node<E> getLeftChild()
        {
            return leftChild;
        }
        
        public void setLeftChild(Node<E> leftChild)
        {
            this.leftChild = leftChild;
            if(leftChild != null)
            {
                leftChild.setIsLeftChild(true);
                leftChild.setParent(this);
            }
        }
        
        @Override
        public String toString()
        {
            return value.toString();
        }
        
        private String toString(TraversalType traversalType)
        {
            String result = "";
            
            switch(traversalType)
            {
                case INORDER:
                    if(leftChild != null)
                    {
                        result += leftChild.toString(traversalType);
                    }
                result += value;
                    if(rightChild != null)
                    {
                        result += rightChild.toString(traversalType);
                    }
                break;
                    
                case POSTORDER:
                    if(leftChild != null)
                    {
                        result += leftChild.toString(traversalType);
                    }
                
                    if(rightChild != null)
                    {
                        result += rightChild.toString(traversalType);
                    }
                    result += value;
                    
                    break;
                    
                case PREORDER:
                    result += value;
                    if(leftChild != null)
                    {
                        result += leftChild.toString(traversalType);
                    }
                
                    if(rightChild != null)
                    {
                        result += rightChild.toString(traversalType);
                    }
                break;
                    
                case LEVELORDER:
                    Queue<Node<E>> level = new LinkedList<>();
                    level.add(this);
                    while(!level.isEmpty())
                    {
                        Node<E> node = level.poll();
                        result += node.value;
                        if(node.leftChild != null)
                        {
                            level.add(node.leftChild);
                        }
                        if(node.rightChild != null)
                        {
                            level.add(node.rightChild);
                        }
                    }
                
                break;
                    
            }
            
            return result;
        }
    }
    
}

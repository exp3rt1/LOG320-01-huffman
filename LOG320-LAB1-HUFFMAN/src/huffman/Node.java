package huffman;

import java.util.ArrayList;

public class Node{
    private Node left;
    private Node right;
    private Node parent;
    private Character item;
    private int nombreOccurences;
    
    public Node(){
    }
    
    public Node(Character item){
        this.item = item;
        nombreOccurences = item.getOccurence();
    }
    
    public Character getItem() {
        return item;
    }

    public void setItem(Character item) {
        this.item = item;
    }

    public int getNombreOccurences() {
        return nombreOccurences;
    }

    public void setNombreOccurences(int nombreOccurences) {
        this.nombreOccurences = nombreOccurences;
    }

    
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node createTree(Node node ,ArrayList<Character> characterList){
        ArrayList<Node> nodeList = new ArrayList<Node>();
        for(int i=0; i != characterList.size(); ++i){
            nodeList.add(new Node(characterList.get(i)));
        }
        
        return buildTree(nodeList, node);
    }
    
    private Node buildTree(ArrayList<Node> nodeList, Node node){
        if(nodeList.size() == 1) return node;
        int index1 = 0;
        int index2 = 1;
        Node parent = new Node();
        for(int i=0; i != nodeList.size(); ++i){
            if(i != index2 && nodeList.get(i).getNombreOccurences() < nodeList.get(index1).getNombreOccurences())
                index1 = i;
            
            if(i != index1 && nodeList.get(i).getNombreOccurences() < nodeList.get(index2).getNombreOccurences())
                index2=i;
        }
        
        if(nodeList.get(index1).getNombreOccurences() >= nodeList.get(index2).getNombreOccurences()){
            parent.setLeft(nodeList.get(index1));
            parent.setRight(nodeList.get(index2));
        }else{
            parent.setLeft(nodeList.get(index2));
            parent.setRight(nodeList.get(index1));
        }
        
        parent.setNombreOccurences(
                parent.left.getNombreOccurences() +
                parent.right.getNombreOccurences());
        
        if(nodeList.size()==1){
            nodeList.remove(0);
        }else{
            if(index1 > index2){
                int tmp = index1;
                index1 = index2;
                index2 = tmp;
            }
            nodeList.remove(index2);
            nodeList.remove(index1);
        }
        nodeList.add(parent);
        return buildTree(nodeList, parent);
    }


    public void CreateBinaryNumbers(Node node, StringBuffer binarySequence){
        if(node.left != null){
            binarySequence.append("0");
            CreateBinaryNumbers(node.getLeft(), binarySequence);
        }
        
        if(node.right != null){
            binarySequence.append("1");
            CreateBinaryNumbers(node.getRight(), binarySequence);
        }
        
        if(node.item != null){
            StringBuffer itemBinaire = new StringBuffer(binarySequence);
            node.item.setBinaire(itemBinaire);
        }
        
        if(binarySequence.length() >0)
            binarySequence.deleteCharAt(binarySequence.length()-1);
    }
}

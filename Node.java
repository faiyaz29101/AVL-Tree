public class Node {
    private int key;
    private int height;
    private Node left = null;
    private Node right = null;
    public Node(){
        height = 0;
    }

    public int getHeight() {
        return height;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    public void setHeight(int height) {
        this.height = height;

    }
    public void setHeight(){
        if(isLeaf()){
            height = 0;
        }
        else if(getLeft()==null){
            height = getRight().getHeight()+1;
        }
        else if(getRight()==null){
            height = getLeft().getHeight()+1;
        }
        else {
            height = max(getLeft().getHeight(), getRight().getHeight())+1;
        }
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeft(Node l) {
        this.left = l;
    }

    public void setRight(Node l) {
        this.right = l;
    }
    public boolean isLeaf(){
        if(right==null && left==null){
            return true;
        }
        else{
            return false;
        }
    }
    public int getBalance(){
        int l, r;
        if(left==null){
            l=0;
        }else{
            l = left.height+1;
        }
        if(right==null){
            r=0;
        }else{
            r = right.height+1;
        }
        return (l-r);
    }
    public void copy(Node n){
        this.left = n.getLeft();
        this.right = n.getRight();
        this.height = n.getHeight();
        this.key = n.getKey();
    }
    private int height(Node x){
        if(x==null||isLeaf()){
            return 0;
        }
        else{
            return height;
        }
    }
    private int max(int a, int b){
        if(a>b){
            return a;
        }
        else {
            return b;
        }
    }
}

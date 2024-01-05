import javax.crypto.spec.PSource;

public class AVL_Tree {
    private Node root = null;
    private int insert_tracker = 0;
    private int checkDel = 0;
    public StringBuffer Insert(int n){
        if(root == null){
            root = new Node();
            root.setKey(n);
        }
        else{
            insert(root, n);
        }
        
        StringBuffer sb = new StringBuffer();
        if(insert_tracker==-1){
            sb.append("Already Included");
            checkDel = 0;
        }else {
            print(sb, root);
            sb.append("\n");
        }
    //    System.out.print(sb);
        return sb;
    }
    private void insert(Node temp, int n){
        if(temp.getKey()==n){
            insert_tracker =-1;
            return;
        }
            if(n<temp.getKey()){
                if(temp.getLeft() == null){
                    Node t = new Node();
                    t.setKey(n);
                    temp.setLeft(t);
                }
                else{
                    insert(temp.getLeft(), n);
                }
            }
            else if(n>temp.getKey()){
                if(temp.getRight() == null){
                    Node t = new Node();
                    t.setKey(n);
                    temp.setRight(t);
                }
                else{
                    insert(temp.getRight(), n);
                }
        }
            if(temp.getRight() == null && temp.getLeft() != null){
                temp.setHeight(temp.getLeft().getHeight()+1);
            }
            else if(temp.getRight() != null && temp.getLeft() == null) {
                temp.setHeight(temp.getRight().getHeight()+1);
            }
            else if(temp.getRight() != null && temp.getLeft() != null) {
                temp.setHeight(max(temp.getLeft().getHeight()+1, temp.getRight().getHeight()+1));
            }
            if(abs(temp.getBalance()) >1) {
                temp = Balance(temp);
            }
    }

    private Node Balance(Node temp){
        if(temp.getBalance()>1 && temp.getLeft().getBalance()>0){
            return RightRightRotate(temp);
        }
        if(temp.getBalance()<-1 && temp.getRight().getBalance()<0){
            return LeftLeftRotate(temp);
        }

        if(temp.getBalance()<-1 && temp.getRight().getBalance()>0){
            return RightLeftRotate(temp);
        }
        if(temp.getBalance()>1 && temp.getLeft().getBalance()<0 ){
            return LeftRightRotate(temp);
        }
        else{
            return temp;
        }
    }
    private Node RightRightRotate(Node temp){
        Node z = new Node();
        Node y = new Node();
        Node x = new Node();
        z.copy(temp);
        y.copy(z.getLeft());
        x.copy(y.getLeft());
        temp.setKey(y.getKey());
        temp.setLeft(x);
        temp.setRight(z);
        temp.getRight().setLeft(y.getRight());
        temp.getRight().setRight(z.getRight());
        if(temp.getLeft()==null){
            temp.getLeft().setHeight(0);
        }else {
            temp.getLeft().setHeight();
        }
        if(temp.getRight()==null){
            temp.getRight().setHeight(0);
        }else{
            temp.getRight().setHeight();
        }
        temp.setHeight();
        return temp;
    }
    private Node LeftRightRotate(Node temp){
        Node z = new Node();
        Node y = new Node();
        Node x = new Node();
        z.copy(temp);
        y.copy(z.getLeft());
        x.copy(y.getRight());
        temp.setKey(x.getKey());
        temp.setLeft(y);
        temp.setRight(z);
        temp.getLeft().setLeft(y.getLeft());
        temp.getLeft().setRight(x.getLeft());
        temp.getRight().setLeft(x.getRight());
        temp.getRight().setRight(z.getRight());
        if(temp.getLeft()==null){
            temp.getLeft().setHeight(0);
        }else {
            temp.getLeft().setHeight();
        }
        if(temp.getRight()==null){
            temp.getRight().setHeight(0);
        }else{
            temp.getRight().setHeight();
        }
        temp.setHeight();
        return temp;
    }
    private Node RightLeftRotate(Node temp){
        Node z = new Node();
        Node y = new Node();
        Node x = new Node();
        z.copy(temp);
        y.copy(z.getRight());
        x.copy(y.getLeft());
        temp.setKey(x.getKey());
        temp.setLeft(z);
        temp.setRight(y);
        temp.getLeft().setLeft(z.getLeft());
        temp.getLeft().setRight(x.getLeft());
        temp.getRight().setLeft(x.getRight());
        temp.getRight().setRight(y.getRight());
        if(temp.getLeft()==null){
            temp.getLeft().setHeight(0);
        }else {
            temp.getLeft().setHeight();
        }
        if(temp.getRight()==null){
            temp.getRight().setHeight(0);
        }else{
            temp.getRight().setHeight();
        }
        temp.setHeight();
        return temp;
    }
    private Node LeftLeftRotate(Node temp){
        Node z = new Node();
        Node y = new Node();
        Node x = new Node();
        z.copy(temp);
        y.copy(z.getRight());
        x.copy(y.getRight());
        temp.setKey(y.getKey());
        temp.setLeft(z);
        temp.setRight(x);
        temp.getLeft().setLeft(z.getLeft());
        temp.getLeft().setRight(y.getLeft());
        if(temp.getLeft()==null){
            temp.getLeft().setHeight(0);
        }else {
            temp.getLeft().setHeight();
        }
        if(temp.getRight()==null){
            temp.getRight().setHeight(0);
        }else{
        temp.getRight().setHeight();
        }
        temp.setHeight();
        return temp;
    }
    public StringBuffer Delete(int n){
        StringBuffer sb = new StringBuffer();
        del(root, n);
        if(checkDel == -1){
            checkDel = 0;
            sb.append("Not Found\n");
            return sb;
        }
        
        print(sb, root);
        sb.append("\n");
    //    System.out.print(sb);
        return sb;
    }
    private void del(Node temp, int n){
        if(temp == null){
            checkDel = -1;
            return;
        }
        if(n < temp.getKey()){
            if(temp.getLeft() == null){
                checkDel = -1;
                return;
            }
            if(temp.getLeft().isLeaf() && temp.getLeft().getKey()==n ){
                temp.setLeft(null);
                temp.setHeight();
            }
            else {
                del(temp.getLeft(), n);
                temp.setHeight();
            }
        }
        else if(n > temp.getKey()){
            if(temp.getRight() == null){
                checkDel = -1;
                return;
            }
            if(temp.getRight().isLeaf() && temp.getRight().getKey()==n){
                temp.setRight(null);
                temp.setHeight();
            }else {
                del(temp.getRight(), n);
                temp.setHeight();
            }
        }
        else{
            if(temp.getLeft()== null && temp.getRight() != null){
                Node t = temp.getRight();
                temp.setKey(t.getKey());
                temp.setRight(t.getRight());
                temp.setLeft(t.getLeft());
                temp.setHeight();
            }
            else if(temp.getLeft()!= null && temp.getRight() == null){
                Node t = temp.getLeft();
                temp.setKey(t.getKey());
                temp.setRight(t.getRight());
                temp.setLeft(t.getLeft());
                temp.setHeight();
            }
            else{
                if(temp.getRight().isLeaf()){
                    temp.setKey(temp.getRight().getKey());
                    temp.setRight(null);
                }
                else {
                    Node t = temp.getRight();
                    if (t.getLeft() != null) {
                        while (t.getLeft().getLeft() != null) {
                            t = t.getLeft();
                        }
                    }
                    if (t.getLeft() == null) {
                        temp.setKey(t.getKey());

                        del(t, t.getKey());
                    } else {
                        temp.setKey(t.getLeft().getKey());
                        del(t, t.getLeft().getKey());
                    }
                }
                temp.setHeight();

            }
        }
        if(abs(temp.getBalance()) >1) {
            temp = Balance(temp);
        }
    }
    public boolean Find(int n){
        Node temp = root;
        if(root == null){
            return false;
        }
        while(true){
            if(temp.getKey() == n){
                return true;
            }
            else if(n>temp.getKey()){
                if(temp.getRight() == null){
                    return false;
                }
                temp = temp.getRight();
            }
            else if(n<temp.getKey()){
                if(temp.getLeft() == null){
                    return false;
                }
                temp = temp.getLeft();
            }
        }
    }
    public StringBuffer Traversal(Node temp, StringBuffer sb){
        if(temp.getLeft() != null) {
            Traversal(temp.getLeft(), sb);
        }
        sb.append(temp.getKey()+" ");
        if(temp.getRight() != null) {
            Traversal(temp.getRight(), sb);
        }
        return sb;
    }
    private int max(int a, int b){
        if(a>b){
            return a;
        }
        else {
            return b;
        }
    }
    public StringBuffer print(StringBuffer sb,Node temp){
        sb.append(temp.getKey());
        if(!temp.isLeaf()){
            sb.append("(");
        }
        if(temp.getLeft() != null){
            print(sb, temp.getLeft());
        }
        if(temp.getRight() != null){
            sb.append(",");
            print(sb, temp.getRight());
        }
        if(!temp.isLeaf()){
            sb.append(")");
        }
        return sb;
    }

    public Node getRoot() {
        return root;
    }
    private int abs(int x){
        if(x<0){
            return x*(-1);
        }
        return x;
    }

}

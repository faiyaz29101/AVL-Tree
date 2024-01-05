import java.io.*;

public class Main {
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String OUTPUT_FILE_NAME = "out_avl.txt";
    private static final String OUTPUT_FILE_NAME1 = "report_avl.txt";

    public static void main(String[] args) throws IOException {
        double insert = 0;
        double delete = 0;
        double traverse = 0;
        double find = 0;
       // int n = 0;
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        BufferedWriter bwr = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME1));
        AVL_Tree avl_tree = new AVL_Tree();
        String line = br.readLine();
        StringBuffer Sb = new StringBuffer();
        StringBuffer Sbr = new StringBuffer();
        while(true) {
           // n+=1;
            //System.out.println(n);
            String[] commands = line.split(" ");
            if(commands[0].equals("F")){
                double start = System.nanoTime();
                if(avl_tree.Find(Integer.parseInt(commands[1]))){
                    Sb.append("Found\n");
                }else {
                    Sb.append("Not Found\n");
                }
                double end = System.nanoTime();
                find += (end-start);
            }
            else if(commands[0].equals("I")){
                double start = System.nanoTime();
                Sb.append(avl_tree.Insert(Integer.parseInt(commands[1])));
                double end = System.nanoTime();
                insert += (end-start);
            }
            else if(commands[0].equals("D")){
                double start = System.nanoTime();
                Sb.append(avl_tree.Delete(Integer.parseInt(commands[1])));
                double end = System.nanoTime();
                delete += (end-start);
            }
            else if(commands[0].equals("T")){
                double start = System.nanoTime();
                Sb = avl_tree.Traversal(avl_tree.getRoot(), Sb);
                double end = System.nanoTime();
                traverse += (end-start);
                Sb.append("\n");
            }
            line = br.readLine();
            if(line == null){
                break;
            }
        }
        bw.write(Sb.toString());
        Sbr.append("operation time(ms)"+"\n");
        Sbr.append("insert "+ insert/1000000+"\n");
        Sbr.append("delete "+ delete/1000000+"\n");
        Sbr.append("search "+ find/1000000+"\n");
        Sbr.append("trav "+ traverse/1000000+"\n");
        Sbr.append("total "+ (insert+delete+find+traverse)/1000000+"\n");
        bwr.write(Sbr.toString());
      //  System.out.println(Sbr);
        bwr.close();
        bw.close();
        }
    }
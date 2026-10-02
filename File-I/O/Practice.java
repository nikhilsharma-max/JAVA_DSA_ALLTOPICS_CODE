// package File-I.O;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Practice {
    public static void main(String[] args) throws Exception {
        File file = new File("NewFile.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(" Mai hu giyan");
        bw.close();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        System.out.println();
        br.close();
    }
}

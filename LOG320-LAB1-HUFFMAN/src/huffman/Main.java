package huffman;

import java.io.File;
import java.security.Timestamp;
import java.util.Scanner;
import java.util.Timer;


public class Main 
{

    public static void main(String[] args) 
    {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Compresser(c) ou Decompresser(d): ");
        String option = scanner.next();
        System.out.print("nom du fichier: ");
        long duration;
        
        
        File file = new File(scanner.next());
        
        if(true)
        {
            long startTime = System.currentTimeMillis();
        	Compression comp = new Compression();
        	comp.readFile(file);
        	comp.compress(file);
        	long endTime = System.currentTimeMillis();
            duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        }
        else
        {
        	Decompression.creationArbre();
        }
        
        
        System.out.println(duration);
    }
}
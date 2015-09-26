package huffman;

import java.io.File;
import java.util.Scanner;


public class Main 
{

    public static void main(String[] args) 
    {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Compresser(c) ou Decompresser(d): ");
        String option = scanner.next();
        System.out.print("nom du fichier: ");
        File file = new File(scanner.next());
        
        if(true)
        {
        	Compression comp = new Compression();
        	comp.readFile(file);
        	comp.Compress(file);
        }
        else
        {
        	Decompression.creationArbre();
        }
    }
}
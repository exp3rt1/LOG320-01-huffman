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
        String fichier = scanner.next();
        
        if(option.equals('c'))
        {
        	System.out.print("compresse");
        }
        else
        {
        	Decompression.creationArbre();
        }
    }
}
package huffman;

import java.io.File;
import java.util.Scanner;


public class Main 
{

    public static void main(String[] args) 
    {
    	Compression c = new Compression();
    	Decompression d = new Decompression();
    	// Scanner scanner = new Scanner(System.in);
        // System.out.print("Compresser(c) ou Decompresser(d): ");
        // String option = scanner.next();
        //System.out.print("nom du fichier: ");
        String fichier = "allo.txt";
        long date1, date2;
       
        if(true)
        {
        	// date1 = System.currentTimeMillis();
        	
        	c.etapeCompression(fichier);
        	
        	
        	// date2 = System.currentTimeMillis();
        	// System.out.print(date2 - date1);
        }
        /*else
        {
        	Decompression.creationArbre();
        }*/
    }
}
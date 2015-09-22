package huffman;

import java.io.File;
import java.util.Scanner;


public class Main 
{

    public static void main(String[] args) 
    {
    	// Scanner scanner = new Scanner(System.in);
        // System.out.print("Compresser(c) ou Decompresser(d): ");
        // String option = scanner.next();
        //System.out.print("nom du fichier: ");
        String fichier = "allo.txt";
        long date1, date2;
       
        if(true)
        {
        	date1 = System.currentTimeMillis();
        	Compression.lireFichier(fichier);
        	Compression.faireTrie();
        	ArbreBinaire a = new ArbreBinaire();
        	a.creationArbreBinaire(Compression.getTableFrequence());
        	date2 = System.currentTimeMillis();
        	System.out.print(date2 - date1);
        }
        /*else
        {
        	Decompression.creationArbre();
        }*/
    }
}
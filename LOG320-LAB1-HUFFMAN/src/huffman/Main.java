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
        //System.out.print("nom du fichier: ");
        String nomfichier = "fichier.txt";
        long date1, date2;
       
        if(option.equals("c"))
        {
        	// date1 = System.currentTimeMillis();
        	Compression c = new Compression(nomfichier);
        	c.etapeCompression();
        	
        	
        	// date2 = System.currentTimeMillis();
        	// System.out.print(date2 - date1);
        }
        else
        {
        	Decompression d = new Decompression(nomfichier);
        	d.lireEntete();
        	d.lireTexte(d.getTete());
        	d.ecrireTexte();
        }
    }
}
package huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompression 
{
	private DataInputStream dataIn = null;
	
	public void lireEntete()
	{
		System.out.print("creationArbre");
		try 
		{
			this.dataIn = new DataInputStream(new FileInputStream("file.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		int finFichier = 0;
		while(finFichier != -1)
		{
			try {
				
				finFichier = dataIn.read();
			} 
			catch (IOException e) 
			{
				finFichier = -1;
			}
		}
	}
	
	public void lireTexte()
	{
		System.out.print("insertion texte");
	}

}

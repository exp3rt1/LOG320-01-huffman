package huffman;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArbreBinaire 
{
	private Noeud tete;
	
	public ArbreBinaire()
	{
	}	
	
	public void creationArbreBinaire(ArrayList<Noeud> T)
	{
		while(T.size() != 1)
		{
			Noeud n = new Noeud('\u0000');
			
			n.setDroite(T.get(1));
			n.setGauche(T.get(0));
			T.set(0, n);
			T.remove(1);
			n.setFrequenceParent();
		
			trieParInsertion(T, 0);
		}
		
		this.setTete(T.get(0));
	}
	
	public void trieParInsertion(ArrayList<Noeud> T, int elementAInserer)
	{
		for (int i=1; i < T.size()-1; i++) 
		{
			if(T.get(elementAInserer).getFrequence() >= T.get(i).getFrequence())
			{
				Noeud temp = T.get(elementAInserer);
				T.set(elementAInserer, T.get(i));
				T.set(i, temp);
				elementAInserer = i;
			}
			else
				break;
		}
	}
	
	public void codeCaractere(ArrayList<Byte> T, Noeud n, int longueur)
	{		
		if(!n.estFeuille())
		{
			T.add((byte) 0);
			this.codeCaractere(T, n.getGauche(), longueur+1);
			T.add((byte) 1);
			this.codeCaractere(T, n.getDroite(), longueur+1);
		}
		else
		{
			Byte[] table = T.toArray(new Byte[T.size()]);
			n.setCode(table);
		}
	}
	
	public void codeArbre(DataOutputStream dataOut, Noeud n)
	{
		if(!n.estFeuille())
		{
			System.out.print("codage");
			// ecrire 0
			// a gauche
			// a droite
			
			try 
			{
			    dataOut.writeBytes("1000001");
			    
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			 
		}
		else
		{
			// ecrire 1
			// ecrire son code
		}
	}

	public Noeud getTete() 
	{
		return tete;
	}


	public void setTete(Noeud tete) 
	{
		this.tete = tete;
	}
}

package huffman;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
public class ArbreBinaire 
{
	private int nbFeuille = 0;
	private Noeud tete = null;
	private String nomFichier = "";
	private ModificationFichier m = null;
	private DataOutputStream dataOut = null;
	private HashMap<Character, Noeud> map = null;
	
	public ArbreBinaire(DataOutputStream dataOut, String nomFichier)
	{
		this.m = new ModificationFichier(dataOut);
		this.nomFichier = nomFichier;
	}	
	
	public void creationArbreBinaire(ArrayList<Noeud> T)
	{
		while(T.size() != 1)
		{
			Noeud n = new Noeud('\u0000');
			n.setDroite(T.get(0));
			n.setGauche(T.get(1));
			T.set(0, n);
			T.remove(1);
			n.setFrequenceParent();
		
			trieParInsertion(T, 0);
		}
		
		this.setTete(T.get(0));
	}
	
	public void trieParInsertion(ArrayList<Noeud> T, int elementAInserer)
	{
		for (int i=0; i < T.size(); i++) 
		{
			if(T.get(elementAInserer).getFrequence() > T.get(i).getFrequence())
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
	
	public void codeCaractere(String T, Noeud n)
	{		
		if(!n.estFeuille())
		{
			T += "0";
			this.codeCaractere(T, n.getGauche());
			T = T.substring(0,T.length()-1);
			T += "1";
			this.codeCaractere(T, n.getDroite());
		}
		else
		{
			n.setCode(T);
			map.get(n.getCaractere()).setCode(T);
			System.out.print(n.getCaractere() + " = " + n.getCode() + "\n");
		}
	}
	
	public void ecrireArbre(Noeud n)
	{
		if(!n.estFeuille())
		{
			// ecrire 0
			// a gauche
			// a droite
			
			this.ecrireBits(false);
			this.ecrireArbre(n.getGauche());
			this.ecrireArbre(n.getDroite());
			 
		}
		else
		{
			// ecrire 1
			// ecrire son code
			
			this.ecrireBits(true);
			this.ecrireCaractere((byte)n.getCaractere());
			
		}
	}
	
	public void ecrireNbFeuille(int n)
	{
		//System.out.print(n);
		this.ecrireCaractere(n);
	}
	
	public void ecrireBits(boolean bit)
	{		
		try 
		{
			m.writeBites(bit);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ecrireCaractere(int caractere)
	{		
		try 
		{
			m.writeCharactere((byte) caractere);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ecrireTexte()
	{		
		String text;
		try 
		{
			text = new String(Files.readAllBytes(Paths.get(this.nomFichier)), StandardCharsets.UTF_8);

			for(int i=0; i < text.length(); i++)
			{
				this.ecrireTableBits(map.get(text.charAt(i)).getCode());
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ecrireTableBits(String booleans)
	{		
		try 
		{
			m.writeString(booleans);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fermerFichier()
	{
		try 
		{
			m.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public Noeud getTete() {
		return tete;
	}

	public void setTete(Noeud tete) {
		this.tete = tete;
	}

	public int getNbFeuille() {
		return nbFeuille;
	}

	public void setNbFeuille(int nbFeuille) {
		this.nbFeuille = nbFeuille;
	}

	public DataOutputStream getDataOut() {
		return dataOut;
	}

	public void setDataOut(DataOutputStream dataOut) {
		this.dataOut = dataOut;
	}
	
	public void setMap(HashMap<Character, Noeud> map)
	{
		this.map = map;
	}
}

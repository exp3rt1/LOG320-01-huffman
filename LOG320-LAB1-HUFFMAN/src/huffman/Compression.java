package huffman;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Compression 
{
	private  ArrayList<Noeud> tableFrequence = new ArrayList<Noeud>();
	private ArbreBinaire arbre = new ArbreBinaire();
	
	public void lireFichier(String fichier)
	{
		HashMap<Character, Noeud> map = new HashMap<Character, Noeud>();
		
		try 
		{
			int c;
			Noeud n = null;
			File f = new File(fichier);
	        FileReader fr = new FileReader(f);
	        
	        while((c = fr.read()) != -1)
	        {
	        	char cle = (char)c;
	        	
	        	if(!map.containsKey(cle))
	        		map.put(cle, new Noeud(cle));
	        	else
	        	{
	        		n = map.get(cle);
	        		n.addFrequence();
	        	}
	        	
	        }
	        
	        fr.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Iterator<Entry<Character, Noeud>> it = map.entrySet().iterator();
		Noeud n = null;
		
	    while (it.hasNext()) 
	    {
	        Map.Entry<Character, Noeud> pair = (Map.Entry<Character, Noeud>)it.next();
	        n = (Noeud) pair.getValue();
	        tableFrequence.add(n);
	    }
	}
	
	public ArrayList<Noeud> getTableFrequence()
	{
		return tableFrequence;
	}
	
	/*************TRIE*************/
	
	public void faireTrie()
	{
		tri_rapide(tableFrequence, 0, tableFrequence.size()-1);
	}
	
	public void echangerValeur(ArrayList<Noeud> T, int val1, int val2)
	{		
		Noeud temp = T.get(val1);
		T.set(val1, T.get(val2));
		T.set(val2, temp);
	}
	
	// http://laure.gonnord.org/pro/teaching/AlgoProg1112_IMA/trirapide.pdf
	public int partitionner(ArrayList<Noeud> T, int premier, int dernier)
	{
	    int indice2 = dernier, indice1 = premier+1, pivot = premier;
	    
	    while(indice1 < indice2)
	    {
	    	if(T.get(indice1).getFrequence() <= T.get(pivot).getFrequence())
	    		indice1++;
	    	else
	    	{
	    		if(T.get(indice2).getFrequence() >= T.get(pivot).getFrequence())
	    			indice2--;
	    		else
	    		{
	    			echangerValeur(T, indice1, indice2);
	    			indice1++;
	    			indice2--;
	    		}
	    	}
	    }
	    
	    if(indice1 == indice2)
	    {
	    	if(T.get(indice1).getFrequence() <= T.get(pivot).getFrequence())
	    		indice2++;
	    	else
	    		indice1--;
	    }
	    else
	    {
	    	indice1--;
	    	indice2++;
	    }
	    
	    echangerValeur(T, premier, indice1);
	    return indice1;
	}

    public void tri_rapide(ArrayList<Noeud> T, int premier, int dernier)
    {
    	int pivot;
    	
    	if(premier == dernier-1)
    	{
    		if(T.get(premier).getFrequence() > T.get(dernier).getFrequence())
    		{
    			echangerValeur(T, premier, dernier);
    		}
    	}
    	else
    	{
	        if(premier < dernier)
	        {
	            // int pivot = choix_pivot(T, premier, dernier);
	            pivot = partitionner(T, premier, dernier);
	            tri_rapide(T, premier, pivot-1);
	            tri_rapide(T, pivot+1, dernier);
	    	}
    	}
        
    }
    // Fin
    /********************************/
    
    public void etapeCompression(String fichier)
    {
    	this.lireFichier(fichier);
    	this.faireTrie();
    	arbre.creationArbreBinaire(this.getTableFrequence());
    	arbre.codeCaractere(new ArrayList<Byte>(), arbre.getTete(), 0);
    	
    	DataOutputStream dataOut = null;
    	
    	try 
    	{
			dataOut = new DataOutputStream(new FileOutputStream("file.txt"));
			arbre.codeArbre(dataOut, arbre.getTete());
		} 
    	catch (FileNotFoundException e) 
    	{
			e.printStackTrace();
		}
    }
}

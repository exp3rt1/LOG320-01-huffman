package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Compression 
{
	private static HashMap<Character, Noeud> map = new HashMap<Character, Noeud>();
	private static ArrayList<Noeud> tableFrequence = new ArrayList<Noeud>();
	// private static long date1, date2;
	
	public static void creationArbre()
	{
		
	}
	
	public static void lireFichier(String fichier)
	{
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
	
	public static ArrayList<Noeud> getTableFrequence()
	{
		return tableFrequence;
	}
	
	/*************TRIE*************/
	
	public static void faireTrie()
	{
		tri_rapide(tableFrequence, 0, tableFrequence.size()-1);
	}
	
	public static void echangerValeur(ArrayList<Noeud> T, int val1, int val2)
	{		
		Noeud temp = T.get(val1);
		T.set(val1, T.get(val2));
		T.set(val2, temp);
	}
	
	// http://laure.gonnord.org/pro/teaching/AlgoProg1112_IMA/trirapide.pdf
	public static int partitionner(ArrayList<Noeud> T, int premier, int dernier)
	{
	    int j = dernier, i = premier+1, pivot = premier;
	    
	    while(i<j)
	    {
	    	if(T.get(i).getFrequence() <= T.get(pivot).getFrequence())
	    		i++;
	    	else
	    	{
	    		if(T.get(j).getFrequence() >= T.get(pivot).getFrequence())
	    			j--;
	    		else
	    		{
	    			echangerValeur(T, i, j);
		        	i++;
		            j--;
	    		}
	    	}
	    }
	    
	    if(i == j)
	    {
	    	if(T.get(i).getFrequence() <= T.get(pivot).getFrequence())
	    		j++;
	    	else
	    		i--;
	    }
	    else
	    {
	    	i--;
	    	j++;
	    }
	    
	    echangerValeur(T, premier, i);
	    return i;
	}

    public static void tri_rapide(ArrayList<Noeud> T, int premier, int dernier)
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
    
    

}

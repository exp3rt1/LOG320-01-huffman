package huffman;

import java.util.ArrayList;

public class ArbreBinaire 
{
	private Noeud tete = null;
	
	
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
		
		this.tete = T.get(0);
	}
	
	public void trieParInsertion(ArrayList<Noeud> T, int elementAInserer)
	{
		for (int i=1; i < T.size()-1; i++) 
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
}

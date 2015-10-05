package huffman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Decompression 
{
	private DataInputStream dataIn = null;
	private int nbBits = 0;
	private byte caractere = 0;
	private byte lettre = 0;
	private Noeud tete = null;
	private int nombreFeuilles = 0;
	private int tailleTexte = 0;
	private int nbLettre = 0;
	private int nbFeuillesActuelles = 0;
	private String texte = "";
	
	public Decompression(String nomFichier)
	{
		try 
		{
			this.dataIn = new DataInputStream(new FileInputStream("file.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void lireEntete()
	{
		try 
		{
			this.nombreFeuilles = this.dataIn.read();
			//this.tailleTexte = this.dataIn.read();
			this.caractere = (byte) this.dataIn.read();
			this.lireEnteteCaractere(null);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void lireEnteteCaractere(Noeud n)
	{		
		try 
		{
			if(this.nombreFeuilles != this.nbFeuillesActuelles)
			{
				Noeud m = new Noeud('\u0000');
				
				if(n == null)
					this.setTete(m);
				
				if( (this.caractere & this.pow(2, 7-this.nbBits)) == this.pow(2, 7-this.nbBits) )
				{
					this.nbBits = 0;
					this.caractere = (byte) this.dataIn.read();
					
					m.setCaractere((char)this.caractere);
					this.nbFeuillesActuelles++;
					this.lettre = 0;
					this.caractere = (byte) this.dataIn.read();
					
					if(n != null)
					{
						if(n.getGauche() == null)
							n.setGauche(m);
						else if(n.getDroite() == null)
							n.setDroite(m);
					}
				}
				else
				{
					this.nbBits++;					
					this.verifierNbBits();
					
					if(n != null)
					{
						if(n.getGauche() == null)
							n.setGauche(m);
						else if(n.getDroite() == null)
							n.setDroite(m);
					}
					
					this.lireEnteteCaractere(m);					
					this.lireEnteteCaractere(m);
				}
			}
		} 
		catch (IOException e) 
		{
		}
	}
	
	public void lireTexte(Noeud n)
	{
		try 
		{
			if(this.caractere != -1)
			{
				if(!n.estFeuille())
				{
					if( (this.caractere & this.pow(2, 7-this.nbBits)) == this.pow(2, 7-this.nbBits) )
					{
						// droite
						this.nbBits++;
						this.verifierNbBits();
						
						this.lireTexte(n.getDroite());
					}
					else
					{
						// gauche
						this.nbBits++;
						this.verifierNbBits();						
						this.lireTexte(n.getGauche());
					}
				}
				else
				{
					System.out.print(n.getCaractere());
					this.texte += n.getCaractere();
					this.nbLettre++;
					this.nbBits = 0;
					
					this.caractere = (byte) this.dataIn.read();
					
					this.lireTexte(this.tete);
				}
			}
		}
		catch (IOException e)
		{
			System.out.print("allo");
		}
	}
	
	public void afficherArrbre(Noeud n)
	{
		if(!n.estFeuille())
		{
			this.afficherArrbre(n.getGauche());
			this.afficherArrbre(n.getDroite());
		}
		else
		{
			System.out.print("\n" + n.getCaractere() + " = " + (byte) n.getCaractere());
		}
	}
	
	public void ecrireTexte()
	{
		try 
		{
			DataOutputStream out = new DataOutputStream(new FileOutputStream("file1.txt"));
			out.writeBytes(this.texte);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int pow(int base, int exposant)
	{
		if(exposant == 0)
			return 1;
		else if(exposant == 1)
			return base;
		else
		{
			int valeur = 2;
			for(int i=1; i < exposant; i++)
			{
				valeur *= base;
			}
			
			return valeur;
		}
	}
	
	public void verifierNbBits() throws IOException
	{
		if(this.nbBits >= 8)
		{
			this.caractere = (byte) this.dataIn.read();
			this.nbBits = 0;
		}
	}

	public Noeud getTete() {
		return tete;
	}

	public void setTete(Noeud tete) {
		this.tete = tete;
	}

}


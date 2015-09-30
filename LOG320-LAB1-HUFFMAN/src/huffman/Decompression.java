package huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompression 
{
	private DataInputStream dataIn = null;
	private int nbBits = 0;
	private int nbBitsLettre = 0;
	private byte caractere = 0;
	private byte lettre = 0;
	private Noeud tete = null;
	private boolean estLettre = false;
	private int nombreFeuilles = 0;
	private int nbFeuillesActuelles = 0;
	
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
			this.caractere = (byte) this.dataIn.read();
			System.out.print(this.caractere + "\n");
			this.caractere = (byte) this.dataIn.read();
			System.out.print(this.caractere);
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
				if(this.nbBits == 8)
				{
					this.caractere = (byte) (this.dataIn.read() & 0xFF);
					this.nbBits = 0;
				}
				
				this.nbBits++;
				this.caractere = (byte) ((this.caractere << 1)& 0xFF);
				
				//System.out.print((this.caractere & 0xFF ) & 128);
				if( (this.caractere & 1) == 1 )
				{
					if(!this.estLettre)
					{
						this.estLettre = true;
					}
					else
					{
						this.lettre = (byte) ((this.lettre << 1)& 0xFF);
						this.lettre = (byte) ((this.lettre | 1)& 0xFF);
						this.nbBitsLettre++;
						
						if(this.nbBitsLettre == 8)
						{
							n.setCaractere((char)this.lettre);
							this.estLettre = false;
							this.nbFeuillesActuelles++;
						}
					}
				}
				else
				{
					// creer un noeud
					Noeud m = new Noeud('\u0000');
					
					if(n == null)
					{
						this.tete = m;
						n = m;
					}
					else
					{
						n.setGauche(m);
					}
					
					//this.lireEnteteCaractere(m);
					
					// creer un noeud
					m = new Noeud('\u0000');
					n.setDroite(m);
					
					//this.lireEnteteCaractere(m);
				}
			}
		} 
		catch (IOException e) 
		{
		}
	}
	
	public void lireTexte()
	{
		System.out.print("insertion texte");
	}

}

/*//lire lettre
for (int i=0; i < 8; i++) 
{
	this.nbBits++;
	this.caractere = (byte) (this.caractere << 1);					
	this.lettre = (byte) (this.lettre << 1);
	
	if( (this.caractere & 1) == 1 )
	{
		this.lettre = (byte) (this.lettre | 1);
	}
	
	if(this.nbBits == 8)
	{
		this.caractere = (byte) dataIn.read();
		this.nbBits = 0;
	}
}
n.setCaractere((char)this.lettre);*/

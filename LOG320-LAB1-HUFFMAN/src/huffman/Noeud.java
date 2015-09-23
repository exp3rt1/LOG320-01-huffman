package huffman;

import java.util.ArrayList;

public class Noeud  
{
	private char caractere;
	private int frequence = 1;
	private Noeud gauche = null;
	private Noeud droite = null;
	private Byte[] code = null;
	
	public Noeud(char c)
	{
		this.caractere = c;
	}
	
	public boolean estFeuille()
	{
		if(this.gauche == null && this.droite == null)
			return true;
		else
			return false;
	}

	public char getCaractere() 
	{
		return caractere;
	}

	public void setCaractere(char caractere) 
	{
		this.caractere = caractere;
	}

	public int getFrequence() 
	{
		return frequence;
	}

	public void addFrequence() 
	{
		this.frequence = this.frequence+1;
	}

	public Noeud getGauche() 
	{
		return gauche;
	}

	public void setGauche(Noeud gauche) 
	{
		this.gauche = gauche;
	}

	public Noeud getDroite() 
	{
		return droite;
	}

	public void setDroite(Noeud droite) 
	{
		this.droite = droite;
	}
	
	public void setFrequenceParent()
	{
		this.frequence = this.getGauche().getFrequence() + this.getDroite().getFrequence();
	}

	public Byte[] getCode() 
	{
		return code;
	}

	public void setCode(Byte[] code) 
	{
		this.code = code;
	}
}

package huffman;

import java.io.DataOutputStream;
import java.io.IOException;

public class ModificationFichier 
{
	private byte caractere = 0;
	private int nbBits = 0;
	private String nomFichier = "";
	private DataOutputStream dataOut = null;
	
	public ModificationFichier(DataOutputStream dataOut)
	{
		this.setDataOut(dataOut);
	}
	
	public void writeBites(boolean bit) throws IOException
	{
		if(bit)
		{
			this.caractere = (byte) (this.caractere | this.pow(2, 7-this.nbBits));
		}
		
		this.nbBits++;
		this.writeCharacter();		
	}
	
	public void writeCharactere(byte caractere) throws IOException
	{
		this.caractere = caractere;
		this.writeCharacter();
	}
	
	public void writeString(String booleans) throws IOException
	{
		for (int i = 0; i < booleans.length(); i++) 
		{
			if(i%8 == 0 && i != 0)
			{
				this.writeCharacter();
			}
			
			if(booleans.charAt(i) == '1')
			{
				this.caractere = (byte) (this.caractere | this.pow(2, 7-i%8));
			}
		}
		
		this.writeCharacter();
	}
	
	public void close() throws IOException
	{
		//this.dataOut.writeByte(this.caractere);
		this.dataOut.close();
	}
	
	public void writeCharacter() throws IOException
	{
		this.dataOut.writeByte(this.caractere);
		this.caractere = (byte) (this.caractere & 0);
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

	public DataOutputStream getDataOut() 
	{
		return dataOut;
	}

	public void setDataOut(DataOutputStream dataOut) 
	{
		this.dataOut = dataOut;
	}

	public String getNomFichier() 
	{
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) 
	{
		this.nomFichier = nomFichier;
	}
}

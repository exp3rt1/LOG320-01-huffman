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
		this.caractere = (byte) (this.caractere >> 1);
		
		if(bit)
		{
			this.caractere = (byte) (this.caractere | 1);
		}
		
		this.nbBits++;
		
		if(this.nbBits == 8)
		{
			this.dataOut.writeByte(this.caractere);
			this.caractere = 0;
			this.nbBits = 0;
		}
		
	}
	
	public void writeCharactere(byte caractere) throws IOException
	{
		System.out.print(caractere);
		for (int i = 0; i < 8; i++) 
		{			
			if((caractere & 1) == 1)
			{
				this.caractere = (byte) ((this.caractere | 128) & 0xFF);
			}
			
			System.out.print((this.caractere) + " ");
			this.caractere = (byte) ((this.caractere >> 1) );			
			caractere = (byte) ((caractere >> 1));

			this.nbBits++;
			
			if(this.nbBits == 8)
			{
				System.out.print(this.nbBits + "  " + (this.caractere) + "\n" );
				this.dataOut.writeByte(this.caractere);
				this.caractere = 0;
				this.nbBits = 0;
			}
		}
	}
	
	public void writeString(String booleans) throws IOException
	{
		for (int i = 0; i < booleans.length(); i++) 
		{
			this.caractere = (byte) (this.caractere >> 1);
			
			if(booleans.charAt(i) == '1')
			{
				this.caractere = (byte) (this.caractere | 1);
			}

			this.nbBits++;
			
			if(this.nbBits == 8)
			{
				this.dataOut.writeByte(this.caractere);
				this.caractere = 0;
				this.nbBits = 0;
			}
		}
	}
	
	public void close() throws IOException
	{
		while(this.nbBits > 0 && this.nbBits < 8)
		{
			this.caractere = (byte) (this.caractere >> 1);
			this.nbBits++;
		}
		
		this.dataOut.writeByte(this.caractere);		
		this.dataOut.close();
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

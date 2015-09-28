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
		this.dataOut.write(caractere);
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

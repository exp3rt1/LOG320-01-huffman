package huffman;

import java.io.DataOutputStream;
import java.io.IOException;

public class ModificationFichier 
{
	private int caractere = 0;
	private int nbBits = 0;
	private String nomFichier = "";
	private DataOutputStream dataOut = null;
	
	public ModificationFichier(DataOutputStream dataOut)
	{
		this.setDataOut(dataOut);
	}
	
	public void writeBites(boolean bit) throws IOException
	{
		this.caractere = this.caractere << 1;
		
		if(bit)
		{
			this.caractere = this.caractere | 1;
		}
		
		this.nbBits++;
		
		if(this.nbBits == 8)
		{
			this.dataOut.write(this.caractere);
			this.caractere = 0;
			this.nbBits = 0;
		}
		
	}
	
	public void writeCharactere(byte caractere) throws IOException
	{
		this.dataOut.write(caractere);
	}
	
	public void writeTableBytes(Boolean[] booleans) throws IOException
	{
		for (int i = 0; i < booleans.length; i++) 
		{
			this.caractere = this.caractere << 1;
			
			if(booleans[i])
			{
				this.caractere = this.caractere | 1;
			}

			this.nbBits++;
			
			if(this.nbBits == 8)
			{
				this.dataOut.write(this.caractere);
				this.caractere = 0;
				this.nbBits = 0;
			}
		}
	}
	
	public void close() throws IOException
	{
		while(this.nbBits < 8)
		{
			this.caractere = this.caractere << 1;
			this.nbBits++;
		}
		
		this.dataOut.write(this.caractere);		
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

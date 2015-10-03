package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compression {
    private ArrayList<Character> characterList;
    
    public Compression(){
        characterList = new ArrayList<Character>();
    }
    
    public ArrayList<Character> readFile(File file){
        try{
            InputStream inputstream = new FileInputStream(file);            
            int character = -2;
            character = inputstream.read();
            while(character != -1) {
              int number = findCharacter(character);
              if(number != -1){
                  characterList.get(number).setOccurence(characterList.get(number).getOccurence()+1);
              }
              else{
                  characterList.add(new Character(character, 1));
              }
              character = inputstream.read();
            }
            
            faireTrie();
            
            return characterList;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void compress(File file){
        Node node = new Node();
        StringBuffer binarySequence = new StringBuffer("");
        node = node.createTree(node, characterList);
        node.CreateBinaryNumbers(node, binarySequence);
        StringBuffer compressedFileHeader = new StringBuffer();
        
        try{
            InputStream inputstream = new FileInputStream(file);
            int character = -2;
            character = inputstream.read();
            while(character != -1) {
                int number = findCharacter(character);
                if(characterList.get(number).getOccurence() != 0){
                    compressedFileHeader.append(characterList.get(number).getBinaire());
                    characterList.get(number).setOccurence(characterList.get(number).getOccurence()-1);
                }
                character = inputstream.read();
            }
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath().concat(".compress"));
            compressedFileHeader.insert(0, createBinaryHeader());
            byte[] data = getBytes(compressedFileHeader);
            out.write(data);
            //File compressedFile = new File(compressedFileHeader.toString());
            out.close();
            
        }catch(FileNotFoundException e) {
            System.out.println(
                    "Unable to open file '" + 
                    file + "'");
            }
            catch(IOException e) {
                System.out.println(
                    "Error reading file '" 
                    + file + "'");
            }
    }
    
    public byte[] getBytes(StringBuffer bitString)
    {
        int offset = bitString.length() % 8;
        bitString.append(addZeros(offset));
        bitString.insert(0, integerToBinary(offset));
        
        byte[] data = new byte[bitString.length() / 8];
        
        for (int i = 0; i < data.length; i++)
        {
            for (int b = 0; b <= 7; b++)
            {
                data[i] |= (byte)((bitString.charAt(i * 8 + b) == '1' ? 1 : 0) << (7 - b));
            }
        }

        return data;
    }
    
    public StringBuffer createBinaryHeader(){
        StringBuffer stringBuffer = new StringBuffer();
        String characterBinary;
        String binaryLength;
        for(int i=0; i != characterList.size(); ++i){
            characterBinary = integerToBinary(characterList.get(i).getCharacterNumber());
            binaryLength = integerToBinary(characterList.get(i).getBinaire().length());
            stringBuffer.append(characterBinary+binaryLength+characterList.get(i).getBinaire());
        }
        
        stringBuffer.insert(stringBuffer.length(), "0000000011111111");
        
        return stringBuffer;
    }
    
    private String integerToBinary(int number){        
        String string = Integer.toBinaryString(number & 0xFF);
        String addedZeros = addZeros(8-string.length()).toString();
        string = addedZeros+string;
        return string;
    }
    
    private StringBuffer addZeros(int length){
        StringBuffer addedZeros = new StringBuffer();
        for(int i=0; i != length; ++i)
            addedZeros.append("0");
        return addedZeros;
    }
    
    private int findCharacter(int character){
        if(characterList.size() != 0){
            for(int i=0; i != characterList.size(); ++i){
                if(characterList.get(i).getCharacterNumber() == character)
                    return i;
            }
        }
        return -1;
    }

/*************TRIE*************/
    
    public void faireTrie()
    {
        tri_rapide(characterList, 0, characterList.size()-1);
    }
    
    public void echangerValeur(ArrayList<Character> T, int val1, int val2)
    {
        Character temp = T.get(val1);
        T.set(val1, T.get(val2));
        T.set(val2, temp);
    }
    
    // http://laure.gonnord.org/pro/teaching/AlgoProg1112_IMA/trirapide.pdf
    public int partitionner(ArrayList<Character> T, int premier, int dernier)
    {
        int indice2 = dernier, indice1 = premier+1, pivot = premier;
        
        while(indice1 < indice2)
        {
            if(T.get(indice1).getOccurence() <= T.get(pivot).getOccurence())
                indice1++;
            else
            {
                if(T.get(indice2).getOccurence() >= T.get(pivot).getOccurence())
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
            if(T.get(indice1).getOccurence() <= T.get(pivot).getOccurence())
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

    public void tri_rapide(ArrayList<Character> T, int premier, int dernier)
    {
        int pivot;
        
        if(premier == dernier-1)
        {
            if(T.get(premier).getOccurence() > T.get(dernier).getOccurence())
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
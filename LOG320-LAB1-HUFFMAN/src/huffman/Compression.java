package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compression {
    
    //TODO faire la tête du fichier compresser
    
    public ArrayList<Character> readFile(File file){
        try{
            InputStream inputstream = new FileInputStream(file);
            ArrayList<Character> characterList = new ArrayList<Character>();
            int character = -2;
            character = inputstream.read();
            while(character != -1) {
                int number = FindCharacter(characterList, character);
              if(number != -1){
                  characterList.get(number).setOccurence(characterList.get(number).getOccurence()+1);
              }
              else{
                  characterList.add(new Character(character, 1));
              }
              character = inputstream.read();
            }
            quickSort(characterList, 0, characterList.size()-1);
            
            return characterList;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void Compress(){
        
    }
    
    public void ChangeToBinary(Character[] characterArr){
        
    }
    
    // http://www.algolist.net/Algorithms/Sorting/Quicksort
    public int partition(ArrayList<Character> characterList, int left, int right)
    {
          int i = left, j = right;
          Character tmp;
          int pivotIndex = (left + right) / 2; 
          int pivot = characterList.get(pivotIndex).getOccurence();
         
          while (i <= j) {
                while (characterList.get(i).getOccurence() < pivot)
                      i++;
                while (characterList.get(j).getOccurence() > pivot)
                      j--;
                if (i <= j) {
                      tmp = characterList.get(i);
                      characterList.set(i, characterList.get(j));
                      characterList.set(i, tmp);
                      i++;
                      j--;
                }
          }
         
          return i;
    }
     
    public void quickSort(ArrayList<Character> characterList, int left, int right) {
          int index = partition(characterList, left, right);
          if (left < index - 1)
                quickSort(characterList, left, index - 1);
          if (index < right)
                quickSort(characterList, index, right);
    }
    // end of reference
    
    public int FindCharacter(ArrayList<Character> characterList, int character){
        for(int i=0; i != characterList.size()-1; ++i){
            if(characterList.get(i).getCharacterNumber() == character)
                return i;
        }
        return -1;
    }
    
    public void ChangeToBinary(ArrayList<Character> characterList){
        /*  regarder les charactères avec le plus petit nombre d'occurances
         *  et les mettres ensembles dans un arbre, et additionner le nombre d'occurances
         *  pour compléter l'arbre
        */
    }
}

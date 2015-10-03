package huffman;

public class Character {
    private int characterNumber;
    private int occurence;
    private StringBuffer binaire;
    
    public Character(int characterNumber, int occurence){
        this.characterNumber = characterNumber;
        this.occurence = occurence;
    }
    
    public Character(int characterNumber, StringBuffer binaire){
        this.characterNumber = characterNumber;
        this.binaire = binaire;
    }
    
    
    public int getOccurence(){
        return occurence;
    }
    
    public StringBuffer getBinaire(){
        return binaire;
    }
    
    public void setOccurence(int occurence){
        this.occurence = occurence;
    }
    
    public void setBinaire(StringBuffer binarySequence){
        this.binaire = binarySequence;
    }

    public int getCharacterNumber() {
        return characterNumber;
    }

    public void setCharacterNumber(int characterNumber) {
        this.characterNumber = characterNumber;
    }
    
    public int Compare(Character character){
        if(this.getOccurence() > character.getOccurence())
            return 1;
        else
            return 0;
    }
    
    public boolean equals(Character o){
        if (this.getCharacterNumber() == o.getCharacterNumber()){
                return true;
        }
        return false;
    }
    
    public int compare(Character o){
        if(this.getBinaire().length() > o.getBinaire().length())
            return 1;
        else if(this.getBinaire().length() < o.getBinaire().length())
            return -1;
        else
            return 0;
    }
}

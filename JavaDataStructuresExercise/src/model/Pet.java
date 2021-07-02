package model;

public class Pet implements Comparable<Pet>{							
    private String petName;

    public Pet(String name){
        this.petName=name;        
    }
	
    public Boolean matchFound(String name){
        return this.petName.equals(name);
    }

    public String toString(){
    	String petDetails = new String();
    	petDetails =this.petName;
        return petDetails;
    }
    
    public int compareTo(Pet pet2){
        Integer compareResult;        
        compareResult=this.petName.compareToIgnoreCase(pet2.petName);        
        return compareResult;
    }
}
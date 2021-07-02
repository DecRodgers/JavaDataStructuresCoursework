package model;

public class Product implements Comparable<Product>{							
    private String productName;
    private String productCode;
    private int stockQuantity;

    public Product(String name,String productCode,int stockQuantity){
        this.productName=name;
        this.productCode=productCode;
        this.stockQuantity=stockQuantity;
    }
	
    public String getProductCode(){
        return this.productCode;
    }

    public String toString(){
    	String petDetails = new String();    	
    	petDetails=String.format("%-5s%-10s%16s%-8s%6s%-9d","Name:",this.productName,"Product Code:",this.productCode,"Stock:",this.stockQuantity);
        return petDetails;
    }
    
    public int compareTo(Product product2){
        Integer compareResult;
        //If Name is the same, Code must differ 
        compareResult=this.productName.compareToIgnoreCase(product2.productName);        
        if (compareResult==0) {
        	compareResult=this.productCode.compareToIgnoreCase(product2.productCode);
        }
        return compareResult;
    }
}
package view;
import controller.BinarySearchTree;
import controller.BinarySearchTree;
import model.Pet;
import model.Product;


public class PetProductListTest {
	
    public static void main(String[] args) {
    	BinarySearchTree productPetList = new BinarySearchTree();    	    
    	Integer menuOption, productQuantity;    	
    	String petName ,productName, productCode;
    	do {
	    	String menuString = new String();        	    
	    	menuString +="\n---Please choose one of the following options---\n";        	
	    	menuString +="1. Add Pet\n";
	    	menuString +="2. Find Pet\n";
	    	menuString +="3. Remove Pet\n";
	    	menuString +="4. Show Pet List\n";
	    	menuString +="5. Add Product for Pet\n";
        	menuString +="6. Find Product for Pet\n";
        	menuString +="7. Remove Product for Pet\n";
        	menuString +="8. Show Product List for Pet\n";
        	menuString +="9. Show Full Product List\n";
	    	menuString +="0. Quit\n";        	
	        menuOption = getMenuOption(menuString);
	        switch (menuOption) {
	        	case 0: // Exit
	        		System.out.println("Exiting System\n");
	        		break;
	        	case 1:	//Add Pet
	        		petName = getValidString("Pet Name");
	        		try {
	        			productPetList.insert(new Pet(petName));
                        System.out.println("Pet '"+petName+"' : Added");
	        		} catch(BinarySearchTree.NotUniqueException e){
	        			System.out.println("Pet '"+petName+"' not added - Pet not unique, already in list");
                    }
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 2: //Find Pet
	        		petName = getValidString("Pet Name to Find");
	        		try{
	        			Comparable foundPet=productPetList.find(new Pet(petName));
                        System.out.println("'"+foundPet+"' : Found");
                    }
                    catch(BinarySearchTree.NotFoundException e){
                    	System.out.println("Pet '"+petName+"' not found : Pet not in list");
                    }
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 3: //Remove Pet
	        		petName = getValidString("Pet Name to Remove");
	                try{
	                	Comparable deletePet=productPetList.find(new Pet(petName));
	                	if(deletePet != null) {
	                		System.out.println("\n## WARNING ##\nRemoving '"+deletePet+"' will remove all associated Products for that Pet.");
	                	}
	                	boolean confirmDelete = userConfirm();
	                	if(!confirmDelete) {
	                		System.out.println("Pet '"+deletePet+"' Not Removed");
	                	} else {	                		
	                		productPetList.remove(new Pet(petName));        	
	                		System.out.println("'"+deletePet+"' : Removed");
	                	}
	                }
	                catch(BinarySearchTree.NotFoundException e){
	                	System.out.println("Pet '"+petName+"' not found : Pet not in list, and not Removed");
	                }
	                Input.getString("Press any key to Continue...");
	        		break;
	        	case 4: //Show Pet List	        		
	        		String fullPetList = new String();	        			        		
	        		fullPetList += productPetList;
	        		System.out.println(fullPetList);
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 5: //Add Product for Pet
	        		petName = getValidString("Pet Name to Add Products for");
	        		try{
	        			productPetList.find(new Pet(petName));
	        			productName = getValidString("Product Name");
	        			productCode = getValidString("Product Code");
	        			productQuantity = getProductQuantity();
	        			productPetList.insertProduct(new Pet(petName),new Product(productName,productCode,productQuantity));
	        			System.out.println("Product added for '"+petName+"'.");
                    } catch (BinarySearchTree.NotFoundException e){
                    	System.out.println("Pet '"+petName+"' not found : Pet not in list");
                    } catch (BinarySearchTree.NotUniqueException e){
                    	System.out.println("Product not unique: not added to Pet Product List");
                    }
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 6: //Find Product for Pet
	        		petName = getValidString("Pet Name to find Product for");
	        		Pet userPet = new Pet(petName);
	        		try{
	        			Comparable foundPet=productPetList.find(new Pet(petName));                        
	        			System.out.println("'"+foundPet+"' : Found");                       
	        		} catch (BinarySearchTree.NotFoundException e){
                    	System.out.println("Pet '"+petName+"' not found : Pet not in list");
                    }   
	        		productCode = getValidString("Product Code of Product to Find");
	        		try {	        			
	    	        	Comparable productToFind = productPetList.findProductByCode(new Pet(petName),productCode);
	    	        	System.out.println("Product Code found:\n"+productToFind);
	        		} catch (BinarySearchTree.NotFoundException e){
                    	System.out.println("Product '"+productCode+"' not found ");
	        		}
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 7: //Remove Product for Pet 
	        		petName = getValidString("Pet Name to remove Product for");	        		
	        		try{
	        			Comparable foundPet=productPetList.find(new Pet(petName));
                        System.out.println("'"+foundPet+"' : Found");     			        		
                    } catch (BinarySearchTree.NotFoundException e){
                    	System.out.println("Pet '"+petName+"' not found : Pet not in list");
                    }
	        		productCode = getValidString("Product Code of Product to Remove");
	        		try {                        
    	        		Comparable deleteProduct = productPetList.findProductByCode(new Pet(petName),productCode);
    	        		if(deleteProduct != null) {
    	        			System.out.println("\nPlease confirm Product removal.");
                        	boolean confirmDelete = userConfirm();
                        	if(!confirmDelete) {
                        		System.out.println("Product not Removed");
                        	} else {	                		
                        		productPetList.removeProductByCode(new Pet(petName),productCode);                		
                        	}	
    	        		}
    	        		System.out.println("Product with Code '"+productCode+"' : Removed");
	        		} catch (BinarySearchTree.NotFoundException e) {
	        			System.out.println("Product '"+productCode+"' not found ");
	        		}
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 8: //Show Products for Pet
	        		petName = getValidString("Pet Name to show all Products for");
	        		String petProducts = new String();
	        		try {
	        			petProducts = productPetList.displayProductsByPet(new Pet(petName));
	        		} catch (BinarySearchTree.NotFoundException e) {
	        			System.out.println("Pet '"+petName+"' not found : Pet not in list");
	        		}
	        		System.out.println(petProducts);
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	case 9: //Show all Pets and Products
	        		String fullProducts = new String();
	        		fullProducts += "### All Pets and Products ###\n";
	        		fullProducts += productPetList.displayAllProducts();
	        		System.out.println(fullProducts);
	        		Input.getString("Press any key to Continue...");
	        		break;
	        	default:
        			System.out.println("Not a valid menu option.\nPlease try again.\n");
        			break;
	        }
        } while (menuOption !=0);
    }
    
    //Private methods as they are relevant only to this Menu Class    
    private static Integer getMenuOption(String menu) {
		//implemented this to prevent full crash if a letter is entered instead of a number for menu
		Integer menuOption = null;
		boolean inputValid = false;
    	do{
    		try{        	 
    			System.out.println(menu);
    			menuOption = Input.getInteger("Please enter your option:");
    			inputValid = true;
    		}catch(NumberFormatException e){			
    			System.out.println("Entered value is not an integer.\nPlease try again.\n");
    		}
    	}while(!inputValid);
    	return menuOption;
	}
	
	private static String getValidString(String stringDetail) {
		class InputEmptyException extends Exception{}		
		boolean inputValid = false;
		String productString = null;
		
		do {
			try {
    			productString = Input.getString("Please enter "+stringDetail+": ");            	
            	if (productString.isEmpty() ==  true) {
            		throw new InputEmptyException();
            	}
            	inputValid= true;
			} catch (InputEmptyException e){
				System.out.println(stringDetail+" cannot be empty.\nPlease try again.\n");
			} 
		}while(!inputValid);		
		return productString;
	}   
	
    private static Integer getProductQuantity() {
		class InvalidStartingQuantity extends Exception{}
		Integer productQuantity = null;
		boolean inputValid = false;
		do {
			try {
				productQuantity = Input.getInteger("Please enter Product starting quantity: ");
				if (productQuantity < 0) {
					throw new InvalidStartingQuantity();
				}
				inputValid= true;
			} catch (InvalidStartingQuantity e){
				//Opted to choose 0 as Min. Quantity Requirement as you can be Pre-Notified of incoming Products
				System.out.println("Starting quantity cannot be less than 0.\nPlease try again.\n");
			} catch (NumberFormatException e){
    			System.out.println("Entered value is not an integer.\nPlease try again.\n");
    		}		
		}while(!inputValid);
		return productQuantity;
	}
    
    //Method Inspired by 'Repeat' Library class provided by university with additional exception handling
	private static boolean userConfirm() {
		Boolean confirmOption = null;
		Character userResponse;		
		do {
			try {
				userResponse = Input.getCharacter("Confirm (y/n)");
				if ((userResponse == 'Y') || (userResponse =='y')) {
					confirmOption = true;				
				} else if ((userResponse == 'N') || (userResponse =='n')) {
					confirmOption = false;				
				} else if (userResponse == null){
					System.out.println("Option not valid - Try again.");
				}
			}catch(NullPointerException e) {
				System.out.println("Option cannot be empty - Try again.");
			}	
		} while (confirmOption == null);		
		return confirmOption;
	}
	
}

package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import controller.BinarySearchTree;
import model.Pet;
import model.Product;


class JUnitTestPetProductList {

// Pet Tests	
	
	@Test
	void AddPetTest(){
		BinarySearchTree petTree = new BinarySearchTree();		
		try{
			petTree.insert(new Pet("Cat"));
		} catch(BinarySearchTree.NotUniqueException e) {
			fail("Pet not inserted");
		}		
		assertTrue(!((BinarySearchTree)petTree).isEmpty());
	}
	
	@Test
	void AddDuplicatePetFailTest() throws BinarySearchTree.NotUniqueException{
		BinarySearchTree petTree = new BinarySearchTree();	
		petTree.insert(new Pet("DOG"));			
		Assertions.assertThrows(BinarySearchTree.NotUniqueException.class, ()->{
			petTree.insert(new Pet("dog"));
		});
	}
	
	@Test
	void FindPet() throws BinarySearchTree.NotFoundException, BinarySearchTree.NotUniqueException {
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("Hamster"));
		Object foundPet = petTree.find(new Pet ("Hamster"));		
		assertNotNull(foundPet);		
		assertTrue(foundPet.toString().compareToIgnoreCase("hamster") == 0);
	}	

	@Test
	void FindPetNotInTree() throws BinarySearchTree.NotUniqueException {
		BinarySearchTree petTree = new BinarySearchTree();		
		petTree.insert(new Pet("Turtle"));
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.find(new Pet("Lizard"));
		});
	}
	
	@Test
	void RemovePet() throws BinarySearchTree.NotUniqueException, BinarySearchTree.NotFoundException {
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("TURTLE"));
		assertTrue(petTree.find(new Pet("TURTLE")) != null);
		petTree.remove(new Pet("turtle"));
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.find(new Pet("TURTLE"));
		});
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.find(new Pet("Ant"));
		});
	}
	
// Product Tests	
	@Test
	void AddPetProduct() throws BinarySearchTree.NotUniqueException, BinarySearchTree.NotFoundException {				
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("Guinea Pig"));		
		petTree.insertProduct(new Pet("Guinea Pig"), new Product("Food Bowl","BW001",1));
		
		Comparable foundProduct = petTree.findProductByCode(new Pet("GUINEA PIG"), "bw001");
		assertNotNull(foundProduct);				
	}
	
	@Test
	void AddProductforAbsentPet() throws BinarySearchTree.NotUniqueException{				
		BinarySearchTree petTree = new BinarySearchTree();
		
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.insertProduct(new Pet("Pig"), new Product("Trough","BW001",1));
		});			
	}
	
	@Test
	void AddDuplicateProductforPet() throws BinarySearchTree.NotFoundException, BinarySearchTree.NotUniqueException {						
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("Pig"));		
		petTree.insertProduct(new Pet("Pig"), new Product("Trough","BW001",1));
		
		Assertions.assertThrows(BinarySearchTree.NotUniqueException.class, ()->{
			petTree.insertProduct(new Pet("Pig"), new Product("Trough","BW001",1));		
		});		
	}
	
	
	
	@Test
	void FindPetAndFalsePetProduct() throws BinarySearchTree.NotUniqueException, BinarySearchTree.NotFoundException {
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("Guinea Pig"));
		
		petTree.insertProduct(new Pet("Guinea Pig"), new Product("Food Bowl","BW001",1));		
		Comparable foundProduct = petTree.findProductByCode(new Pet("GUINEA PIG"), "bw001");
		assertNotNull(foundProduct);
		
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.findProductByCode(new Pet("Pig"), "CP001");
		});		
	}
	
	@Test
	void RemovePetProduct() throws BinarySearchTree.NotUniqueException, BinarySearchTree.NotFoundException {
		BinarySearchTree petTree = new BinarySearchTree();
		petTree.insert(new Pet("Guinea Pig"));
		
		petTree.insertProduct(new Pet("Guinea Pig"), new Product("Food Bowl","BW001",1));
		Comparable foundProduct = petTree.findProductByCode(new Pet("Guinea Pig"), "BW001");
		assertNotNull(foundProduct);
		
		petTree.removeProductByCode(new Pet("Guinea Pig"),"BW001");
		Assertions.assertThrows(BinarySearchTree.NotFoundException.class, ()->{
			petTree.findProductByCode(new Pet("Guinea Pig"), "BW001");		
		});
	}
	
}

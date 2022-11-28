
 package com.infy.ekart.service.test;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.dto.CartProductDTO;
import com.infy.ekart.dto.CustomerCartDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.CartProduct;
import com.infy.ekart.entity.CustomerCart;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CustomerCartRepository;
import com.infy.ekart.service.CustomerCartService;
import com.infy.ekart.service.CustomerCartServiceImpl;



@SpringBootTest
class CustomerCartServiceTests {
	
	// Write testcases here
	
	@Mock
	private CustomerCartRepository customerCartRepository;

	@Mock
	private CustomerCartRepository CartProductRepository;

	@InjectMocks
	CustomerCartService customerCartService = new CustomerCartServiceImpl();
    @Test
	public void getAllProductsValid1() throws EKartException {

		// write your logic here
    	List<Product> products=new ArrayList<Product>();
    	Product product1=new Product();
    	product1.setProductId(1);
    	products.add(product1);
    	
    	Product product2=new Product();
    	product2.setProductId(2);;
    	products.add(product2);

	}
	@Test
	public void getAllProductsValid2() throws EKartException {
		
		// write your logic here

		Product product=new Product();
		product.setProductId(1234);


		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setProductId(1);
		products.add(product1);

		Product product2 = new Product();
		product2.setProductId(2);
		products.add(product2);


		
	}
    @Test
	public void addProductToCartValidTest() throws EKartException {

		// write your logic here
    	CustomerCartDTO c=new CustomerCartDTO();
    	Set<CartProductDTO> list=new HashSet<>();
    	CartProductDTO a=new CartProductDTO();
    	a.setQuantity(2);
    	ProductDTO p=new ProductDTO();
    	p.setProductId(222);
    	p.setQuantity(75);
    	p.setAvailableQuantity(8000);
    	a.setProduct(p);
    	list.add(a);
    	c.setCartProducts(list);
    	c.setCustomerEmailId("name@infosys.com");
    	CustomerCart cart=new CustomerCart();
    	cart.setCartId(100);
    	
    	CustomerCart rep=new CustomerCart();
    	rep.setCartId(10);
    	Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
    	Assertions.assertEquals(cart.getCartId(), rep.getCartId());
    	
	}
    @Test
	public void getProductFromCartInvalidTest1() throws EKartException {

		// write your logic here
    	String email = "name@infosys.com";
    	Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
    	EKartException exp=Assertions.assertThrows(EKartException.class,
    			()-> customerCartService.getProductsFromCart(email));
    	Assertions.assertEquals("CustomerCartService.NO_CART_FOUND",exp.getMessage());

	}
    @Test
	public void getProductFromCartInvalidTest2() throws EKartException {

		// write your logic here
    	String email="name@infosys.com";
    	CustomerCart customerCart=new CustomerCart();
    	customerCart.setCartId(2345);
    	customerCart.setCustomerEmailId(email);
    	Set<CartProduct> cartProductSet= new HashSet<>();
    	customerCart.setCartProducts(cartProductSet);
    	Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.of(customerCart));
    	EKartException exp= Assertions.assertThrows(EKartException.class,()->customerCartService.getProductsFromCart(email));
    	Assertions.assertEquals("CustomerCartService.NO_PRODUCT_ADDED_TO_CART",exp.getMessage());
    	
	}
	@Test
	public void deleteProductFromCartValid1() throws EKartException {

		// write your logic here
		String email="name@infosys.com";
		Integer productId=135;
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp =Assertions.assertThrows(EKartException.class, ()->customerCartService.deleteProductFromCart(email, productId));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND",exp.getMessage());
		
	}
    @Test
	public void deleteProductFromCartValid2() throws EKartException {

		// write your logic here
    	String email="name@infosys.com";
		Integer productId=135;
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());

		EKartException exp =Assertions.assertThrows(EKartException.class, ()->customerCartService.deleteProductFromCart(email, productId));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND",exp.getMessage());

		

		
		



 
	}
    @Test
	public void deleteProductFromCartInValidTest1() {

		// write your logic here
    	String email="name@infosys.com";
    	Integer productId=2345;
    	Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
    	EKartException exp=Assertions.assertThrows(EKartException.class,
    			()->customerCartService.deleteProductFromCart(email,productId));
    	Assertions.assertEquals("CustomerCartService.NO_CART_FOUND",exp.getMessage());
    	

		


	}
    @Test
	public void deleteProductFromCartInValidTest2() {

		// write your logic here
    	String email="name@infosys.com";
    	Integer productId=2345;
    	CustomerCart customerCart=new CustomerCart();
    	customerCart.setCustomerEmailId(email);
    	Set<CartProduct> cartProductSet=new HashSet<>();
    	customerCart.setCartProducts(cartProductSet);
    	Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.of(customerCart));
    	EKartException exp=Assertions.assertThrows(EKartException.class,()->customerCartService.deleteProductFromCart(email, productId));
    	Assertions.assertEquals("CustomerCartService.NO_PRODUCT_ADDED_TO_CART",exp.getMessage());
	}
	
	@Test
	public void modifyQuantityOfProductInCartInValidTest1() {
		String email = "name@infosys.com";
		Integer productId = 135;
		Integer quantity = 1;
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.modifyQuantityOfProductInCart(email, productId, quantity));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND", exp.getMessage());
	}
	
	@Test
	public void modifyQuantityOfProductInCartInvalidTest2() {
		String email = "name@infosys.com";
		Integer productId = 135;
		Integer quantity = 4;
		CustomerCart customerCart = new CustomerCart();
		customerCart.setCustomerEmailId(email);
		Set<CartProduct> cartProductSet = new HashSet<>();
		CartProduct cartProduct = new CartProduct();
		cartProduct.setProductId(132);
		cartProduct.setQuantity(87);
		cartProductSet.add(cartProduct);
		customerCart.setCartProducts(cartProductSet);
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString()))
				.thenReturn(Optional.of(customerCart));
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.modifyQuantityOfProductInCart(email, productId, quantity));
		Assertions.assertEquals("CustomerCartService.PRODUCT_ALREADY_NOT_AVAILABLE", exp.getMessage());

	}

	@Test
	public void modifyQuantityOfProductInCartInvalidTest3() {

		String email = "name@infosys.com";
		Integer productId = 135;
		Integer quantity = 4;
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.modifyQuantityOfProductInCart(email, productId, quantity));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND", exp.getMessage());
	}

	@Test
	public void modifyQuantityOfProductInCartInValidTest4() {
		String email = "name@infosys.com";
		Integer productId = 135;
		Integer quantity = 1;
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.modifyQuantityOfProductInCart(email, productId, quantity));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND", exp.getMessage());
	}

	@Test
	public void deleteAllProductsFromCartValidTest() {
		String email = "name@infosys.com";
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.deleteAllProductsFromCart(email));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND", exp.getMessage());
	}

	@Test
	public void deleteAllProductFromCartInValidTest1() {
		String email = "name@infosys.com";
		CustomerCart customerCart = new CustomerCart();
		customerCart.setCustomerEmailId(email);
		Set<CartProduct> cartProductSet = new HashSet<>();
		customerCart.setCartProducts(cartProductSet);
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString()))
				.thenReturn(Optional.of(customerCart));
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.deleteAllProductsFromCart(email));
		Assertions.assertEquals("CustomerCartService.NO_PRODUCT_ADDED_TO_CART", exp.getMessage());

	}

	@Test
	public void modifyQuantityOfProductInCartInValidTest2() {
		String email = "name@infosys.com";
		Integer productId = 135;
		Integer quantity = 4;
		CustomerCart customerCart = new CustomerCart();
		customerCart.setCustomerEmailId(email);
		Set<CartProduct> cartProductSet = new HashSet<>();
		customerCart.setCartProducts(cartProductSet);
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString()))
				.thenReturn(Optional.of(customerCart));
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.modifyQuantityOfProductInCart(email, productId, quantity));
		Assertions.assertEquals("CustomerCartService.NO_PRODUCT_ADDED_TO_CART", exp.getMessage());

	}	

	@Test
	public void deleteAllProductsFromCartIValidTest3() {
		String email = "name@infosys.com";
		Mockito.when(customerCartRepository.findByCustomerEmailId(Mockito.anyString())).thenReturn(Optional.empty());
		EKartException exp = Assertions.assertThrows(EKartException.class,
				() -> customerCartService.deleteAllProductsFromCart(email));
		Assertions.assertEquals("CustomerCartService.NO_CART_FOUND", exp.getMessage());
	}

}

package wipb.jsfcruddemo.web;


import org.h2.tools.Console;
import org.junit.Test;
import wipb.jsfcruddemo.web.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasketTest {

    private Basket basket = new Basket();
    private List<BasketProduct> basketProducts = new ArrayList<>();
    private User user = new User("login","psswd","email",true);
    private Product product1 = new Product("productName1","category1", BigDecimal.TEN);
    private Product product2 = new Product("productName2","category2", BigDecimal.ONE);
private Discount discount = new Discount();


    @Test
    public void checkBasketAmountTest(){
        for(int i=0;i<10;i++) {
            basket.addProduct(product1, BigDecimal.ONE,discount);
        }
        basketProducts=basket.getBasketProducts();
        assert(basket.getBasketProducts().size()==10);
    }

    @Test
    public void checkBasketRemoveMultipleOfSameProductTest(){
        basket.addProduct(product1, BigDecimal.ONE,discount);
        basket.addProduct(product1, BigDecimal.TEN,discount);
        basket.addProduct(product1, BigDecimal.ONE,discount);
        basket.removeProduct(product1);
        basketProducts=basket.getBasketProducts();
        assert(basket.getBasketProducts().size()==0);
    }

    @Test
    public void checkBasketRemoveWith2ProductsTest(){
        basket.addProduct(product2, BigDecimal.ONE,discount);
        basket.addProduct(product1, BigDecimal.ONE,discount);


        basket.removeProduct(product2);
        basketProducts=basket.getBasketProducts();
        assert(basket.getBasketProducts().size()==0);
    }

    @Test
    public void basketClearTest(){
        basket.addProduct(product2, BigDecimal.ONE,discount);
        basket.addProduct(product1, BigDecimal.ONE,discount);

        basket.clearBasket();

        assert(basket.getBasketProducts().size()==0);
    }

    @Test
    public void checkNumberOfProductsInBasket(){
       basket.addProduct(product1, BigDecimal.ONE,discount);
       basket.addProduct(product1, BigDecimal.TEN,discount);
       basket.addProduct(product2, BigDecimal.ONE,discount);
        basketProducts=basket.getBasketProducts();
       int totalAmount=0;
        for (BasketProduct basketProduct : basketProducts) {
            totalAmount += basketProduct.getNumberOfProductsInBasket().intValue();
        }
       basketProducts=basket.getBasketProducts();

       assert(totalAmount==12);
  }
}
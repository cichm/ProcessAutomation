package net.usermd.mcichon;

import net.usermd.mcichon.body.products.OrderItem;
import net.usermd.mcichon.body.products.OrderLine;
import net.usermd.mcichon.body.products.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static net.usermd.mcichon.body.products.Price.price;
import static net.usermd.mcichon.body.products.Weight.weight;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductsImplTest")
class AppTest implements TestLifecycleLogger, TestInterfaceDynamicTestsDemo {

    @Test
    @DisplayName("checkIfProductsAreSimilarTest")
    @TestFactory
    void checkIfProductsAreSimilarTest() {
        OrderLine orderLine = new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1);
        OrderLine orderLine1 = new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1);
        OrderLine orderLine2 = new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1);

        assertTrue(true == true);

        assertAll(
                "assert products are similar",
                () -> assertEquals(orderLine, new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1)),
                () -> assertEquals(orderLine1, new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1)),
                () -> assertEquals(orderLine2, new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1))
        );
    }

    @Test
    @DisplayName("checkIfProductsAreDifferent")
    @TestFactory
    void checkIfProductsAreDifferent() {
        OrderLine orderLine = new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1);

        assertFalse(true == false);

        assertAll(
                "did products are not similar",
                () -> assertNotEquals(orderLine, new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1)),
                () -> assertNotEquals(orderLine, new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1)),
                () -> assertNotEquals(orderLine, new OrderLine(new OrderItem(new Product("Ołówek", weight(0.2)), price(0.8)), 2))
        );
    }

    @Test
    @DisplayName("checkIfProductAreNotNull")
    @TestFactory
    void checkIfProductAreNotNull() {
        OrderLine orderLine = new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1);
        OrderLine orderLine1 = new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1);
        OrderLine orderLine2 = new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1);

        assertNotNull(orderLine);
        assertAll(
                "did products are not null",
                () -> assertNotNull(orderLine.getOrderItem().getPrice()),
                () -> assertNotNull(orderLine1.getOrderItem().getProduct().getName()),
                () -> assertNotNull(orderLine2.getOrderItem().getProduct().getWeight())
        );
    }

    @Test
    @DisplayName("getProductNamesAndCompare")
    void getProductNamesAndCompare() {
        OrderLine orderLine = new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1);
        OrderLine orderLine1 = new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1);
        OrderLine orderLine2 = new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1);

        assertAll(
                "did products names and compare",
                () -> assertThat(orderLine.getOrderItem().getProduct().getName()).isEqualTo("Ziemniaki"),
                () -> assertThat(orderLine1.getOrderItem().getProduct().getName()).isEqualTo("Szczypiorek"),
                () -> assertThat(orderLine2.getOrderItem().getProduct().getName()).isEqualTo("Słownik")
        );
    }
}
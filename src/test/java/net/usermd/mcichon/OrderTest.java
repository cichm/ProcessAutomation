package net.usermd.mcichon;

import net.usermd.mcichon.body.products.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static net.usermd.mcichon.body.products.Price.price;
import static net.usermd.mcichon.body.products.Weight.weight;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class OrderTest {

    @Mock
    private IOrder iOrder;

    private List<OrderLine> productsList = Arrays.asList(
            new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1),
            new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1),
            new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1)
    );

    private List<OrderLine> er213 = Arrays.asList(
            new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1),
            new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1),
            new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1)
    );

    @Before
    public void beforeEachTest() {
        MockitoAnnotations.initMocks(this);

        when(iOrder.getProducts()).thenReturn(productsList);
    }

    @Test
    public void addTwoNumbers() {
        Order order = new Order(productsList);
        List<OrderLine> result = order.getProducts();

        assertThat(result, is(er213));
    }
}

package pl.edu.wszib.order.application.order;

import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.application.product.ProductModuleInitialization;
import pl.edu.wszib.order.application.product.ProductSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.application.product.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFacadeTest {
    //T3: można usunąć pozycję
    //T4: można zwiększyć ilość dla pozycji
    //T5: można zmniejszyć ilość dla pozycji
    //T6: dodanie pozycji przedmiotu, który znajduje się już w zamówieniu powinno skutkować zwiększeniem jego ilości
    //T7: można ukończyć zamówienie
    //T8: nie można modyfikować ukończonego zamówienia
    //T9: wartość zamówienia powinna być sumą wartości pozycji, wartość pozycji to cena jednostkowa * ilość

    private OrderFacade orderFacade;

    @BeforeEach
    public void setup() {
        final ProductFacade productFacade = new ProductModuleInitialization().getProductFacade();
        orderFacade = new OrderModuleInitialization(productFacade).getProductFacade();
    }

    @Test
    public void should_be_able_to_create_order() {
        //given:

        //when:
        final OrderApi createdOrder = orderFacade.create().getOrder();

        //then:
        final OrderApiResult foundOrder = orderFacade.findById(createdOrder.getId());
        assertTrue(foundOrder.isSuccess());
    }

    @Test
    public void should_be_able_to_add_item_to_order() {
        //given:
        final String orderId = orderFacade.create().getOrder().getId();
        final String productToAdd = ProductSamples.CHOCOLATE.getId();

        //when:
        final OrderApiResult result = orderFacade.addItem(orderId, productToAdd, 1);

        //then:
        assertTrue(result.isSuccess());
        assertOrderContainsProduct(orderId, productToAdd);
    }

    private void assertOrderContainsProduct(final String orderId,
                                            final String productId) {
        final OrderApi order = orderFacade.findByIdOrThrow(orderId);
        boolean orderContainsProduct = order.containsProduct(productId);
        assertTrue(orderContainsProduct);
    }

    @Test
    public void should_be_able_to_remove_item_from_order() {
        //given:
        final String orderId = orderFacade.create().getOrder().getId();
        final String productToRemove = ProductSamples.CHOCOLATE.getId();
        orderFacade.addItem(orderId, productToRemove, 1);
        orderFacade.addItem(orderId, ProductSamples.COCA_COLA_ZERO.getId(), 1);

        //when:
        orderFacade.removeItem(orderId, productToRemove);

        //then
        assertOrderNotContainsProduct(orderId, productToRemove);
    }

    private void assertOrderNotContainsProduct(final String orderId,
                                               final String productId) {
        final OrderApi order = orderFacade.findByIdOrThrow(orderId);
        boolean orderNotContainsProduct = order.notContainsProduct(productId);
        assertTrue(orderNotContainsProduct);
    }
}

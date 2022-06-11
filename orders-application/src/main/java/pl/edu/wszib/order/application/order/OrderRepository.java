package pl.edu.wszib.order.application.order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {

    default Optional<Order> findById(String id) {
        return findById(OrderId.of(id));
    }

    Optional<Order> findById(OrderId id);

    Order save(Order order);

    Collection<Order> findAll(Integer page, Integer size, String sort);
}

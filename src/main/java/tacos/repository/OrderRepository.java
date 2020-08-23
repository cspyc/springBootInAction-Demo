package tacos.repository;

import tacos.model.Order;
import tacos.model.User;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository {
   Order save(Order order);

   List<Order> findByUserOrderByPlacedAtDesc(User user,Pageable pageable);
}

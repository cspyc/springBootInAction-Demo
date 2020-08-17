package tacos.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.model.Order;

import java.util.Date;
import java.util.List;

/**
 * @author pi
 * @date 2020/8/17 11:00:55
 */
public interface OrderSpringDataRepository extends CrudRepository<Order,Long> {
    List<Order> findByZip(String zip);

    List<Order> readOrderByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);

    @Query(" select o from Order o where o.city = 'Seattle'")
    List<Order> readOrdersDeliveredInSeattle();

}

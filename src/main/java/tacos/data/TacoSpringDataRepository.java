package tacos.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.User;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author pi
 * @date 2020/8/17 10:58:44
 */
public interface TacoSpringDataRepository extends CrudRepository<Taco,Long> {

    List<Taco> findAll(PageRequest pageRequest);
}

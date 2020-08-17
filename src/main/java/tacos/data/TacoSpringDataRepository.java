package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Taco;

/**
 * @author pi
 * @date 2020/8/17 10:58:44
 */
public interface TacoSpringDataRepository extends CrudRepository<Taco,Long> {
}

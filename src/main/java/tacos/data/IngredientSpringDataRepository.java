package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Ingredient;

/**
 * @author pi
 * @date 2020/8/17 10:54:23
 */
public interface IngredientSpringDataRepository extends CrudRepository<Ingredient,String> {
}

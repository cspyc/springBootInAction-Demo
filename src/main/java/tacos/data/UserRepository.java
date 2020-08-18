package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.model.User;

/**
 * @author pi
 * @date 2020/8/18 15:12:39
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String username);
}

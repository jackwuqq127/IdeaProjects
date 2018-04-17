package person.jack.dao;

import org.springframework.data.repository.CrudRepository;
import person.jack.bean.User;

public interface UserRepository extends CrudRepository<User,Long> {
}

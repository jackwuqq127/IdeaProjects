package person.jack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.jack.bean.User;
import person.jack.dao.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor=Exception.class)
    public void save(User user){ //新增或更改；执行更改，id字段必须有值，否则为新增
        userRepository.save(user);
    }

    public List<User> getAll() { //查找所有

        /**默认返回的是个迭代**/
        Iterable<User> userAll = userRepository.findAll();

        /*转换为 List 集合返回*/
        Iterator<User> iterator = userAll.iterator();
        List<User> userList = new ArrayList<User>();
        while (iterator.hasNext()) {
            userList.add(iterator.next());
        }
        return userList;
    }

    public User findOne(Long id){
        return userRepository.findOne(id);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void delete(Long id){
        userRepository.delete(id);
    }
}

package com.caching.redis.services.impl;

import com.caching.redis.dao.UsersDao;
import com.caching.redis.exception.UserNotFoundException;
import com.caching.redis.models.Users;
import com.caching.redis.services.framework.UserService;
import com.caching.redis.utilities.MessageUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UsersDao usersDao;
    private final MessageUtilities messageUtilities;

    @Override
    @Cacheable(value = "users", key = "#id") //this will try to fetch from the redis first if not exist will fetch from the DB
    public Users getUserById(Long id) {
        log.warn("i'm gonna to fetch from DB ");
        return usersDao.findById(id).orElseThrow( () -> new UserNotFoundException("USER_NOT_FOUND"));
    }

    @Override
    @CachePut(value = "users", key = "#user.id") //this will append the new user added to the redis
    public void addUser(Users user) {
        log.warn("i'm gonna save to DB ");
        usersDao.save(user);
    }

    @Override
    @CachePut(value = "users", key = "#user.id") // Update the cache after updating DB
    public void updateUser(Users user) {
        log.warn("I'm gonna update to DB");

        // Find existing user
        Users dbUser = usersDao.findById((long) user.getId())
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        // Copy updated fields instead of replacing object
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setAddress(user.getAddress());
        dbUser.setEducation(user.getEducation());

        // Save updated user
        usersDao.save(dbUser);
    }

    @Override
    @CacheEvict(value = "users", key = "#id") //this will work on delete the removed user from the Db Also To Be Deleted From Redis
    public void deleteUser(Long id) {
        log.warn("i'm gonna delete from DB ");
        usersDao.deleteById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersDao.findAll();
    }
}

package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    public User findByUid(int uid); // public added 11/27/16 b/c of persistence video circa 27:00
    
    public List<User> findAll(); // should this be public? public added 11/27/16 b/c of persistence video circa 27:00
    
    // TODO - add method signatures as needed
    		//User findByUsername(string username);  -- I noted this on 11/22/16es
    		//List<User> findAll(); -- I noted this on 11/22/16es
    public User findByUsername(String username);  //added 11/27/16es
}

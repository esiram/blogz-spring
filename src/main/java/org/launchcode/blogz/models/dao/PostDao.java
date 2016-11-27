package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
    public List<Post> findByAuthor(int authorId); // public added 11/27/16 b/c of persistence video circa 27:00
    
    // TODO - add method signatures as needed
	public Post findByUid(int uid); // added 11/27/16  // public added 11/27/16 b/c of persistence video circa 27:00
}

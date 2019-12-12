package be.ehb.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlogDAO extends CrudRepository<Blog, Integer> {


}

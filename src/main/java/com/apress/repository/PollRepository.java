package com.apress.repository;

import com.apress.domain.Poll;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface PollRepository extends CrudRepository<Poll, Long>{

}

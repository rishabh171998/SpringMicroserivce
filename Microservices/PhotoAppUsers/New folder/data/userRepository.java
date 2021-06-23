package com.appdev.photoapp.api.users.PhotoAppUsers.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends CrudRepository<userEntity,Long> {
}

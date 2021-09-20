package edu.anna.exchangepermissions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewPermissionRepository extends CrudRepository<UserPermission, UserPermissionId> {


}

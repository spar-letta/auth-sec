package auth.service;

import auth.dto.request.CreateRoleDTO;
import auth.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    Role createRole(CreateRoleDTO createRoleDTO) throws Exception;

    List<Role> getAllRoles(Pageable pageable);
}

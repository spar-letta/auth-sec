package auth.service;

import auth.dto.request.CreateRoleDTO;
import auth.entity.Role;
import auth.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(CreateRoleDTO createRoleDTO) throws Exception {
        Optional<Role> optionalRole = roleRepository.findByNameIgnoreCase(createRoleDTO.getName());
        if (optionalRole.isPresent()) {
            throw new Exception("Role already exists");
        }
        Role role = new Role();
        role.setName(createRoleDTO.getName());
        role.setDescription(createRoleDTO.getDescription());

        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles(Pageable pageable) {
        List<Role> roleList = roleRepository.findAllAndDeletedIsFalse();
        return roleList;
    }
}

package auth.web;

import auth.application.doc.Examples;
import auth.dto.request.CreateRoleDTO;
import auth.entity.Role;
import auth.service.RoleService;
import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/roles")
@Tag(name = "roles")
@RequiredArgsConstructor
@SecurityRequirement(name = "Jwt")
public class RoleRestController {
    private final RoleService roleService;


    @PostMapping
    @JsonView(BaseView.RoleView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.USER_OK_RESPONSE)))
    })
    public Role createUser(@RequestBody @Valid CreateRoleDTO createRoleDTO) throws Exception {
        return roleService.createRole(createRoleDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN')")
    @JsonView(BaseView.RoleView.class)
    @PageableAsQueryParam
    public List<Role> getAllRoles(@PageableDefault Pageable pageable) throws Exception {
        return roleService.getAllRoles(pageable);
    }
}

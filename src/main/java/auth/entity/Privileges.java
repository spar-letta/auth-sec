package auth.entity;

import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction(value = "deleted = false")
@Table(name = "privileges", schema = "public")
public class Privileges extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(BaseView.RoleView.class)
    private Long id;

    @Column(name = "name")
    @JsonView({BaseView.RoleView.class, BaseView.UserDetailedView.class})
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

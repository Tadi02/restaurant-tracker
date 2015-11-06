package restaurant.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import restaurant.auth.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max = 50)
    private String name;

    @Column(unique = true)
    @Size(min = 1, max = 100)
    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserRole permissionLevel;

    @OneToMany(mappedBy = "ratedUser", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Review> ratedRestaurants;

    public void addRestaurantReview(Review review){
        if(this.ratedRestaurants == null){
            this.ratedRestaurants = new ArrayList<>();
        }
        this.ratedRestaurants.add(review);
        review.setRatedUser(this);
    }

    public List<Review> getRatedRestaurants() {
        return ratedRestaurants;
    }

    public void setRatedRestaurants(List<Review> ratedRestaurants) {
        this.ratedRestaurants = ratedRestaurants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(getPermissionLevel().name()));
        return authorityList;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public UserRole getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(UserRole permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}


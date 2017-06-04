/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package security;

import domain.DomainEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity implements UserDetails {

    // Constructors -----------------------------------------------------------

    private static final long serialVersionUID = 7254823034213841482L;
    private String username;


    // Attributes -------------------------------------------------------------

    // UserDetails interface --------------------------------------------------
    private String password;
    private Collection<Authority> authorities;

    public UserAccount() {
        super();

        this.authorities = new ArrayList<Authority>();
    }

    @Size(min = 5, max = 32)
    @Column(unique = true)
    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Size(min = 5, max = 32)
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @NotEmpty
    @Valid
    @ElementCollection
    @Override
    public Collection<Authority> getAuthorities() {
        // WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
        return this.authorities;
    }

    public void setAuthorities(final Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(final Authority authority) {
        Assert.notNull(authority);
        Assert.isTrue(! this.authorities.contains(authority));

        this.authorities.add(authority);
    }

    public void removeAuthority(final Authority authority) {
        Assert.notNull(authority);
        Assert.isTrue(this.authorities.contains(authority));

        this.authorities.remove(authority);
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

}

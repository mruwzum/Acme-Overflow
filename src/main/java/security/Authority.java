/*
 * Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package security;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

    // Constructors -----------------------------------------------------------

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";


    // Values -----------------------------------------------------------------
    public static final String TEACHER = "TEACHER";
    public static final String BAN = "BAN";
    public static final String MODERATOR = "MODERATOR";
    private static final long serialVersionUID = 1L;
    private String authority;


    // Attributes -------------------------------------------------------------

    public Authority() {
        super();
    }

    public static Collection<Authority> listAuthorities() {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<Authority>();

        authority = new Authority();
        authority.setAuthority(Authority.ADMIN);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.USER);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.TEACHER);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.BAN);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.MODERATOR);
        result.add(authority);

        return result;
    }

    @NotBlank
    @Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.USER + "|" + Authority.TEACHER + "|" + Authority.BAN + "|" + Authority.MODERATOR + "$")
    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    // Equality ---------------------------------------------------------------

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (! this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));

        return result;
    }

}

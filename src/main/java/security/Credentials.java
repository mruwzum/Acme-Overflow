/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package security;

import javax.validation.constraints.Size;

public class Credentials {

    // Constructors -----------------------------------------------------------

    private String username;


    // Attributes -------------------------------------------------------------
    private String password;

    public Credentials() {
        super();
    }

    @Size(min = 5, max = 32)
    public String getUsername() {
        return this.username;
    }

    public void setJ_username(final String username) {
        this.username = username;
    }

    @Size(min = 5, max = 32)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}

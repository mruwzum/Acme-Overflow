/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package utilities;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import security.LoginService;

public abstract class AbstractTest {

    // Supporting services --------------------------------

    @Autowired
    private LoginService loginService;


    // Set up and tear down -------------------------------

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // Supporting methods ---------------------------------

    public void authenticate(final String username) {
        UserDetails userDetails;
        TestingAuthenticationToken authenticationToken;
        SecurityContext context;

        if (username == null)
            authenticationToken = null;
        else {
            userDetails = this.loginService.loadUserByUsername(username);
            authenticationToken = new TestingAuthenticationToken(userDetails, null);
        }

        context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);
    }

    public void unauthenticate() {
        this.authenticate(null);
    }

    public void checkExceptions(final Class<?> expected, final Class<?> caught) {
        if (expected != null && caught == null)
            throw new RuntimeException(expected.getName() + " was expected");
        else if (expected == null && caught != null)
            throw new RuntimeException(caught.getName() + " was unexpected");
        else if (expected != null && caught != null && ! expected.equals(caught))
            throw new RuntimeException(expected.getName() + " was expected, but " + caught.getName() + " was thrown");
    }

}

package com.example.StudentManagement;

import com.example.StudentManagement.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TokenGeneratorTest {

    @Autowired
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    public void tokenGenerator() {
        // Create a mock UserDetails object
        UserDetails mockUserDetails = User.builder()
                .username("duchiep")
                .password("Motconvit1?")
                .authorities("ROLE_STUDENT")
                .build();

        // Mock the Authentication object to return our mock UserDetails when getPrincipal() is called
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getPrincipal()).thenReturn(mockUserDetails);

        // Mock the behavior of authenticationManager to return our mocked Authentication object
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuthentication);

        // Authenticate and generate token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("duchiep", "Motconvit1?")
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken((com.example.StudentManagement.domain.User) userDetails);

        // Output the token for demonstration purposes (In real tests, you should assert expected values)
        System.out.println(token);

        // Assertions would go here to verify the expected outcomes, e.g., token is not null or empty
    }
}

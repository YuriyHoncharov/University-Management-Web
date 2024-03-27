package ua.com.foxminded.yuriy.schedulewebapp.repository.controller.guestcontrollers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.yuriy.schedulewebapp.controllers.guestcontrollers.LoginController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

   @Mock
   private Authentication authentication;

   @InjectMocks
   private LoginController loginController;

   private MockMvc mockMvc;

   @BeforeEach
   public void setUp() {
       
       mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
   }

   @Test
   public void testGetLoginForm_authenticated() throws Exception {
       when(authentication.isAuthenticated()).thenReturn(true);
       SecurityContextHolder.getContext().setAuthentication(authentication);

       mockMvc.perform(get("/login"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("profile/dashboard"));
   }

   @Test
   public void testGetLoginForm_unauthenticated() throws Exception {
       when(authentication.isAuthenticated()).thenReturn(false);
       SecurityContextHolder.getContext().setAuthentication(authentication);

       mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(forwardedUrl("authorization/login"));
   }
}
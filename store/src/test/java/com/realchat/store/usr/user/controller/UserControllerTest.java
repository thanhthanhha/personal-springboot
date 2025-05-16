package com.realchat.store.usr.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realchat.store.usr.user.dto.User;
import com.realchat.store.usr.user.dto.UserAuth;
import com.realchat.store.usr.user.dto.UserQuery;
import com.realchat.store.usr.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    private ObjectMapper objectMapper;
    private User mockUser;
    private UserQuery mockUserQuery;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        
        // Create a mock user
        mockUser = new User();
        mockUser.setUser_id("ABC123");
        mockUser.setName("Test User");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");
        
        // Create a mock user query
        mockUserQuery = UserQuery.builder()
                .build();
    }
    
    @Test
    public void testRegisterUser() throws Exception {
        // Arrange
        when(userService.registerUser(any(User.class))).thenReturn(1);
        
        // Act & Assert
        mockMvc.perform(post("/user/rest/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        
        verify(userService, times(1)).registerUser(any(User.class));
    }
    
    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        when(userService.updateUser(any(User.class), anyString())).thenReturn(mockUser);
        
        // Act & Assert
        mockMvc.perform(put("/user/rest/v1/ABC123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value("ABC123"));
        
        verify(userService, times(1)).updateUser(any(User.class), eq("ABC123"));
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        when(userService.deleteUser(anyString())).thenReturn(1);
        
        // Act & Assert
        mockMvc.perform(delete("/user/rest/v1/ABC123"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        
        verify(userService, times(1)).deleteUser("ABC123");
    }
    
    @Test
    public void testListUser() throws Exception {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(mockUser);
        when(userService.listUser(any(UserQuery.class))).thenReturn(userList);
        
        // Act & Assert
        mockMvc.perform(get("/user/rest/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserQuery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].user_id").value("ABC123"));
        
        verify(userService, times(1)).listUser(any(UserQuery.class));
    }
    
    @Test
    public void testGetUser_WithUserId() throws Exception {
        // Arrange
        when(userService.getUser(eq("ABC123"), isNull())).thenReturn(mockUser);
        
        // Act & Assert
        mockMvc.perform(get("/user/rest/v1/ABC123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value("ABC123"));
        
        verify(userService, times(1)).getUser(eq("ABC123"), isNull());
    }
    
    @Test
    public void testGetUser_WithEmail() throws Exception {
        // Arrange
        when(userService.getUser(isNull(), eq("test@example.com"))).thenReturn(mockUser);
        
        // Act & Assert
        mockMvc.perform(get("/user/rest/v1/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value("ABC123"));
        
        verify(userService, times(1)).getUser(isNull(), eq("test@example.com"));
    }
}
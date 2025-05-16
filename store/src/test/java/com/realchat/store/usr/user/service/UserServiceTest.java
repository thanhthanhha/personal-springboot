package com.realchat.store.usr.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.realchat.store.usr.user.dto.User;
import com.realchat.store.usr.user.dto.UserAuth;
import com.realchat.store.usr.user.dto.UserQuery;
import com.realchat.store.usr.user.repository.UserRepository;
import com.realchat.store.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;
    private UserQuery mockUserQuery;
    private UserAuth mockUserAuth;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        
        // Create a mock user
        mockUser = new User();
        mockUser.setUser_id("ABC123");
        mockUser.setName("Test User");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");
        
        // Create a mock user query
        mockUserQuery = UserQuery.builder()
                .user_id(Arrays.asList("ABC123"))
                .email(Arrays.asList("test@example.com"))
                .build();
        
        // Create a mock user auth
        mockUserAuth = UserAuth.builder()
                .user_id("ABC123")
                .email("test@example.com")
                .password("password123")
                .build();
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Arrange
        when(userRepository.registerUser(any(User.class))).thenReturn(1);
        
        // Act
        int result = userService.registerUser(mockUser);
        
        // Assert
        assertEquals(1, result);
        verify(userRepository, times(1)).registerUser(any(User.class));
        assertNotNull(mockUser.getPassword_salt(), "Password salt should be generated");
        assertNotEquals("password123", mockUser.getPassword(), "Password should be hashed");
    }
    
    @Test
    public void testRegisterUser_MissingName() throws Exception {
        // Arrange
        mockUser.setName(null);
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.registerUser(mockUser);
        });
        
        assertEquals("User name is required", exception.getMessage());
        verify(userRepository, times(0)).registerUser(any(User.class));
    }
    
    @Test
    public void testRegisterUser_MissingEmail() throws Exception {
        // Arrange
        mockUser.setEmail(null);
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.registerUser(mockUser);
        });
        
        assertEquals("Email is required", exception.getMessage());
        verify(userRepository, times(0)).registerUser(any(User.class));
    }
    
    @Test
    public void testRegisterUser_MissingPassword() throws Exception {
        // Arrange
        mockUser.setPassword(null);
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.registerUser(mockUser);
        });
        
        assertEquals("Password is required for registration", exception.getMessage());
        verify(userRepository, times(0)).registerUser(any(User.class));
    }
    
    @Test
    public void testUpdateUser_Success() throws Exception {
        // Arrange
        when(userRepository.updateUser(any(User.class), anyString())).thenReturn(mockUser);
        
        // Act
        User result = userService.updateUser(mockUser, "ABC123");
        
        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).updateUser(any(User.class), anyString());
    }
    
    @Test
    public void testUpdateUser_MissingUserId() throws Exception {
        // Arrange
        mockUser.setUser_id(null);
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.updateUser(mockUser, null);
        });
        
        assertEquals("User ID is required", exception.getMessage());
        verify(userRepository, times(0)).updateUser(any(User.class), anyString());
    }
    
    @Test
    public void testListUser_Success() throws Exception {
        // Arrange
        List<User> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userRepository.listUser(any(UserQuery.class))).thenReturn(mockUserList);
        
        // Act
        List<User> result = userService.listUser(mockUserQuery);
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).listUser(any(UserQuery.class));
    }
    
    @Test
    public void testListUser_NoSearchCriteria() throws Exception {
        // Arrange
        UserQuery emptyQuery = new UserQuery();
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.listUser(emptyQuery);
        });
        
        assertEquals("At least one search criteria is required", exception.getMessage());
        verify(userRepository, times(0)).listUser(any(UserQuery.class));
    }
    
    @Test
    public void testGetUser_Success() throws Exception {
        // Arrange
        List<User> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userRepository.listUser(any(UserQuery.class))).thenReturn(mockUserList);
        
        // Act
        User result = userService.getUser("ABC123", "test@example.com");
        
        // Assert
        assertNotNull(result);
        assertEquals("ABC123", result.getUser_id());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).listUser(any(UserQuery.class));
    }
    
    @Test
    public void testGetUser_NoResult() throws Exception {
        // Arrange
        when(userRepository.listUser(any(UserQuery.class))).thenReturn(new ArrayList<>());
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.getUser("ABC123", "test@example.com");
        });
        
        assertEquals("User don't exist", exception.getMessage());
    }
    
    @Test
    public void testGetUser_MissingCriteria() {
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.getUser(null, null);
        });
        
        assertEquals("At least one search criteria is required", exception.getMessage());
    }
    
    @Test
    public void testDeleteUser_Success() throws Exception {
        // Arrange
        when(userRepository.deleteUser(anyString())).thenReturn(1);
        
        // Act
        int result = userService.deleteUser("ABC123");
        
        // Assert
        assertEquals(1, result);
        verify(userRepository, times(1)).deleteUser(anyString());
    }
    
    @Test
    public void testDeleteUser_MissingUserId() throws Exception {
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.deleteUser(null);
        });
        
        assertEquals("User ID is required", exception.getMessage());
        verify(userRepository, times(0)).deleteUser(anyString());
    }
    
    @Test
    public void testAuthUser_Success() throws Exception {
        // Arrange
        List<User> mockUserList = new ArrayList<>();
        mockUser.setPassword_salt("somesalt");
        mockUser.setPassword("hashedpassword");
        mockUserList.add(mockUser);
        
        when(userRepository.listUser(any(UserQuery.class))).thenReturn(mockUserList);
        when(userRepository.authUser(any(UserAuth.class))).thenReturn(true);
        
        // Mock the SecurityUtils.hashPassword method with Mockito
        // Note: In real code, you might need to use PowerMockito to mock static methods
        // For simplicity, we'll assume the auth works by setting expectations
        
        // Act
        boolean result = userService.authUser(mockUserAuth);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testAuthUser_MissingCredentials() {
        // Arrange
        UserAuth emptyAuth = new UserAuth();
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.authUser(emptyAuth);
        });
        
        assertEquals("Either email or user_id is required for authentication", exception.getMessage());
    }
    
    @Test
    public void testAuthUser_MissingPassword() {
        // Arrange
        mockUserAuth.setPassword(null);
        
        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.authUser(mockUserAuth);
        });
        
        assertEquals("Password is required for authentication", exception.getMessage());
    }
}
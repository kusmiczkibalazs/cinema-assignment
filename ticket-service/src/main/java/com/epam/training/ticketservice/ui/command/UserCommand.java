package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
public class UserCommand {

    private final UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(key = "sign in privileged", value = "Admin login")
    public void login(String username, String password) {
        Optional<UserDto> user = userService.login(username, password);
        if (user.isEmpty()) {
            System.out.println("Login failed due to incorrect credentials");
        }
    }

    @ShellMethod(key = "sign out", value = "User logout")
    public void logout() {
        Optional<UserDto> user = userService.logout();
    }

    @ShellMethod(key = "describe account", value = "Get user information")
    public String printLoggedInUser() {
        Optional<UserDto> userDto = userService.getLoggedInUser();
        if (userDto.isEmpty()) {
            return "You are not signed in";
        } else if (userDto.get().getRole().equals(User.Role.ADMIN)) {
            return "Signed in with privileged account '" + userDto.get().getUsername() + "'";
        } else {
            return "Signed in with account '" + userDto.get().getUsername() + "'";
        }
    }

/*    @ShellMethod(key = "user register", value = "User registration")
    public String registerUser(String userName, String password) {
        try {
            userService.registerUser(userName, password);
            return "Registration was successful!";
        } catch (Exception e) {
            return "Registration failed!";
        }
    }*/
}

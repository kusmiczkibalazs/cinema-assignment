package com.epam.training.ticketservice.core.user.model;

import com.epam.training.ticketservice.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private final String username;
    private final User.Role role;

}

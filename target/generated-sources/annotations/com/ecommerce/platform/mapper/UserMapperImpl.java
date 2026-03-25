package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.UserRequest;
import com.ecommerce.platform.dto.request.UserUpdate;
import com.ecommerce.platform.dto.response.UserResponse;
import com.ecommerce.platform.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:14:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userRequest.getUsername() );
        user.password( userRequest.getPassword() );
        user.firstName( userRequest.getFirstName() );
        user.lastName( userRequest.getLastName() );
        user.dob( userRequest.getDob() );
        user.city( userRequest.getCity() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );

        return userResponse.build();
    }

    @Override
    public void updateUser(UserUpdate userUpdate, User user) {
        if ( userUpdate == null ) {
            return;
        }

        user.setPassword( userUpdate.getPassword() );
        user.setFirstName( userUpdate.getFirstName() );
        user.setLastName( userUpdate.getLastName() );
        user.setDob( userUpdate.getDob() );
        user.setCity( userUpdate.getCity() );
    }
}

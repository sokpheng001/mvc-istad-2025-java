package mapper;

import model.dto.UserResponseDto;
import model.entities.User;

public class UserMapper {
    public UserResponseDto fromUserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .build();
    }
}

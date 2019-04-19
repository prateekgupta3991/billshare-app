package bitspilani.goa.letsPlay.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupResponseDto {

    private Long id;
    private String name;
    private List<UserResponseDto> users;
    private UserResponseDto admin;


    public GroupResponseDto() {
    }

    public GroupResponseDto(Long id, String name, List<UserResponseDto> users, UserResponseDto admin) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserResponseDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponseDto> users) {
        this.users = users;
    }

    public UserResponseDto getAdmin() {
        return admin;
    }

    public void setAdmin(UserResponseDto admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "GroupResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", admin=" + admin +
                '}';
    }
}

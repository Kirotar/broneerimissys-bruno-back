package ee.rara.bruno.bruno.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int roleId;


}

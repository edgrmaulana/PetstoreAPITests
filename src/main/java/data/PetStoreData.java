package data;

import lombok.Data;

@Data
public class PetStoreData {
    private String endpointUrl;
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
    private String webResponse;
}

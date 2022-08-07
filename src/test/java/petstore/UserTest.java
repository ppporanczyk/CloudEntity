package petstore;

import DTO.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends ResourceManager {

    private static final String PREFIX_ENDPOINT = "/user";
    private static UserDTO newUser;
    String pathToResource;

    @BeforeEach
    public void createUserBeforeRunTest(){
        newUser = createUserDTO();
        pathToResource = PREFIX_ENDPOINT+"/"+newUser.getUsername();
        createResource(PREFIX_ENDPOINT, newUser);
    }

    @AfterEach
    public void deleteUserAfterRunTest(){
        given()
            .spec(specification)
            .when()
            .delete(pathToResource);
        newUser = null;
    }

    @Test
    public void verifyUserCreationAndCheckExistence(){
        UserDTO retrievedUser = getResourceAsDTO(pathToResource, UserDTO.class);
        assertEqualUser(newUser, retrievedUser);
    }

//    @Test
//    public void verifyUserDeletionAndCheckExistence(){
//        deleteResourceWithSuccess(pathToResource);
//        exceptGetResponseWithNotFoundCode(pathToResource);
//    }

    @Test
    public void requestUserDeletionTwiceAndExceptNotFoundResponse(){
        deleteResourceWithSuccess(pathToResource);
        deleteResourceWithNotFoundResponse(pathToResource);
    }

//    @Test
//    public void requestDataUpdateAndVerifyChanges(){
//        newUser.setLastName("Dickens");
//        updateResourceWithSuccess(pathToResource,newUser);
//        UserDTO updatedRetrievedUser = getResourceAsDTO(pathToResource, UserDTO.class);
//        assertEqualUser(newUser, updatedRetrievedUser);
//    }
//
//    @Test
//    public void requestNotExistingDataUpdateAndExceptNotFoundResponse(){
//        newUser.setLastName("Dickens");
//        deleteResourceWithSuccess(pathToResource);
//        updateResourceWithNotFoundResponse(pathToResource,newUser);
//    }

    private UserDTO createUserDTO() {
        return new UserDTO()
                .setUsername("Missing")
                .setFirstName("James")
                .setLastName("Doe")
                .setEmail("john.doe@mail.com")
                .setPassword("f0undMy8ody")
                .setPhone("123456789")
                .setUserStatus(0);
    }


    private void assertEqualUser(UserDTO newUser, UserDTO retrievedUser) {
        assertThat(retrievedUser.getUsername()).isEqualTo(newUser.getUsername());
        assertThat(retrievedUser.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(retrievedUser.getLastName()).isEqualTo(newUser.getLastName());
        assertThat(retrievedUser.getEmail()).isEqualTo(newUser.getEmail());
        assertThat(retrievedUser.getPassword()).isEqualTo(newUser.getPassword());
        assertThat(retrievedUser.getPhone()).isEqualTo(newUser.getPhone());
        assertThat(retrievedUser.getUserStatus()).isEqualTo(newUser.getUserStatus());
    }
}

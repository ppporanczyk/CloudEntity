package petstore;

import DTO.OrderDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class StoreTest extends ResourceManager {

    private static final String PREFIX_ENDPOINT = "/store/order";
    private static OrderDTO newOrder;
    String pathToResource;

    @BeforeEach
    public void createOrderBeforeRunTest(){
        newOrder = createOrderDTO();
        pathToResource = PREFIX_ENDPOINT+"/"+newOrder.getId();
        createResource(PREFIX_ENDPOINT, newOrder);
    }

    @AfterEach
    public void deleteOrderAfterRunTest(){
        given()
            .spec(specification)
            .when()
            .delete(pathToResource);
        newOrder =null;
    }

    @Test
    public void verifyOrderCreationAndCheckExistence(){
        OrderDTO retrievedOrder = getResourceAsDTO(pathToResource, OrderDTO.class);
        assertEqualOrder(newOrder, retrievedOrder);
    }

    @Test
    public void getStoreInventory(){
        exceptGetResponseWithSuccess("/store/inventory");
    }

    @Test
    public void verifyOrderDeletionAndCheckExistence(){
        deleteResourceWithSuccess(pathToResource);
        exceptGetResponseWithNotFoundCode(pathToResource);
    }

    @Test
    public void requestOrderDeletionTwiceAndExceptNotFoundResponse(){
        deleteResourceWithSuccess(pathToResource);
        deleteResourceWithNotFoundResponse(pathToResource);
    }
    private OrderDTO createOrderDTO() {
        return new OrderDTO()
                .setId(12345)
                .setPetId(0)
                .setQuantity(0)
                .setShipDate("2022-08-04T19:11:38.211")
                .setStatus("placed")
                .setComplete(true);
    }


    private void assertEqualOrder(OrderDTO newOrder, OrderDTO retrievedOrder) {
        assertThat(retrievedOrder.getPetId()).isEqualTo(newOrder.getPetId());
        assertThat(retrievedOrder.getQuantity()).isEqualTo(newOrder.getQuantity());
        assertThat(retrievedOrder.getShipDate().split("\\+")[0]).isEqualTo(newOrder.getShipDate());
        assertThat(retrievedOrder.getStatus()).isEqualTo(newOrder.getStatus());
        assertThat(retrievedOrder.isComplete()).isEqualTo(newOrder.isComplete());
    }

}

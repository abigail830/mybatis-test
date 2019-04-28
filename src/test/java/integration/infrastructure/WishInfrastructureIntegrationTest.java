package integration.infrastructure;

import com.github.abigail830.mybatictest.domain.WishInfrastructure;
import com.github.abigail830.mybatictest.domain.model.Wish;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import integration.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class WishInfrastructureIntegrationTest extends IntegrationTestBase {

    @Autowired
    WishInfrastructure wishInfrastructure;

    @AfterEach
    void tearDown() {

    }

    @Test
    @DatabaseSetup(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.DELETE)
    void getAll() {
        final List<Wish> allWishesByUser = wishInfrastructure.getAllWishesByUser(1);
        Assertions.assertEquals(2, allWishesByUser.size());

        Assertions.assertEquals(1,
                allWishesByUser.stream().filter(wish -> wish.getId() == 1)
                        .filter(wish -> wish.getDescription().equals("This is wish1"))
                        .count());

        Assertions.assertEquals(1,
                allWishesByUser.stream().filter(wish -> wish.getId() == 2)
                        .filter(wish -> wish.getDescription().equals("This is wish2"))
                        .count());
    }

    @Test
    void getWishById() {
    }

    @Test
    void insertWish() {
    }

    @Test
    void updateWishDescriptionById() {
    }

    @Test
    void deleteWish() {
    }
}
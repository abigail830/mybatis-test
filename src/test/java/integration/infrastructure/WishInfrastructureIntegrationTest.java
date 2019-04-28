package integration.infrastructure;

import com.github.abigail830.mybatictest.domain.WishInfrastructure;
import com.github.abigail830.mybatictest.domain.model.Wish;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import integration.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class WishInfrastructureIntegrationTest extends IntegrationTestBase {

    @Autowired
    WishInfrastructure wishInfrastructure;

    @Test
    @DatabaseSetup(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.DELETE)
    void getWishById() {
        final Wish wish3 = wishInfrastructure.getWishById(3).get();
        Assertions.assertEquals("This is wish3", wish3.getDescription());
        Assertions.assertEquals("2019-01-11 12:12:12.112233", wish3.getCreateTime().toString());
    }

    @Test
    @DatabaseSetup(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "/dbunit/WishTest_AllWishesByUser.xml", type = DatabaseOperation.DELETE)
    void should_get_empty_when_query_by_not_exist_wish() {
        final Optional<Wish> wishById = wishInfrastructure.getWishById(4);
        Assertions.assertFalse(wishById.isPresent());
    }

    @Test
    void updateWishDescriptionById() {
    }

    @Test
    void deleteWish() {
    }
}
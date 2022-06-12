package Ivin.HW6;

import Ivin.HW6.API.ProdService;
import Ivin.HW6.DB.DAO.CatMapper;
import Ivin.HW6.DB.DAO.ProdMapper;
import Ivin.HW6.DB.MODEL.Classes;
import Ivin.HW6.DTO.Prod;
import Ivin.HW6.DB.MODEL.ClassesEx;
import Ivin.HW6.DB.MODEL.Products;
import Ivin.HW6.UTILS.RetroUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestModifyProd {

    static ProdService prodService;
    Prod prod = null;
    int id = 1;
    static SqlSession session;
    String title;
    int price;
    String category;
    String titleBeforeChanges;
    int priceBeforeChanges;
    String categoryBeforeChanges;

    @BeforeAll
    static void BeforeAll() throws IOException {
        prodService = RetroUtils.getRetrofit().create(ProdService.class);
        session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    void SetUP() {
        prod = new Prod()
                .withId(id)
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Change product (Positive)")
    void ModifyProductTest() throws IOException {
        Response<Prod> response = prodService.getProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assert response.body() != null;
        titleBeforeChanges = response.body().getTitle();
        priceBeforeChanges = response.body().getPrice();
        categoryBeforeChanges = response.body().getCategoryTitle();
        title = "computer";
        price = 5000;
        category = "Electronic";
        SetUP();
        response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getTitle() != titleBeforeChanges, is (true));
        assertThat(response.body().getPrice() != priceBeforeChanges, is (true));
        assertThat(response.body().getCategoryTitle() != categoryBeforeChanges, is (true));
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) response.body().getId());
        ClassesEx example = new ClassesEx();
        example.createCriteria().andTitleLike(response.body().getCategoryTitle());
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        assertThat(selected.getTitle(), equalTo(title));
        assertThat(selected.getPrice(), equalTo(price));
        assertThat(selected.getCategory_id(), equalTo(category_id));
        DOWN();
    }


    @SneakyThrows
    void DOWN() {
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected_p = prodMapper.selectByPrimaryKey((long) id);
        ClassesEx example = new ClassesEx();
        example.createCriteria().andTitleLike(categoryBeforeChanges);
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        selected_p.setTitle(titleBeforeChanges);
        selected_p.setPrice(priceBeforeChanges);
        selected_p.setCategory_id(category_id);
        prodMapper.updateByPrimaryKey(selected_p);
        session.commit();
        assertThat(selected_p.getTitle(), equalTo(titleBeforeChanges));
        assertThat(selected_p.getPrice(), equalTo(priceBeforeChanges));
        assertThat(selected_p.getCategory_id(), equalTo(category_id));
    }

    @AfterAll
    static void AfterALL() {
        session.close();
    }

}

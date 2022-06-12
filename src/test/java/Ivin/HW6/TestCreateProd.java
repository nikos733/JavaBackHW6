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
import org.apache.ibatis.io.Resources;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class TestCreateProd {

    static ProdService prodService;
    Prod prod = null;
    int id;
    static SqlSession session;


    @BeforeAll
    static void BeforeALL() throws IOException {
        prodService = RetroUtils.getRetrofit().create(ProdService.class);
        session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    void SetUP(String title, int price, String category) {
        prod = new Prod()
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Product creation")
    void CreateAndDeleteProductTest() throws IOException {
        SetUP("bread", 9562, "Food");
        Response<Prod> response = prodService.createProduct(prod).execute();
        assertThat(response.code(), equalTo(201));
        assert response.body() != null;
        assertThat(response.body().getId(), notNullValue());
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        id =  response.body().getId();
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) id);
        ClassesEx example = new ClassesEx();
        example.createCriteria().andTitleLike(response.body().getCategoryTitle());
        List<Classes> list = catMapper.selectByExample(example);
        Classes classes = list.get(0);
        Long category_id = classes.getId();
        assertThat(selected.getTitle(), equalTo(response.body().getTitle()));
        assertThat(selected.getPrice(), equalTo(response.body().getPrice()));
        assertThat(selected.getCategory_id(), equalTo(category_id));
        DOWN();
        Response<Prod> responseForChecking = prodService.getProductById(id).execute();
        assertThat(responseForChecking.code(), equalTo(404));
    }

    @SneakyThrows
    void DOWN() {
        ProdMapper prodMapper = session.getMapper(ProdMapper.class);
        Products selected = prodMapper.selectByPrimaryKey((long) id);
        prodMapper.deleteByPrimaryKey(selected.getId());
        session.commit();
    }

    @AfterAll
    static void AfterALL() {
        session.close();
    }
}
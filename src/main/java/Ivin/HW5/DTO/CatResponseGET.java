package Ivin.HW5.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CatResponseGET {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("products")
    private List<Prod> prods = new ArrayList<>();


}

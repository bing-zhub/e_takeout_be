package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class CategoryCreateForm {

    @NotEmpty(message = "类别名不能为空")
    private String  categoryName;

    @NotNull(message = "类别标识不能为空")
    private Integer  categoryType;

}

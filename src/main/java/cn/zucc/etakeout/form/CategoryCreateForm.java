package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class CategoryCreateForm {
    @NotEmpty(message = "类别名不能为空")
    private String  categoryName;
    @NotEmpty(message = "类别标识不能为空")
    private Integer  categoryType;

}

package cn.zucc.etakeout.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class CategoryForm {
    @NotEmpty(message = "类别名不能为空")
    private int CategoryId;
    @NotEmpty(message = "类别名不能为空")
    private String CategoryName;
    @NotEmpty(message = "类别编号不能为空")
    private int CategoryType;
}

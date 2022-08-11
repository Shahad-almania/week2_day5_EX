package Model;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Emp {
    @NotNull(message ="Id can not be null")
    @Range(min=2, max=10,message = "Id must be more than 2")
    private  int id;

    @NotBlank(message ="Name can not be null")
    @Size(min=4,message = "Name must be more than 4")
    private String name;

    @NotNull(message ="Age can not be null")
    @Range(min=25,max=60,message = "Age must to be more 25")
    private int age;

    private boolean onLeave =false;

    @NotNull(message ="EmploymentYear can not be null")
    @PastOrPresent
    private int employmentYear;

    @NotNull(message ="annualLeave can not be null")
    private int annualLeave;

}

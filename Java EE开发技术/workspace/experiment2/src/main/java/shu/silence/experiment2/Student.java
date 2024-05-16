package shu.silence.experiment2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private String password;
    private String sex;
    private String age;
    private String country;
    private String email;
    private String phone;
}

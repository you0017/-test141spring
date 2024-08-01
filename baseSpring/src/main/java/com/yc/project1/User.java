package com.yc.project1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class User {
    private String username;
    private String password;
    private String sex;
    private String tel;
    private String email;
}

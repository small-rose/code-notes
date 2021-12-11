package cn.com.lgs.domain;

import lombok.Data;

/**
 * @author luguosong
 * @date 2021/12/10 16:02
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

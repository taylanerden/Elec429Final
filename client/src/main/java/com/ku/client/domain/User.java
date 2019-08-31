package com.ku.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Taylan Erden
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "fullname")
    private String fullName;

    @JsonProperty(value = "username")
    private String userName;

    @JsonProperty(value = "gsmno")
    private String gsmNo;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;
}

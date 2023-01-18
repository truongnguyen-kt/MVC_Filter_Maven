/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.user;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 12345
 */
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private String fullname;
    private boolean isAdmin;

    public User() {
    }

    public User(String username, String password, String fullname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }
}

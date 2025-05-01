package co.spribe.pc.dto;

import java.util.Objects;

public class Player {
    public int id;
    public int age;
    public String gender;
    public String login;
    public String password;
    public String role;
    public String screenName;

    public Player(int id,
                  int age,
                  String gender,
                  String login,
                  String password,
                  String role,
                  String screenName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public Player(int age,
                  String gender,
                  String login,
                  String password,
                  String role,
                  String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public boolean isEqualTo(Player player) {
        if (this == player) return true;
        if (player == null || getClass() != player.getClass()) return false;

        return this.age == player.age &&
                Objects.equals(this.gender, player.gender) &&
                Objects.equals(this.login, player.login) &&
                Objects.equals(this.password, player.password) &&
                Objects.equals(this.role, player.role) &&
                Objects.equals(this.screenName, player.screenName);
    }

}
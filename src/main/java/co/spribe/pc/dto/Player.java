package co.spribe.pc.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Player {
    private int id;
    private int age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    // Default constructor
    public Player() {}

    @JsonCreator
    public Player(
            @JsonProperty("id") int id,
            @JsonProperty("age") int age,
            @JsonProperty("gender") String gender,
            @JsonProperty("login") String login,
            @JsonProperty("password") String password,
            @JsonProperty("role") String role,
            @JsonProperty("screenName") String screenName) {
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

    public Player(int id,
                  int age,
                  String gender,
                  String screenName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.screenName = screenName;
    }

    public Player(int id,
                  int age,
                  String gender,
                  String login,
                  String role,
                  String screenName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.login = login;
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

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public Player setAge(int age) {
        this.age = age;
        return this;
    }

    public Player setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Player setLogin(String login) {
        this.login = login;
        return this;
    }

    public Player setPassword(String password) {
        this.password = password;
        return this;
    }

    public Player setRole(String role) {
        this.role = role;
        return this;
    }

    public Player setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }
}
package co.spribe.pc.dto;

import java.util.Objects;

public class PlayerDto {
    private Integer id;
    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public PlayerDto() {}

    public PlayerDto(Integer age, String gender, String login, String password, String role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public Integer getId() {
        return id;
    }

    public PlayerDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public PlayerDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PlayerDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public PlayerDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PlayerDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public PlayerDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getScreenName() {
        return screenName;
    }

    public PlayerDto setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto player = (PlayerDto) o;
        return Objects.equals(id, player.id) && Objects.equals(age, player.age) && Objects.equals(gender, player.gender) && Objects.equals(login, player.login) && Objects.equals(password, player.password) && Objects.equals(role, player.role) && Objects.equals(screenName, player.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, gender, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
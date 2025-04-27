package co.spribe.pc.dto;

public class PlayerDto {
    public int age;
    public String gender;
    public String login;
    public String password;
    public String role;
    public String screenName;

    public PlayerDto(int age, String gender, String login, String password, String role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }
}
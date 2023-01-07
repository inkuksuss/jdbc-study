package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {

    private String memeberId;
    private int money;

    public Member() {}

    public Member(String memeberId, int money) {
        this.memeberId= memeberId;
        this.money = money;
    }
}

package com.kimsk.springboot.domain.user;

import com.kimsk.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //엔티티 만들때는 항상 써줘야 함
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) //EnumType을 어떤 형태로 저장할지 결정
    @Column
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        //id는 지정할 수 없으므로 포함시키지 않는다.
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        //update 시키면 update 대상 entity를 리턴한다.
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return role.getKey();
    }
}

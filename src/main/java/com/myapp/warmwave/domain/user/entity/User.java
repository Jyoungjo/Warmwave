package com.myapp.warmwave.domain.user.entity;

import com.myapp.warmwave.common.BaseEntity;
import com.myapp.warmwave.common.Role;
import com.myapp.warmwave.domain.address.entity.Address;
import com.myapp.warmwave.domain.article.entity.Article;
import com.myapp.warmwave.domain.chat.entity.ChatMessage;
import com.myapp.warmwave.domain.favorite_inst.entity.FavoriteInst;
import com.myapp.warmwave.domain.image.entity.Image;
import com.myapp.warmwave.domain.temperture.entity.Temperature;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "TB_USER")
public abstract class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_IMAGE_ID")
    private Image profileImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPERATURE_ID")
    private Temperature temperature;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ChatMessage> messageList = new ArrayList<>();

    @OneToMany(mappedBy = "individualUser", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FavoriteInst> favoriteList = new ArrayList<>();

    public void updateUserInfo(String password, Address address, Image profileImg) {
        this.password = password;
        this.address = address;
        this.profileImg = profileImg;
    }
}

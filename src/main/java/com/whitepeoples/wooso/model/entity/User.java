//package com.whitepeoples.wooso.model.entity;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "user")
//@Data
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer userId;
//
//    private String username;
//    private String email;
//    private String password;
//    private String phoneNumber;
//    private String gender;
//    private String guaranteeDocument;
//    private Boolean verificationStatus;
//
//    @ManyToOne
//    @JoinColumn(name = "user_profile")
//    private Profile profile;
//
//
//}
//

package com.whitepeoples.wooso.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String guaranteeDocument;
    private Boolean verificationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private Profile userProfile;
}


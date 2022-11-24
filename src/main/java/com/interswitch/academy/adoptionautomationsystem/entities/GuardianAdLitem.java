package com.interswitch.academy.adoptionautomationsystem.entities;

import com.interswitch.academy.adoptionautomationsystem.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GuardianAdLitem {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String location;
    private String report; //JQUERY CKEDITOR

}

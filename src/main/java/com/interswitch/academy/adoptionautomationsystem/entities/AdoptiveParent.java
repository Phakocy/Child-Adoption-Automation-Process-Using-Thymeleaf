package com.interswitch.academy.adoptionautomationsystem.entities;

import com.interswitch.academy.adoptionautomationsystem.entities.enums.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Adoptive_Parent") // To override the table name
public class AdoptiveParent {

    @Id
    private String id;
    private String name;
    private String occupation;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
    private Education qualification;

    private String nationality;
    private String officialAddress;

    private String homeAddress;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToOne(mappedBy = "parent", cascade = CascadeType.ALL)
    private Children child;

    @OneToOne(mappedBy = "parent")
    private AdoptionRequest request;

    @OneToOne(mappedBy = "adoptiveParent")
    private Tracking tracking;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;


    @ManyToOne
    @JoinColumn(name = "created_by_userId") //, nullable = false
    private User createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "last_updated_by_userId")
    private User updatedBy;
}

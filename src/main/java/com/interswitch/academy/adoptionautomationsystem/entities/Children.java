package com.interswitch.academy.adoptionautomationsystem.entities;

import com.interswitch.academy.adoptionautomationsystem.entities.enums.AdoptionStatus;
import com.interswitch.academy.adoptionautomationsystem.entities.enums.Gender;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Children {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String motherName;
    private String fatherName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
    private String orphanageCode;
    private String nationality;
    @Lob
    private String image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private AdoptiveParent parent;
    @ManyToOne
    @JoinColumn(name = "guardian_id")
    private GuardianAdLitem guardian;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "created_by_userId", nullable = false) //, nullable = false
    private User createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "last_updated_by_userId", nullable = false)
    private User updatedBy;
}

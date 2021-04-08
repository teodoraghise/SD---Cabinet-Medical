package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="patients")
public class Patient implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String symptoms;

    @Column
    private String diagnostic;

    @Column
    private String treatment;

}

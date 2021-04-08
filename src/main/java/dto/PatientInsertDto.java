package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientInsertDto {

    private int id;

    private String firstName;

    private String lastName;

    private String symptoms;

    private String diagnostic;

    private String treatment;
}

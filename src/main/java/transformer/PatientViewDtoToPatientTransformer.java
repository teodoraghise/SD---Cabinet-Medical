package transformer;

import domain.Patient;
import dto.PatientViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientViewDtoToPatientTransformer {

    public Patient convertToEntity(PatientViewDto patientViewDto){
        ModelMapper modelMapper = new ModelMapper();
        Patient patient = new Patient();
        modelMapper.map(patientViewDto, patient);
        return patient;
    }

}

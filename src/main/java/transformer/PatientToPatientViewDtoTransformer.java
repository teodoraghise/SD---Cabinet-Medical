package transformer;

import domain.Patient;
import dto.PatientViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientToPatientViewDtoTransformer {

    public PatientViewDto entityToDto(Patient patient) {
        ModelMapper modelMapper = new ModelMapper();
        PatientViewDto patientViewDto = new PatientViewDto();
        modelMapper.map(patient, patientViewDto);
        return patientViewDto;
    }

}

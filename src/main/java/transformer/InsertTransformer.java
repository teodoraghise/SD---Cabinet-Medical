package transformer;

import domain.Patient;
import dto.PatientInsertDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InsertTransformer {

    public PatientInsertDto convertToDto (Patient patient) {
        PatientInsertDto patientInsertDto = new PatientInsertDto();
        BeanUtils.copyProperties(patient, patientInsertDto);
        return patientInsertDto;
    }

    public Patient convertToEntity(PatientInsertDto patientInsertDto ){
        ModelMapper modelMapper = new ModelMapper();
        Patient patient = new Patient();
        modelMapper.map(patientInsertDto, patient);
        return patient;
    }

}

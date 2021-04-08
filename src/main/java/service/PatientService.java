package service;

import domain.Patient;
import dto.PatientInsertDto;
import dto.PatientViewDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PatientService {

    @Transactional(readOnly = true)
    List<PatientViewDto> getAll();

    @Transactional(rollbackFor = Exception.class)
    Patient addPatients(PatientInsertDto patientInsertDto);

    @Transactional(rollbackFor = Exception.class)
    PatientInsertDto editPatient(PatientInsertDto PatientInsertDto);

    @Transactional(rollbackFor = Exception.class)
    PatientViewDto getPatientByID(int id);

    @Transactional(rollbackFor = Exception.class)
    PatientInsertDto editById(int id);

    @Transactional(rollbackFor = Exception.class)
    PatientViewDto getOne(int id);

    @Transactional(rollbackFor = Exception.class)
    void delete(int id);

}

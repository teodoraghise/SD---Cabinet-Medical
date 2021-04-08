package service.impl;

import domain.Patient;
import dto.PatientInsertDto;
import dto.PatientViewDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PatientRepository;
import service.PatientService;
import transformer.InsertTransformer;
import transformer.PatientToPatientViewDtoTransformer;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private final PatientRepository patientRepository;
    private final PatientToPatientViewDtoTransformer transformer;
    private final InsertTransformer patientInsertDtoTransformer;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PatientToPatientViewDtoTransformer transformer, InsertTransformer patientInsertDtoTransformer) {
        this.patientRepository = patientRepository;
        this.transformer = transformer;
        this.patientInsertDtoTransformer = patientInsertDtoTransformer;
    }
    @Override
    public List<PatientViewDto> getAll() {
        List<PatientViewDto> patientViewList = new ArrayList<>();
        for (Patient patient : patientRepository.findAll()) {
            patientViewList.add(transformer.entityToDto(patient));
        }
        return patientViewList;
    }

    @Override
    public Patient addPatients(PatientInsertDto patientInsertDto) {
        Patient patient = patientInsertDtoTransformer.convertToEntity(patientInsertDto);

        patientRepository.save(patient);

        log.debug("Method={addPatient}, Details=[message={}]", "Add patient to the database");

        return patient;
    }
    @Override
    public PatientInsertDto editPatient(PatientInsertDto patientInsertDto) {
        Patient patient = patientInsertDtoTransformer.convertToEntity(patientInsertDto);
        patientRepository.save(patient);
        return patientInsertDtoTransformer.convertToDto(patient);
    }


    @Override
    public PatientViewDto getPatientByID(int id) {
        Patient patient = patientRepository.findById(id).get();
        log.debug("Method={getPatientByID}, Details=[message={}]", "Get a specific patient by id");
        return transformer.entityToDto(patient);
    }


    @Override
    public PatientInsertDto editById(int id) {
        Patient patient= patientRepository.findById(id).get();
        log.debug("Method={getPatientByID}, Details=[message={}]", "Get a specific patient by id");
        return patientInsertDtoTransformer.convertToDto(patient);
    }

    @Override
    public PatientViewDto getOne(int id) {
        Patient patient = patientRepository.getOne(id);
        return transformer.entityToDto(patient);
    }

    @Override
    public void delete(int id) {
        log.debug("Method={delete}, Details=[message={}]", "Delete patient by id");

        patientRepository.deleteById(id);
    }
}

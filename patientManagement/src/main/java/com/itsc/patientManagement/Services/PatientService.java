package com.itsc.patientManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.patientManagement.Model.Patient;
import com.itsc.patientManagement.Model.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);

    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, Patient updatePatient) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setName(updatePatient.getName());
            patient.setAge(updatePatient.getAge());
            patient.setGender(updatePatient.getGender());
            return patientRepository.save(patient);
        } else {
            throw new RuntimeException("Patient not found!");
        }
    }
}

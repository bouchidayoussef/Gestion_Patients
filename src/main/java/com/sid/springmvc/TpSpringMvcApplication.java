package com.sid.springmvc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sid.springmvc.dao.PatientRepository;
import com.sid.springmvc.entities.Patient;

@SpringBootApplication
public class TpSpringMvcApplication implements CommandLineRunner {
    @Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(TpSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Hassan",new Date(),false,8));
		patientRepository.save(new Patient(null,"Mohamed",new Date(),false,9));
		patientRepository.save(new Patient(null,"Samira",new Date(),false,12));
		
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});
	}

}

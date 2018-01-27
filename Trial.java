package ics372;

import java.util.ArrayList;

public class Trial {
	

	ArrayList<Patient> patients = new ArrayList<Patient>();
	ArrayList<Record> records = new ArrayList<Record>();
	
	public Trial() {}
		
	
	
	
	public void addReading(String patient_id, String reading_type, String reading_id,long reading_date) {
		
		for( int i = 0; i < patients.size(); i++) {
			
			
			
			if(patients.get(i).getPatient_id() == patient_id && patients.get(i).isPatient_active()){
				Record newRecord = new Record(patient_id,reading_type,reading_id,reading_date);
				
				records.add(newRecord);
			System.out.println("New record added");
				
				
			}
			else {
				System.out.println("Patient not in the system or inactive for the trial");
			}
		}
		
		
		
	}
}
			
		
			
		
  
		
	
	



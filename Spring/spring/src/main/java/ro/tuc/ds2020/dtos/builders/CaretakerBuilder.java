package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaretakerDTO;
import ro.tuc.ds2020.dtos.CaretakerDetailsDTO;
import ro.tuc.ds2020.entities.Caretaker;

public class CaretakerBuilder {

	private CaretakerBuilder() {
		
	}
	
	public static CaretakerDTO toCaretakerDTO(Caretaker caretaker) {
        return new CaretakerDTO(caretaker.getId(), caretaker.getName(), caretaker.getAddress());
    }
	
	public static Caretaker toEntity(CaretakerDetailsDTO caretakerDetailsDTO) {
        return new Caretaker(caretakerDetailsDTO.getName(),
        		caretakerDetailsDTO.getAddress(),
        		caretakerDetailsDTO.getGender(),
        		caretakerDetailsDTO.getDob());
    }
	
	public static Caretaker toEntity(CaretakerDTO caretakerDTO) {
        return new Caretaker(caretakerDTO.getName(),
        		caretakerDTO.getAddress());
	}
}

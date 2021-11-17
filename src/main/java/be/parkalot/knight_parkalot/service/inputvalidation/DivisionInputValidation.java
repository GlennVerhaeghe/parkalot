package be.parkalot.knight_parkalot.service.inputvalidation;

import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.repository.DivisionRepository;

public class DivisionInputValidation {

    private CreateDivisionDto createDivisionDto;
    private DivisionRepository divisionRepository;

    public DivisionInputValidation(CreateDivisionDto createDivisionDto, DivisionRepository divisionRepository) {
        this.createDivisionDto = createDivisionDto;
        this.divisionRepository = divisionRepository;
    }

    public void validate(CreateDivisionDto createDivisionDto){
        if(createDivisionDto.getParentId()!=0 && !divisionRepository.existsById(createDivisionDto.getParentId())){
            throw new IllegalArgumentException("Division parent Id is not Existing");
        }

        if(createDivisionDto.getName() == null){
            throw new IllegalArgumentException("Please fill the name");
        }

        if(createDivisionDto.getDirectorName()==null){
            throw new IllegalArgumentException("Please fill the Director name");
        }


        if(createDivisionDto.getOldName() == null){
            throw new IllegalArgumentException("Please fill the Old division name");
        }




    }



}

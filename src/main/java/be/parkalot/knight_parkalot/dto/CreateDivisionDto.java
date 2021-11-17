package be.parkalot.knight_parkalot.dto;

public class CreateDivisionDto {
    private String name;
    private String oldName;
    private CreateNameDto directorName;
    private int parentId;



    public String getName() {
        return name;
    }

    public String getOldName() {
        return oldName;
    }

    public CreateNameDto getDirectorName() {
        return directorName;
    }

    public int getParentId() {
        return parentId;
    }
}

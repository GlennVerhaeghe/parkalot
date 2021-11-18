package be.parkalot.knight_parkalot.dto;

public class CreateDivisionDto {
    private String name;
    private String originalName;
    private NameDto directorName;
    private int parentId;

    public CreateDivisionDto(String name, String originalName, NameDto directorName, int parentId) {
        this.name = name;
        this.originalName = originalName;
        this.directorName = directorName;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public NameDto getDirectorName() {
        return directorName;
    }

    public int getParentId() {
        return parentId;
    }
}

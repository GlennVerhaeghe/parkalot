package be.parkalot.knight_parkalot.dto.division;

import be.parkalot.knight_parkalot.dto.NameDto;

public class CreateDivisionDto {
    private final String name;
    private final String originalName;
    private final NameDto directorName;
    private final int parentId;

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

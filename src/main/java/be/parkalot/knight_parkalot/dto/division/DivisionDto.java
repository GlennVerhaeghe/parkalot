package be.parkalot.knight_parkalot.dto.division;

import be.parkalot.knight_parkalot.dto.NameDto;

import java.util.Objects;

public class DivisionDto {
    private int id;
    private String name;
    private String originalName;
    private NameDto directorName;
    private int parentId;

    public DivisionDto() {
    }

    private DivisionDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        originalName = builder.originalName;
        directorName = builder.directorName;
        parentId = builder.parentId;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisionDto that = (DivisionDto) o;
        return id == that.id && parentId == that.parentId && Objects.equals(name, that.name) && Objects.equals(originalName, that.originalName) && Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, originalName, directorName, parentId);
    }

    public static class Builder {
        private int id;
        private String name;
        private String originalName;
        private NameDto directorName;
        private int parentId;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOldName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public Builder withDirectorName(NameDto directorName) {
            this.directorName = directorName;
            return this;
        }

        public Builder withParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public DivisionDto build() {
            return new DivisionDto(this);
        }
    }
}

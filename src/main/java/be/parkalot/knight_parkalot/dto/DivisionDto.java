package be.parkalot.knight_parkalot.dto;

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

        public Builder withOldName(String oldName) {
            this.oldName = oldName;
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

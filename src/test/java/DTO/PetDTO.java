package DTO;


import java.util.List;

public class PetDTO {
    private long id;
    private CategoryDTO category;
    private String name;
    private List<String> photoUrls;
    private List<TagDTO> tags;
    private String status;

    public static class TagDTO{
        public int id;
        public String name;

    }

    public long getId() {
        return id;
    }

    public PetDTO setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public PetDTO setCategory(CategoryDTO category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public PetDTO setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public PetDTO setTags(List<TagDTO> tags) {
        this.tags = tags;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PetDTO setStatus(String status) {
        this.status = status;
        return this;
    }
}

package RestAssured;


import java.util.List;

public class Pet {

    private Long id;
    private String name;
    private String[] photoUrls = {"photo1.png", "photo2.png", "photo3.png"};
    private List<Tags> tags = null;
    private String status;

    public Pet() {

    }

    public Pet(Long id, String name, String status, List<Tags> tags) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }
}

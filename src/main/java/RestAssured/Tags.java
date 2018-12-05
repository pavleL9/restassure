package RestAssured;

public class Tags {

    private int idTags;
    private String nameTags;

    public Tags() {

    }

    public Tags(int idTags, String nameTags) {
        this.idTags = idTags;
        this.nameTags = nameTags;
    }

    public int getIdTags() {
        return idTags;
    }

    public void setIdTags(int idTags) {
        this.idTags = idTags;
    }

    public String getNameTags() {
        return nameTags;
    }

    public void setNameTags(String nameTags) {
        this.nameTags = nameTags;
    }
}

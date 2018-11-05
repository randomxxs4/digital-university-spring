package ru.digitaluniversity.dto.alternative;

/**
 * The type Alternative group dto.
 */
public class AlternativeGroupDto {
    private String id;
    private String title;
    private long countStundent;

    public AlternativeGroupDto() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCountStundent() {
        return countStundent;
    }

    public void setCountStundent(long countStundent) {
        this.countStundent = countStundent;
    }
}

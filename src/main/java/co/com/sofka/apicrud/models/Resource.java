package co.com.sofka.apicrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "resources")
public class Resource implements Serializable {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private LocalDate returnDate;

    @NonNull
    private String type;

    @NonNull
    private String thematic;

    @NonNull
    private Boolean isAvailable;

    public Resource() {
        name = "name empty";
        returnDate = LocalDate.now();
        type = "type empty";
        thematic = "thematic empty";
        isAvailable = true;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(@NonNull LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getThematic() {
        return thematic;
    }

    public void setThematic(@NonNull String thematic) {
        this.thematic = thematic;
    }

    @NonNull
    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(@NonNull Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", returnDate=" + returnDate +
                ", type='" + type + '\'' +
                ", thematic='" + thematic + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }


}

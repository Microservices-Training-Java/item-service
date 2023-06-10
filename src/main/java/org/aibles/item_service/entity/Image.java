package org.aibles.item_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    private String id;

    private String name;

    private String path;

    @PrePersist
    private void prePersistId() {
        this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
    }

    public static Image of(String name, String path){
        Image image = new Image();
        image.setName(name);
        image.setPath(path);
        return image;
    }
}

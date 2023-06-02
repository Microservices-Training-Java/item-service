package org.aibles.item_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "avatar")
@Data
public class Avatar {

    @Id
    private String id;

    private String name;

    private String path;

    @PrePersist
    private void prePersistId() {
        this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
    }

    public static Avatar of(String name,String path){
        Avatar avatar = new Avatar();
        avatar.setName(name);
        avatar.setPath(path);
        return avatar;
    }
}

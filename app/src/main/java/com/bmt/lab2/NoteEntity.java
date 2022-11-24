package com.bmt.lab2;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class NoteEntity implements Serializable {
    private static int count = 0;
    private Integer id;
    private String title,content;
    private Date createTime;

    public NoteEntity(String title, String content, Date createTime) {
        this.id = count++;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
}

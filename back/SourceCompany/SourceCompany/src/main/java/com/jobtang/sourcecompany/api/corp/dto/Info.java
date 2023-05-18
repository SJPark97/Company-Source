package com.jobtang.sourcecompany.api.corp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Info {
    private String title;
    private Object content;

    public Info (String title, Object content) {
        this.title = title;
        this.content = content;
    }
}

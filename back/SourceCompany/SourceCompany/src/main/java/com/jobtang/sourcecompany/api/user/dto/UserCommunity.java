package com.jobtang.sourcecompany.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommunity {
    private String communityType;
    private String title;
    private int totalView;
}

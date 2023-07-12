package com.nagarro.reviewSystem;

import org.springframework.stereotype.Component;

@Component
public class CONSTANTS {

    public final Integer SUCCESS = 200;
    public final Integer FAILURE = 400;
    public final Integer APPROVE = 1;
    public final Integer REJECTED = -1;
    public final Integer PENDING = 0;
    public final Integer REVIEWED = 2;

}

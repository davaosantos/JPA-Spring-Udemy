package io.github.davaosantos.api;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@Data
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String msgErro){
        this.errors = Arrays.asList(msgErro);
    }

}

package io.zutun.samples.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationError {

    PET_NOT_FOUND(HttpStatus.NOT_FOUND, "pet not found");

    private final HttpStatus status;
    private final String defaultMessage;

    public String getCode() {
        return this.name();
    }

}

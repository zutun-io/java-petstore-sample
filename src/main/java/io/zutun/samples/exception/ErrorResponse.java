package io.zutun.samples.exception;


import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponse(

                            @Schema(description = "Timestamp of the error", example = "2025-04-27T12:34:56Z")
                            String timestamp,
                            @Schema(description = "Detailed error message", example = "Pet not found with ID: 3fa85f64-5717-4562-b3fc-2c963f66afa6")
                            String message,
                            @Schema(description = "HTTP status code", example = "404")
                            Integer statusCode) {
}

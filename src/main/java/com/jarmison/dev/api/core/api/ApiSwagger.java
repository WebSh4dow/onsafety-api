package com.jarmison.dev.api.core.api;

import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/persons")
public interface ApiSwagger {

    @Operation(summary = "Create person", description = "Create Person in application")
    @ApiResponse(responseCode = "200", description = "Successfully created person")
    ResponseEntity<PersonResponse> add(
            @Parameter(description = "Person request body") @RequestBody PersonRequest requestBody);

    @Operation(summary = "Get all persons", description = "Retrieve a list of all persons.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of persons")
    ResponseEntity<List<PersonResponse>> findAll();

    @Operation(summary = "Find person by ID", description = "Retrieve a person by their ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved person")
    @ApiResponse(responseCode = "404", description = "Person not found")
    ResponseEntity<PersonResponse> findById(
            @Parameter(description = "ID of the person to be retrieved") @PathVariable Long id);

    @Operation(summary = "Delete person by ID", description = "Deletes a person by their ID.")
    @ApiResponse(responseCode = "204", description = "Successfully deleted person")
    @ApiResponse(responseCode = "404", description = "Person not found")
    void delete(
            @Parameter(description = "ID of the person to be deleted") @PathVariable Long id);

    @Operation(summary = "Update person by ID", description = "Updates an existing person entry by their ID.")
    @ApiResponse(responseCode = "200", description = "Successfully updated person")
    @ApiResponse(responseCode = "404", description = "Person not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    ResponseEntity<PersonResponse> update(
            @Parameter(description = "ID of the person to be updated") @PathVariable Long id,
            @Parameter(description = "Person request body with updated data") @RequestBody PersonRequest requestBody);
}


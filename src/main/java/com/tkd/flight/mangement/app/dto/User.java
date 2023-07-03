package com.tkd.flight.mangement.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@NotBlank
	private String address;

	@NotBlank
	@Size(max = 10, min = 10, message = "Phone Number should be of legth 10")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid format of Phone number")
	private String phoneNumber;
}

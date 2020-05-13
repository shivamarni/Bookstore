<<<<<<< HEAD
package com.bridgelabz.fundoonotes.exception;

=======
package com.bridgelabz.bookstore.exception;
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstore.response.ExceptionResponse;
<<<<<<< HEAD



=======
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
@ControllerAdvice
public class BookStoreExceptionHandler {

	@ExceptionHandler(BookStoreException.class)
	public final ResponseEntity<ExceptionResponse> BookStoreException(BookStoreException ex) {

		ExceptionResponse exp = new ExceptionResponse();
		exp.setMessage(ex.getMessage());
		exp.setCode(ex.getStatus());
		exp.setTime(LocalDateTime.now());

		return ResponseEntity.status(exp.getCode()).body(new ExceptionResponse(exp.getMessage(), exp.getCode(),exp.getTime()));

	}

}

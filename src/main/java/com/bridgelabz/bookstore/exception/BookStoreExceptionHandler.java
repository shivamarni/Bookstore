
<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.bridgelabz.fundoonotes.exception;

=======
package com.bridgelabz.bookstore.exception;
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
>>>>>>> 9e79260081006563116865a2e2d652de901a8eb5
=======
package com.bridgelabz.bookstore.exception;
>>>>>>> e5e607b26169006dad9f8f781e60e0e060d82f20
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstore.response.ExceptionResponse;
<<<<<<< HEAD
<<<<<<< HEAD



=======
<<<<<<< HEAD



=======
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
>>>>>>> 9e79260081006563116865a2e2d652de901a8eb5
=======
>>>>>>> e5e607b26169006dad9f8f781e60e0e060d82f20
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

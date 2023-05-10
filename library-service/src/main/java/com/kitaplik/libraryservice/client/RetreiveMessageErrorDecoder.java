package com.kitaplik.libraryservice.client;

import com.kitaplik.libraryservice.exception.BookNotFoundException;
import com.kitaplik.libraryservice.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default(); //her feign hatasinda calisiyor ve decodeu calistiyor

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;

        try (InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage(
                    (String) response.headers().get("date").toArray()[0], //response date
                    response.status(), //response code
                    HttpStatus.resolve(response.status()).getReasonPhrase(), //Http code aciklamasi
                    IOUtils.toString(body, StandardCharsets.UTF_8), //exception mesaji
                    response.request().url()); //request in atildigi yerin url i
        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        switch (response.status()) {
            case 404:
                throw new BookNotFoundException(message);
            default:
                return errorDecoder.decode(methodKey, response); //handle ettigimiz case ler disindakiler
        }
    }
}

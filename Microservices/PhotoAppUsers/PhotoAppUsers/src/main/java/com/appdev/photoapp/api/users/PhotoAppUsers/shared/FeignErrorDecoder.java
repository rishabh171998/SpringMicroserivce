package com.appdev.photoapp.api.users.PhotoAppUsers.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404: {
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
            }


        }
    return null;
}
}

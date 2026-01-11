package org.docencia.hotel.web.soap;

import org.docencia.hotel.domain.model.Guest;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(
        name = "GuestSoapService",
        targetNamespace = "http://hotel.docencia.org/ws"
)
public interface GuestSoapService {

    /**
     * obtiene un huesped por su id a traves de soap
     * 
     * @param id el id del huesped
     * @return Guest
     */
    @WebMethod(operationName = "GetGuestById")
    @WebResult(name = "guest")
    Guest getGuestById(@WebParam(name = "id") Long id);

    /**
     * guarda un nuevo huesped a traves de soap
     * 
     * @param guest el huesped a guardar
     * @return Guest
     */
    @WebMethod(operationName = "SaveGuest")
    @WebResult(name = "guest")
    Guest saveGuest(@WebParam(name = "guest") Guest guest);
}

package askers;

import exceptions.EmptyFieldException;
import exceptions.InvalidObjectException;
import exceptions.ValidValuesException;


public abstract class AbstractAsker<T> {
    public abstract T build() throws EmptyFieldException, ValidValuesException, InvalidObjectException;
}

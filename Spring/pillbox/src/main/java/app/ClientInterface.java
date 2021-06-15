package app;

import java.io.IOException;

public interface ClientInterface {
    void medicationTaken(String medicationName) throws IOException;
    void medicationNotTaken(String medicationName) throws IOException;
}

package app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld{

    public String sayHelloWithHessian(String msg);

}

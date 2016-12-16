package beans;

import javax.ejb.Remote;

@Remote
public interface ABean {
    public String print();
}
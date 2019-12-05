package m19.core;

import java.io.Serializable;

interface Observer extends Serializable {
    long serialVersionUID = 20356846356265234L;

    void notifyObserverDelivery(Work work);
    void notifyObserverRequest(Work work);
}
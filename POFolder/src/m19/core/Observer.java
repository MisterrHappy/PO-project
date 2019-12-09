package m19.core;

import java.io.Serializable;

interface Observer extends Serializable {
    long serialVersionUID = 20356846356265234L;

    void notifyObserver(Subject subject);
}
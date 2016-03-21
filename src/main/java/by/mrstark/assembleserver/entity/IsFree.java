package by.mrstark.assembleserver.entity;

/**
 * Created by mrstark on 21.3.16.
 */
public class IsFree {

    private boolean isFree;

    public IsFree(boolean isFree) {
        setFree(isFree);
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}

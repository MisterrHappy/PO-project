package m19.core;

import java.util.Comparator;
import m19.core.User;


class UserComparator implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        if (a.getName().compareTo(b.getName()) == 0)
            return a.hashCode() - b.hashCode();
        return a.getName().compareTo(b.getName());
    }
}
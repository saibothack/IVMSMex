package ivms.sysware.com.ivmsmex.database.user;

import ivms.sysware.com.ivmsmex.database.user.entities.User;

public class UserHelper {

    private static final UserHelper ourInstance = new UserHelper();

    public static UserHelper getInstance() {
        return ourInstance;
    }

    public User getUserProfiler(int iUser) {
        User user = new User();
        user.setId(1);
        user.setRole("Conductor");
        user.setName("Gad Arenas");
        user.setEmail("garenas@sysware.com.mx");
        user.setPhone("5573348266");
        user.setEmployeeNumber("8899NUM");
        user.setBoss("Victor Mondragon");
        user.setBossEmail("mondragon.victor26@gmail.com");
        user.setZone("Centro");
        user.setLocation("Ixtapaluca");

        return user;

    }

}

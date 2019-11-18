package kristian9577.heroes.services;

import kristian9577.heroes.data.models.auth.RegisterUserServiceModel;
import kristian9577.heroes.services.models.LoginUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}

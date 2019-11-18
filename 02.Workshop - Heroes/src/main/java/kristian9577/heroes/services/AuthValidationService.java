package kristian9577.heroes.services;

import kristian9577.heroes.data.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel user);
}

package kristian9577.heroes.services.impl;

import kristian9577.heroes.data.models.User;
import kristian9577.heroes.data.models.auth.RegisterUserServiceModel;
import kristian9577.heroes.data.repositories.UsersRepository;
import kristian9577.heroes.services.AuthService;
import kristian9577.heroes.services.AuthValidationService;
import kristian9577.heroes.services.HashingService;
import kristian9577.heroes.services.models.LoginUserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService authValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;

    public AuthServiceImpl(
            AuthValidationService authValidationService,
            UsersRepository usersRepository,
            ModelMapper mapper,
            HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!authValidationService.isValid(model)) {
            // do something
            return;
        }

        User user = mapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception {
        String passwordHash = hashingService.hash(serviceModel.getPassword());
        return usersRepository
                .findByUsernameAndPassword(serviceModel.getUsername(), passwordHash)
                .map(user -> {
                    String heroName = user.getHero() == null
                            ? null
                            : user.getHero().getName();

                    return new LoginUserServiceModel(serviceModel.getUsername(), heroName);
                })
                .orElseThrow(() -> new Exception("Invalid user"));
    }
}
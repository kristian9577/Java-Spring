package kristian.springessential.service.services.implementations;

import kristian.springessential.data.models.Car;
import kristian.springessential.data.repositories.CarRepo;
import kristian.springessential.service.models.CarServiceModel;
import kristian.springessential.service.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final CarRepo carRepo;

    @Autowired
    public CarServiceImpl(ModelMapper modelMapper, CarRepo carRepo) {
        this.modelMapper = modelMapper;
        this.carRepo = carRepo;
    }

    @Override
    public CarServiceModel save(CarServiceModel model) {
        this.carRepo.save(this.modelMapper.map(model, Car.class));
        return model;
    }

    @Override
    public List<CarServiceModel> getCars() {
        return this.carRepo.findAll().stream()
                .map(car -> this.modelMapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }
}

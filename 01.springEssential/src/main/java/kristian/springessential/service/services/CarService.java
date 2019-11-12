package kristian.springessential.service.services;

import kristian.springessential.service.models.CarServiceModel;

import java.util.List;

public interface CarService {

    CarServiceModel save(CarServiceModel model);

    List<CarServiceModel> getCars();

}

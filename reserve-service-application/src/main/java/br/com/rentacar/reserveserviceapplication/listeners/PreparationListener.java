package br.com.rentacar.reserveserviceapplication.listeners;


import br.com.rentacar.reserveserviceapplication.bindings.PreparationFinishSource;
import br.com.rentacar.reserveserviceapplication.bindings.PreparationSource;
import br.com.rentacar.reserveserviceapplication.dtos.CarUpdateDTO;
import br.com.rentacar.reserveserviceapplication.dtos.PreparationMessageDTO;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.services.CarService;
import br.com.rentacar.reserveserviceapplication.services.ReserveService;
import br.com.rentacar.reserveserviceapplication.types.CarSituation;
import br.com.rentacar.reserveserviceapplication.types.ReserveStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableBinding({PreparationSource.class, PreparationFinishSource.class})

public class PreparationListener {

    private static final Log log = LogFactory.getLog(PreparationListener.class);

    @Autowired
    ReserveService reserveService;

    @Autowired
    CarService carService;

    @StreamListener(PreparationSource.PREPARATION_CHANNEL)
    @HystrixCommand(defaultFallback = "fallbackListenerPreparationStartMessage")
    public void readMessage(PreparationMessageDTO preparationMessageDTO) {
        log.info("reading a message from Preparation Service");
        System.out.println("INICIANDO A PREPARAÇÃO DO VEÍCULO " + preparationMessageDTO.getReserveTransactionId() + " "+ preparationMessageDTO.getCarId());
        carService.updateCar(preparationMessageDTO.getCarId(),new CarUpdateDTO(CarSituation.RESERVED, "Inicializando a preparação", false));
    }

    public void fallbackListenerPreparationStartMessage() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Fallback is dispatched");
    }

    @StreamListener(PreparationFinishSource.PREPARATION_FINISH_CHANNEL)
    @HystrixCommand(defaultFallback = "fallbackListenerPreparationFinishMessage")
    public void readMessage2(PreparationMessageDTO preparationMessageDTO) {
        log.info("reading message from finish preparation from Preparation Service");
        reserveService.updateReserveStatus(preparationMessageDTO.getReserveTransactionId(), ReserveStatus.PREPARED);
        System.out.println(" A PREPARAÇÃO DO VEÍCULO FOI FINALIZADA " + preparationMessageDTO.getReserveTransactionId());
    }

    public void fallbackListenerPreparationFinishMessage() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Fallback is dispatched");
    }


}

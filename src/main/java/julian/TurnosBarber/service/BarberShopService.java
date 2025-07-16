package julian.TurnosBarber.service;

import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.BarberShop;
import julian.TurnosBarber.repository.IBarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberShopService {
    @Autowired
    private IBarberShopRepository barberShopRepository;


    private List<BarberShop> getBarberShops(){
        List<BarberShop> barberShops = barberShopRepository.findAll();
        return barberShops;
    }

    private BarberShop findBarberShopById(String id){
        BarberShop barberShop = barberShopRepository.findById(id).orElse(null);
        return barberShop;
    }

    public void saveBarberShop(BarberShop barberShop){
        barberShopRepository.save(barberShop);
    }

    public void deleteBarberShop(BarberShop barberShop){
        barberShopRepository.delete(barberShop);
    }

    public List<BarberShop> getActiveBarberShop(){
        return barberShopRepository.findByIsActiveTrue();
    }
}

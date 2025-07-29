package julian.TurnosBarber.controller;

import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.BarberShop;
import julian.TurnosBarber.service.BarberShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/barbershop")
public class BarberShopController {

    @Autowired
    private BarberShopService shopService;

    @GetMapping //GET Obtener todos las barberias
    public ResponseEntity<List<BarberShop>> getAllBarberShop(){
        List<BarberShop> barberShop = shopService.getBarberShops();
        return ResponseEntity.ok(barberShop);
    }

    @GetMapping("/{id}") //GET Obtener barberia por id
    public ResponseEntity<BarberShop> getBarberById(@PathVariable String id){
        BarberShop barberShop = shopService.findBarberShopById(id);
        return ResponseEntity.ok(barberShop);
    }

    @GetMapping //GET buscar por barberias activas
    public ResponseEntity<List<BarberShop>> getActiveBarberShop(){
        List<BarberShop> activeBarberShop = shopService.getActiveBarberShop();

        return ResponseEntity.ok(activeBarberShop);


    }

    @PostMapping //POST crear Barberia
    public ResponseEntity<BarberShop> createBarberShop(@RequestBody BarberShop barberShop){
        BarberShop savedBarberShop  = shopService.saveBarberShop(barberShop);
        if(savedBarberShop!= null){
            return ResponseEntity.ok(savedBarberShop);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PutMapping("/{id}") //PUT actualizar barberia
    public ResponseEntity<BarberShop> updateBarberShop(@PathVariable String id, @RequestBody BarberShop barberShop){
        BarberShop updatedShop  = shopService.updateBarberShop(id, barberShop);
        if(updatedShop!= null){
            return ResponseEntity.ok(updatedShop);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping ("{id}")//DELETE eliminar barberia
    public ResponseEntity<Void> deleteBarberShop(@PathVariable String id){
        shopService.deleteBarberShop(id);

        return ResponseEntity.noContent().build();


    }
}

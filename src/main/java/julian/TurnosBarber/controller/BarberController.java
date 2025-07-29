package julian.TurnosBarber.controller;

import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")

public class BarberController {

    @Autowired
    private BarberService barberService;

    @GetMapping //GET Obtener todos los barberos
    public ResponseEntity<List<Barber>> getAllBarbers(){
        List<Barber> barbers = barberService.getBarbers();
        return ResponseEntity.ok(barbers);
    }

    @GetMapping("/{id}") //GET Obtener barbero por id
    public ResponseEntity<Barber> getBarberById(@PathVariable String id){
        Barber barber  = barberService.getBarberById(id);
        if(barber!= null){
            return ResponseEntity.ok(barber);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping //POST crear Barbero
    public ResponseEntity<Barber> createBarber(@RequestBody Barber barber){
        Barber savedBarber  = barberService.saveBarber(barber);
        if(savedBarber!= null){
            return ResponseEntity.ok(savedBarber);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PutMapping("/{id}") //PUT actualizar barbero
    public ResponseEntity<Barber> updateBarber(@PathVariable String id, @RequestBody Barber barber){
        Barber updatedBarber  = barberService.updateBarber(id, barber);
        if(updatedBarber!= null){
            return ResponseEntity.ok(updatedBarber);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping ("{id}")//DELETE eliminar barbero
    public ResponseEntity<Void> deleteBarber(@PathVariable String id){
        barberService.deleteBarber(id);

        return ResponseEntity.noContent().build();


    }

    @GetMapping //GET buscar por barberos activos
    public ResponseEntity<List<Barber>> getActiveBarbers(){
        List<Barber> activeBarbers = barberService.getActiveBarbers();

        return ResponseEntity.ok(activeBarbers);


    }
}

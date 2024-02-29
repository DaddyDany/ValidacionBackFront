package formularioValidacion.demo.Controller;

import formularioValidacion.demo.Model.Type;
import formularioValidacion.demo.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "http://localhost:5173")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping
    public Type saveType(@RequestBody(required = true) Type type){
        return typeService.saveType(type);
    }

    @GetMapping
    public List<Type> getAllTypes() {
        return typeService.getAllTypes();
    }
}

package formularioValidacion.demo.Service;

import formularioValidacion.demo.Model.Type;
import formularioValidacion.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public Type saveType(Type bean) { return typeRepository.save(bean); }

    public List<Type> getAllTypes(){ return typeRepository.findAll(); }
}

package viseco.sc.service;


import viseco.sc.model.document.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComponentService {

 private List<Component> componentList= new ArrayList<>();

 public  List<Component> getAllComponents(){

     return componentList;
 }
 public  void addComponent (Component component){

    componentList.add(component);

    }



}

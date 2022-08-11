package Controller;

import Model.Api;
import Model.Emp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/employees")

public class Employees {

    private ArrayList<Emp> empList=new ArrayList<>();

    @GetMapping("/users")
    public ResponseEntity getEmp(){
        return ResponseEntity.status(200).body(empList);
    }

    @PostMapping("/add")
    public ResponseEntity addEmp(@RequestBody @Valid Emp emp, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message,400));
        }
        empList.add(emp);
        return ResponseEntity.status(201).body( new Api("New employee added !",201));
    }

    @PutMapping ("/users/{index}")
    public ResponseEntity updateEmp(@RequestBody @Valid Emp emp,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message,400));
        }
        if(index>=empList.size()){
            return ResponseEntity.status(400).body(new Api("Invalid index",400));
        }
        empList.set(index,emp);
        return ResponseEntity.status(201).body( new Api("employee updated !",201));
    }
    @DeleteMapping("/users/{index}")
    public ResponseEntity deleteEmp(@PathVariable int index){
        if(index>=empList.size()){
            return ResponseEntity.status(400).body(new Api("Invalid index",400));
        }
        empList.remove(index);
        return ResponseEntity.status(201).body(new Api("employee deleted !",201));
    }

    @PostMapping("onLeave")
    public ResponseEntity onLeaveEmp(@PathVariable int onLeave, Errors errors){
        Emp employees=empList.get(onLeave);

        if (employees.equals(onLeave)) {
            return ResponseEntity.status(400).body(new Api("you are on leave",400));
        }
        if(employees.getAnnualLeave()==0) {
            return ResponseEntity.status(400).body(new Api("you are not on leave",400));
        }
        employees.setOnLeave(true);
        employees.setAnnualLeave(onLeave-1);
        return ResponseEntity.status(200).body(new Api("your are leave",200));
    }
}


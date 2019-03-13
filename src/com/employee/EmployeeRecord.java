package com.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRecord implements Company{

    private String id;
    private String managerId;

    private String name;
    private EmployeeRecord manager;

    private List<EmployeeRecord> employees;

    public EmployeeRecord(){
        super();
        this.employees = new ArrayList<>();

    }

    public EmployeeRecord(String name, String employeeId, String managerId){
        this.name = name;
        this.id = employeeId;
        this.managerId = managerId;
        this.employees = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getManagerId(){
        return managerId;
    }

    public void setManagerId(String managerId){
        this.managerId = managerId;
    }

    public EmployeeRecord getManager(){
        return manager;
    }

    public void setManager(EmployeeRecord manager){
        this.manager = manager;
    }

    public List<EmployeeRecord> getEmployees(){
        return employees;
    }

    public void setEmployees(List<EmployeeRecord> employees){
        this.employees = employees;
    }

    public void addEmployee(EmployeeRecord employee){
        if(!this.employees.contains(employee) && employee != null)
            this.employees.add(employee);
    }

    @Override
    public String toString() {
        return "{id:" + id + ", name:" + name + ", managerId:" + managerId
                + ", employees:" + employees + "}";
    }

    public EmployeeRecord buildHierarchy(List<EmployeeRecord> employees){
        Map<String, EmployeeRecord> mapTmp = new HashMap<>();

        //Save all employees to a map
        for (EmployeeRecord current : employees){
            mapTmp.put(current.getId(), current);
        }

        //loop and assign the manager/employee relationships
        for(EmployeeRecord current : employees){
            String managerId = current.getManagerId();
            if(managerId != null){
                EmployeeRecord manager = mapTmp.get(managerId);
                if(manager != null){
                    current.setManager(manager);
                    manager.addEmployee(current);
                    mapTmp.put(managerId, manager);
                    mapTmp.put(current.getId(), current);
                }
            }
        }

        //get the headManager
        EmployeeRecord headManager = null;
        for (EmployeeRecord employee : mapTmp.values()){
            if (employee.getManager() == null){
                headManager = employee;
                break;
            }
        }

        //System.out.println(headManager);
        return headManager;

    }

}

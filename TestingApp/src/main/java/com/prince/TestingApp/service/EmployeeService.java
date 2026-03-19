package com.prince.TestingApp.service;

import com.prince.TestingApp.dto.EmployeeDto;
import com.prince.TestingApp.entities.Employee;
import com.prince.TestingApp.exceptions.ResourceNotFoundException;
import com.prince.TestingApp.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        modelMapper.map(employeeDto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
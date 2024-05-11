package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.entity.User;
import com.dreamgames.backendengineeringcasestudy.repository.UserRepository;
import com.dreamgames.backendengineeringcasestudy.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityService userEntityService;

    /*
    public List<UserRepository> findAll() {

        List<User> cusCustomerList = userEntityService.findAll();

        //List<UserRepository> userRepositoryList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }


    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        if (cusCustomerSaveRequestDto == null){
            throw new GenBusinessException(GenErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        String password = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(password);
        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto findById(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        cusCustomerEntityService.delete(cusCustomer);
    }

    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {

        Long id = cusCustomerUpdateRequestDto.getId();

        boolean isExist = cusCustomerEntityService.existsById(id);
        if (!isExist){
            throw new GenBusinessException(CusErrorMessage.CUSTOMER_DOES_NOT_EXIST);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);

        String password = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(password);
        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }*/
}

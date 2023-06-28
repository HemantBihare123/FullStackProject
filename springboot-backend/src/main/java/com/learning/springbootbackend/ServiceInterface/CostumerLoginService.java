package com.learning.springbootbackend.ServiceInterface;

import com.learning.springbootbackend.Model.Costumer;

public interface CostumerLoginService {

	Costumer findByEmail(String email);

}

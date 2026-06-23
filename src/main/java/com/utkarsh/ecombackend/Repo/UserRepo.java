package com.utkarsh.ecombackend.Repo;

import com.utkarsh.ecombackend.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    UserModel findByUsername(String username);
}

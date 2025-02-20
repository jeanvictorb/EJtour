package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AuthRequest;
import com.example.demo.Dto.ResgisterRequest;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private  UserRepository userRepository;
    
    private final PasswordEncorder passwordEncorder;
//    private final JwtUtil jwtUtil; 

    public UserService(UserRepository userRepository, PasswordEncorder passwordEncorder){
        this.userRepository = userRepository;
        this.passwordEncorder = passwordEncorder;
     //   this.jwtUtil = jwtUtil;
    }


    public String register(ResgisterRequest request){
        Optional<User> existingUser = userRepository.finByEmail(request.getEmail())
        if(existingUser.isPresent()){
            throw new RuntimeErrorException(null, "Email já cadastrado!");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncorder.encode((request.getPassword())));

        userRepository.save(user);
        return "Usuário cadastrado com sucesso!";
    }


    public String login(AuthRequest request){

        User user = userRepository.finByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncorder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta!");
        }

        return null;
        //return jwtUtil.generateToken(user.getEmail());
    }


    public String save(User user) {
        try {
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do usuario é obrigatório.");
            }
            userRepository.save(user);
            return "Usuario salvo com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar o Usuario: " + e.getMessage();
        }
    }
    public String delete(long id) {
        try {
            userRepository.deleteById(id);
            return "usuario deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Usuario não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o Usuario: " + e.getMessage();
        }
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    public String update(long id, User User) {
        try {
            if (User.getName() == null || User.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do User é obrigatório.");
            }
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User não encontrado.");
            }
            User.setId(id);
            userRepository.save(User);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o User: " + e.getMessage();
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

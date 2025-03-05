package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String para, String assunto, String texto){
	SimpleMailMessage mensagem = new SimpleMailMessage();
	mensagem.setTo(para);
	mensagem.setSubject(assunto);
	mensagem.setText(texto);
	javaMailSender.send(mensagem);


	}

	public void sendEmailInactive(String para){
	String assunto = "Vamos fazer uma aventura!!";
	String texto = "Percebemos que faz algum tempo que você não explora nossos destinos. Visite nosso site e descubra as novas experiências turísticas que preparamos para você!";
	sendEmail(para,assunto,texto);	

	}

}
package service.aplication.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import service.aplication.model.PesquisaUsuario;

@Component
public class Mailer {
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@EventListener
	private void teste(ApplicationReadyEvent event) {
		System.out.println("Iniciando o envio de email  ....");
		//this.enviarEmail("pablomaiden@gmail.com",Arrays.asList("pablomaiden@gmail.com"),"Testando", "Olá!<br/>Teste ok.");
		System.out.println("Terminado o envio de e-mail ....");
	}
	
	public void pesquisasAlimentosDiario(List<PesquisaUsuario> alimentos, List<String> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		
		variaveis.put("alimentos",alimentos);
		
		//List<String> emails = destinatarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
		
		this.enviarEmail("pablomaiden@gmail.com", 
				 destinatarios, 
				"Pesquisas de Alimentos Diário", 
				"mail/envio-email", 
				 variaveis);
	}
	
	public void enviarEmail() {
		
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add("pablomaiden@gmail.com");
		destinatarios.add("pablomaiden2@hotmail.com");
		
        Map<String, Object> variaveis = new HashMap<>();		
		variaveis.put("alimentos","teste");
		
		this.enviarEmail("pablomaiden@gmail.com",destinatarios,"Pesquisas de Alimentos Diário",	"mail/envio-email",variaveis);
		
	}
	
	public void enviarEmail(String remetente,List<String> destinatarios, String assunto, String template, Map<String, Object> variaveis) {
		Context context = new Context(new Locale("pt", "BR"));		
		variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));		
		String mensagem = thymeleaf.process(template, context);		
		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String mensagem) {
		try {			
			MimeMessage mimeMessage = mailSender.createMimeMessage();			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e); 
		}
	}
}

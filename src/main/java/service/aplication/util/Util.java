package service.aplication.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

public class Util {
	
	static TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
	static Locale locale     = new Locale("pt", "BR");
	static ZoneId saoPaulo   = ZoneId.of("America/Sao_Paulo");
	
	public static String formataDateEmString(Date data, String mask) {
		DateFormat formatter = new SimpleDateFormat(mask);
		return formatter.format(data);
	}	
	
	public static void timeZone(){
		TimeZone.setDefault(timeZone);
	}
	
	public static List<String> getServices(String class_){
		@SuppressWarnings("rawtypes")
		Class cls;
		List<String> retorno = new ArrayList<String>();
		try {
			cls = Class.forName(class_);			
			Method[] methods = cls.getDeclaredMethods();	    
	        for (int i = 0; i < methods.length; i++) {
	        	 Method m = methods[i];                	 
	        	 retorno.add("Path:"+m.getAnnotation(RequestMapping.class).value()[0]+"  Method:"+m.getAnnotation(RequestMapping.class).method()[0]);                	 
			}
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}		
        return retorno;
	}
	
	public static Date horaServidor(){
		timeZone();	
		Calendar calendar = Calendar.getInstance(timeZone,locale);	
		Date horaAtual = calendar.getTime();
		return horaAtual;
	}
	
	public static Date getDataAtual(){
		Instant instant   = Instant.now();
		ZonedDateTime now = instant.atZone(saoPaulo);	
		Date dataAtual    = Date.from(now.toInstant());
		return dataAtual;
	}
	
	
	public static Date horaServidor(int hora,int minuto){   	
		   timeZone();	   		   		   
		   Calendar horaMinuto = Calendar.getInstance(timeZone,locale);	
		   horaMinuto.set(Calendar.HOUR_OF_DAY,hora);
		   horaMinuto.set(Calendar.MINUTE,minuto);	
		   horaMinuto.set(Calendar.SECOND,00);
		
		return horaMinuto.getTime();		
	}
	
	@SuppressWarnings("deprecation")
	public static boolean getHoraServidor(Date horaInicio, Date horaFim) throws ParseException{		   
		   timeZone();		
		   Calendar calendar = Calendar.getInstance(timeZone,locale);	
		   		   		   		   
		   Calendar inicio = Calendar.getInstance(timeZone,locale);	
		   inicio.set(Calendar.HOUR_OF_DAY,horaInicio.getHours());
		   inicio.set(Calendar.MINUTE,horaInicio.getMinutes());		   		   
		   		   		   		   
		   Calendar fim = Calendar.getInstance(timeZone,locale);	
		   fim.set(Calendar.HOUR_OF_DAY,horaFim.getHours());
		   fim.set(Calendar.MINUTE,horaFim.getMinutes());		   		   
		   		
		if(calendar.getTimeInMillis() > inicio.getTimeInMillis() && calendar.getTimeInMillis() < fim.getTimeInMillis()){
		   return true;		   				
		}		
		return false;		
	}
	
	public static String getDiaSemana(){		
		String[] DIA_SEMANA_EXTENSO = new DateFormatSymbols(locale).getWeekdays();
        Date date = null;
        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String formato = "yyyy-MM-dd";
        //isso dá maior flexibilidade e certeza q a data vai estar no formato desejado
        SimpleDateFormat df = new SimpleDateFormat(formato);
		try {
			date =  df.parse(data);
		}
		catch (ParseException pe) {
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String diaDaSemana = DIA_SEMANA_EXTENSO[c.get(Calendar.DAY_OF_WEEK)];
		return diaDaSemana.toLowerCase();		
	}
	
	public static String formatterDate(LocalDate localDate){		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return localDate.format(formatter);				
	}
	
	public static String formatterDate(LocalDateTime localDate){		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return localDate.format(formatter);		
			
	}
	
	public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	
	public static String gerarHashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.encode(password);		
	}
	
	public static String formatarData(Date data, String formato) {		
		SimpleDateFormat spd = new SimpleDateFormat(formato,locale);
		return spd.format(data);
	}
	
	public static long minutosRestantes(Date dataInicial) {		
		long diferencas = dataInicial.getTime() - Util.horaServidor().getTime();
		long diferencaSegundos = diferencas / 1000;  
	    long diferencaMinutos  = diferencaSegundos / 60;  
	    //long diferencaHoras    = diferencaMinutos / 60;  
	    //long diferencaDias     = diferencaHoras / 24;		
		return diferencaMinutos;
		
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("12345678"));
	}
	
	
}
